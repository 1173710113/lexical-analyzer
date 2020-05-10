package xml;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import grammar.grammarsymbol.EmptyTerminalSymbol;
import grammar.grammarsymbol.GrammarSymbol;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.StringNonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;
import grammar.production.Production;
import lexer.token.Token;

public class XMLTest {

	@Test
	public void test() throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document d = builder.parse("text\\NewFile.xml");
		NodeList list = d.getElementsByTagName("production");
		  for (int i = 0; i <list.getLength() ; i++) {
	            Element element = (Element) list.item(i);
	            NonterminalSymbol leftnonterminalSymbol = new StringNonterminalSymbol(element.getAttribute("value"));
	            NodeList childNodes = element.getChildNodes();
	            for (int j = 0; j <childNodes.getLength() ; j++) {
	                if (childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) {
	                	NodeList n = childNodes.item(j).getChildNodes();
	                	List<GrammarSymbol> grammarSymbols = new ArrayList<GrammarSymbol>();
	                	for(int k = 0; k < n.getLength(); k++) {
	                		if(n.item(k).getNodeType() == Node.ELEMENT_NODE) {
	                			 //获取节点
	                           String nodeName = n.item(k).getNodeName();
	                           if(nodeName.equals("nonterminal")) {
	                        	  NonterminalSymbol nonterminalSymbol = new StringNonterminalSymbol(n.item(k).getFirstChild().getNodeValue());
	                        	  grammarSymbols.add(nonterminalSymbol);
	                           } else if(nodeName.equals("terminal")) {
	                        	   TerminalSymbol terminalSymbol = new Token(Integer.parseInt(n.item(k).getFirstChild().getNodeValue()));
	                        	   grammarSymbols.add(terminalSymbol);
	                           } else if(nodeName.equals("empty")) {
	                        	   grammarSymbols.add(EmptyTerminalSymbol.getInstance());
	                           }
	                		}
	                	}
	                	
	                	Production production = new Production(leftnonterminalSymbol, grammarSymbols);
	                	System.out.println(production.toString());
	                }
	            }
	        }
	}

}
