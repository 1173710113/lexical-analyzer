package grammar.predictinganalysis;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import grammar.grammarsymbol.EmptyTerminalSymbol;
import grammar.grammarsymbol.EndTerminalSymbol;
import grammar.grammarsymbol.GrammarSymbol;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;
import grammar.production.Production;

public class Follow {

	public static Map<NonterminalSymbol, Set<TerminalSymbol>> getFollow(Map<NonterminalSymbol, Set<TerminalSymbol>> firstMap,
			NonterminalSymbol startSymbol, Set<Production> productions) {
		Map<NonterminalSymbol, Set<TerminalSymbol>> map = new LinkedHashMap<>();
		for (NonterminalSymbol nonterminalSymbol : firstMap.keySet()) {
			map.put(nonterminalSymbol, new LinkedHashSet<TerminalSymbol>());
		}
		boolean flag = false;
		do {
			flag = false;
			for (Production production : productions) {
				NonterminalSymbol nonterminalSymbol = production.getNonterminalSymbol();
				Set<TerminalSymbol> followList = map.get(nonterminalSymbol);
				if (isStartSymbol(startSymbol, nonterminalSymbol)) {
					flag = flag || addEndSymbole(followList);
				}
				List<GrammarSymbol> grammarSymbols = production.getGrammarSymbolList();
				int grammarSymbolsSize = grammarSymbols.size();
				for (int i = 0; i < grammarSymbolsSize; i++) {
					GrammarSymbol grammarSymbol = grammarSymbols.get(i);
					if (grammarSymbol instanceof NonterminalSymbol) {
						followList = map.get(grammarSymbol);
						if (isLastIndex(i, grammarSymbolsSize)) {
							Set<TerminalSymbol> followListTemp = map.get(nonterminalSymbol);
							flag = flag || addListIntoFollow(followList, followListTemp);
						} else {
							List<GrammarSymbol> subGrammarSymbols = grammarSymbols.subList(i + 1, grammarSymbolsSize);
							Set<TerminalSymbol> firstSymbols = First.getGrammarSymbolsFirstSet(subGrammarSymbols,
									firstMap);
							if (firstSymbols.contains(EmptyTerminalSymbol.getInstance())) {
								Set<TerminalSymbol> followListTemp = map.get(nonterminalSymbol);
								flag = flag || addListIntoFollow(followList, followListTemp);
							}
							firstSymbols.remove(EmptyTerminalSymbol.getInstance());
							flag = flag || addListIntoFollow(followList, firstSymbols);
						}
					}
				}
			}
		} while (flag);
		return map;
	}

	private static boolean isLastIndex(int index, int size) {
		return index == size - 1;
	}

	private static boolean isStartSymbol(NonterminalSymbol startSymbol, NonterminalSymbol nonterminalSymbol) {
		return startSymbol.equals(nonterminalSymbol);
	}

	private static boolean addEndSymbole(Set<TerminalSymbol> followList) {
		boolean flag = false;
		TerminalSymbol endSymbol = EndTerminalSymbol.getInstance();
		if (!followList.contains(endSymbol)) {
			followList.add(endSymbol);
			flag = true;
			;
		}
		return flag;
	}

	private static boolean addListIntoFollow(Set<TerminalSymbol> followList, Set<TerminalSymbol> followListTemp) {
		boolean flag = false;
		for (TerminalSymbol terminalSymbol : followListTemp) {
			if (!followList.contains(terminalSymbol)) {
				followList.add(terminalSymbol);
				flag = true;
			}
		}
		return flag;
	}

}
