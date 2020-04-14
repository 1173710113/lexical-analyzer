package grammar.production;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import grammar.grammarsymbol.EmptyTerminalSymbol;
import grammar.grammarsymbol.GrammarSymbol;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.StringNonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;
import grammar.grammarsymbol.TokenTerminalSymbol;
import lexer.token.Token;
import util.filereader.InputStrategy;

public class ProductionFactory {

	
	public static Set<Production> getProductionsFormJSONFile(String filePath) throws FileNotFoundException{
		File jsonFile = new File(filePath);
		InputStrategy inputStrategy = InputStrategy.input(jsonFile);
		JSONObject jsonObject = JSON.parseObject(inputStrategy.getString());
		JSONArray jsonArray = jsonObject.getJSONArray("productions");
		int jsonArraySize = jsonArray.size();
		Set<Production> productions = new LinkedHashSet<Production>();
		for(int i = 0; i < jsonArraySize; i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			NonterminalSymbol nonterminalSymbol = new StringNonterminalSymbol(object.getString("left"));
			JSONArray grammarSymbolJSONArray = object.getJSONArray("right");
			int grammarSymbolJSONArraySize = grammarSymbolJSONArray.size();
			List<GrammarSymbol> grammarSymbols = new ArrayList<>();
			for(int j = 0; j < grammarSymbolJSONArraySize; j++) {
				JSONObject grammarSymbolJSONObject = grammarSymbolJSONArray.getJSONObject(j);
				String type = grammarSymbolJSONObject.getString("type");
				switch (type) {
				case "nonterminalSymbol":
					NonterminalSymbol grammarSymbol = new StringNonterminalSymbol(grammarSymbolJSONObject.getString("value"));
					grammarSymbols.add(grammarSymbol);
					break;
				case "emptyTerminalSymbol":
					grammarSymbols.add(EmptyTerminalSymbol.getInstance());
					break;
				case "terminalSymbol":
					TerminalSymbol grammarTerminalSymbol = new TokenTerminalSymbol(new Token(grammarSymbolJSONObject.getInteger("tag")));
					grammarSymbols.add(grammarTerminalSymbol);
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + type);
				}
			}
			Production production = new Production(nonterminalSymbol, grammarSymbols);
			productions.add(production);
		}
		return productions;
	}
}
