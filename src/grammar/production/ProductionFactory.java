package grammar.production;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import grammar.grammarsymbol.EmptyTerminalSymbol;
import grammar.grammarsymbol.GrammarSymbol;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.StringNonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;
import lexer.token.Token;
import sdt.action.ActionGenerator;
import util.filereader.InputStrategy;

public class ProductionFactory {

	public static Set<Production> getProductionsFormJSONFile(String filePath) throws FileNotFoundException {
		File jsonFile = new File(filePath);
		InputStrategy inputStrategy = InputStrategy.input(jsonFile);
		JSONObject jsonObject = JSON.parseObject(inputStrategy.getString());
		JSONArray jsonArray = jsonObject.getJSONArray("productions");
		int jsonArraySize = jsonArray.size();
		Set<Production> productions = new LinkedHashSet<Production>();
		for (int i = 0; i < jsonArraySize; i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			NonterminalSymbol nonterminalSymbol = new StringNonterminalSymbol(object.getString("left"));
			JSONArray grammarSymbolJSONArray = object.getJSONArray("right");
			int grammarSymbolJSONArraySize = grammarSymbolJSONArray.size();
			List<GrammarSymbol> grammarSymbols = new ArrayList<>();
			for (int j = 0; j < grammarSymbolJSONArraySize; j++) {
				JSONObject grammarSymbolJSONObject = grammarSymbolJSONArray.getJSONObject(j);
				String type = grammarSymbolJSONObject.getString("type");
				switch (type) {
				case "nonterminalSymbol":
					NonterminalSymbol grammarSymbol = new StringNonterminalSymbol(
							grammarSymbolJSONObject.getString("value"));
					grammarSymbols.add(grammarSymbol);
					break;
				case "emptyTerminalSymbol":
					grammarSymbols.add(EmptyTerminalSymbol.getInstance());
					break;
				case "terminalSymbol":
					TerminalSymbol grammarTerminalSymbol = new Token(grammarSymbolJSONObject.getInteger("tag"));
					grammarSymbols.add(grammarTerminalSymbol);
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + type);
				}
			}
			Production production;
			production = new Production(nonterminalSymbol, grammarSymbols);
			productions.add(production);
		}
		return productions;
	}

	public static Set<Production> getProductionsFromXMLFile(String filePath)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document d = builder.parse(filePath);
		NodeList productionNodeList = d.getElementsByTagName("production");
		Set<Production> productions = new LinkedHashSet<Production>();
		int productionNodeListSize = productionNodeList.getLength();
		for (int i = 0; i < productionNodeListSize; i++) {
			Node childNode = productionNodeList.item(i);
			productions.addAll(constructProduction(childNode));
		}
		return productions;
	}

	private static Set<Production> constructProduction(Node node) {
		Set<Production> productions = new LinkedHashSet<Production>();
		Element element = (Element) node;
		NonterminalSymbol nonterminalSymbol = new StringNonterminalSymbol(element.getAttribute("value"));
		NodeList childNodeList = element.getChildNodes();
		int childNodeListSize = childNodeList.getLength();
		for (int i = 0; i < childNodeListSize; i++) {
			if (childNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Node chilNode = childNodeList.item(i);
				List<GrammarSymbol> grammarSymbolList = constructProductionRight(chilNode);
				Production production = new Production(nonterminalSymbol, grammarSymbolList);
				productions.add(production);
			}
		}
		return productions;
	}

	private static List<GrammarSymbol> constructProductionRight(Node node) {
		List<GrammarSymbol> grammarSymbols = new ArrayList<GrammarSymbol>();
		NodeList childNodeList = node.getChildNodes();
		int childNodeListSize = childNodeList.getLength();
		for (int i = 0; i < childNodeListSize; i++) {
			if (childNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				String type = childNodeList.item(i).getNodeName();
				switch (type) {
				case "nonterminal":
					NonterminalSymbol nonterminalSymbol = new StringNonterminalSymbol(
							childNodeList.item(i).getFirstChild().getNodeValue());
					grammarSymbols.add(nonterminalSymbol);
					break;
				case "terminal":
					TerminalSymbol terminalSymbol = new Token(
							Integer.parseInt(childNodeList.item(i).getFirstChild().getNodeValue()));
					grammarSymbols.add(terminalSymbol);
					break;
				case "empty":
					grammarSymbols.add(EmptyTerminalSymbol.getInstance());
					break;
				case "action":
					Element element = (Element) childNodeList.item(i);
					grammarSymbols.add(ActionGenerator.parseAction(element.getFirstChild().getNodeValue(),
							element.getAttribute("name")));

					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + type);
				}
			}
		}
		return grammarSymbols;

	}
}
