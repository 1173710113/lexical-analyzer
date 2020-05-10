package grammar.predictinganalysis;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import grammar.grammarsymbol.EmptyTerminalSymbol;
import grammar.grammarsymbol.GrammarSymbol;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;
import grammar.production.Production;

public class First {

	public static Map<NonterminalSymbol, Set<TerminalSymbol>> getNonterminalSymbolFirstSetMap(Set<Production> productionList) {
		Map<NonterminalSymbol, Set<TerminalSymbol>> firstMap = new LinkedHashMap<>();
		for(Production production : productionList) {
			NonterminalSymbol nonterminalSymbol = production.getNonterminalSymbol();
			firstMap.put(nonterminalSymbol, new LinkedHashSet<TerminalSymbol>());
		}
		boolean flag = false;
		do {
			flag = false;
			for (Production production : productionList) {
				NonterminalSymbol nonterminalSymbol = production.getNonterminalSymbol();
				Set<TerminalSymbol> firstList = firstMap.get(nonterminalSymbol);
				List<GrammarSymbol> grammarSymbols = production.getGrammarSymbolsWithoutAction();
				Set<TerminalSymbol> terminalSymbols = getGrammarSymbolsFirstSet(grammarSymbols, firstMap);
				for(TerminalSymbol terminalSymbol : terminalSymbols) {
					if(!firstList.contains(terminalSymbol)) {
						firstList.add(terminalSymbol);
						flag = true;
					}
				}
			}
		} while (flag);
		return firstMap;
	}
	
	public static Set<TerminalSymbol> getGrammarSymbolsFirstSet(List<GrammarSymbol> grammarSymbols,
			Map<NonterminalSymbol, Set<TerminalSymbol>> firstMap) {
		Set<TerminalSymbol> firstSet = new LinkedHashSet<>();
		int grammarSymbolsSize = grammarSymbols.size();
		GrammarSymbol grammarSymbol = grammarSymbols.get(0);
		if (grammarSymbol instanceof TerminalSymbol) {
			if (grammarSymbol instanceof EmptyTerminalSymbol) {
				if (grammarSymbolsSize == 1) {
					firstSet.add(EmptyTerminalSymbol.getInstance());
					return firstSet;
				}
				List<GrammarSymbol> subGrammarSymbols = grammarSymbols.subList(1, grammarSymbolsSize);
				firstSet.addAll(getGrammarSymbolsFirstSet(subGrammarSymbols, firstMap));
				return firstSet;
			} else {
				TerminalSymbol terminalSymbol = (TerminalSymbol) grammarSymbol;
				firstSet.add(terminalSymbol);
				return firstSet;
			}
		}
		if (grammarSymbol instanceof NonterminalSymbol) {
			Set<TerminalSymbol> terminalSymbols = firstMap.get(grammarSymbol);
			firstSet.addAll(terminalSymbols);
			if (terminalSymbols.contains(EmptyTerminalSymbol.getInstance())) {
				if (grammarSymbolsSize == 1) {
					firstSet.add(EmptyTerminalSymbol.getInstance());
					return firstSet;
				}
				List<GrammarSymbol> subGrammarSymbols = grammarSymbols.subList(1, grammarSymbolsSize);
				firstSet.addAll(getGrammarSymbolsFirstSet(subGrammarSymbols, firstMap));
			}
			return firstSet;
		}
		return firstSet;
	}
}
