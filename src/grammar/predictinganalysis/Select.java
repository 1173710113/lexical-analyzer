package grammar.predictinganalysis;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import grammar.grammarsymbol.EmptyTerminalSymbol;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;
import grammar.production.Production;

public class Select {

	public static Map<Production, Set<TerminalSymbol>> getProductionSelectSet(Set<Production> productions,
			Map<NonterminalSymbol, Set<TerminalSymbol>> firstMap,
			Map<NonterminalSymbol, Set<TerminalSymbol>> followMap) {
		Map<Production, Set<TerminalSymbol>> map = new LinkedHashMap<>();
		for(Production production : productions) {
			map.put(production, new LinkedHashSet<TerminalSymbol>());
			Set<TerminalSymbol> grammarSymbolsFirstSet = First.getGrammarSymbolsFirstSet(production.getGrammarSymbolsWithoutAction(), firstMap);
			if(!grammarSymbolsFirstSet.contains(EmptyTerminalSymbol.getInstance())) {
				map.get(production).addAll(grammarSymbolsFirstSet);
			} else {
				grammarSymbolsFirstSet.remove(EmptyTerminalSymbol.getInstance());
				map.get(production).addAll(grammarSymbolsFirstSet);
				map.get(production).addAll(followMap.get(production.getNonterminalSymbol()));
			}
		}
		
		return map;
	}
}
