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
import grammar.grammarsymbol.Production;
import grammar.grammarsymbol.TerminalSymbol;

public class Follow {

	private Map<NonterminalSymbol, Set<TerminalSymbol>> map;
	private Map<NonterminalSymbol, Set<TerminalSymbol>> firstMap;
	private NonterminalSymbol startSymbol;
	private Set<Production> productions;

	public Follow(Map<NonterminalSymbol, Set<TerminalSymbol>> firstMap, NonterminalSymbol startSymbol,
			Set<Production> productions) {
		this.firstMap = firstMap;
		this.startSymbol = startSymbol;
		this.productions = productions;
		this.map = new LinkedHashMap<>();
		for (NonterminalSymbol nonterminalSymbol : firstMap.keySet()) {
			this.map.put(nonterminalSymbol, new LinkedHashSet<TerminalSymbol>());
		}
	}

	public Map<NonterminalSymbol, Set<TerminalSymbol>> getFollow() {
		boolean flag = false;
		do {
			flag = false;
			for (Production production : productions) {
				NonterminalSymbol nonterminalSymbol = production.getNonterminalSymbol();
				Set<TerminalSymbol> followList = map.get(nonterminalSymbol);
				if (isStartSymbol(nonterminalSymbol)) {
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

	private boolean isLastIndex(int index, int size) {
		return index == size - 1;
	}

	private boolean isStartSymbol(NonterminalSymbol nonterminalSymbol) {
		return startSymbol.equals(nonterminalSymbol);
	}

	private boolean addEndSymbole(Set<TerminalSymbol> followList) {
		boolean flag = false;
		TerminalSymbol endSymbol = EndTerminalSymbol.getInstance();
		if (!followList.contains(endSymbol)) {
			followList.add(endSymbol);
			flag = true;
			;
		}
		return flag;
	}

	private boolean addListIntoFollow(Set<TerminalSymbol> followList, Set<TerminalSymbol> followListTemp) {
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
