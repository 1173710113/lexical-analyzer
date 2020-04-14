package grammar.predictinganalysis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.Production;
import grammar.grammarsymbol.TerminalSymbol;

public class PredictingAnalysisTable {

	private Map<NonterminalSymbol, Integer> nonterminalSymbolMap;
	private Map<TerminalSymbol, Integer> terminalSymbolMap;
	private Production[][] table;

	public PredictingAnalysisTable(Map<Production, Set<TerminalSymbol>> selectMap,
			Set<NonterminalSymbol> nonterminalSymbols, Set<TerminalSymbol> terminalSymbols) {
		table = initTable(nonterminalSymbols.size(),terminalSymbols.size());
		nonterminalSymbolMap = initNonterminalSymbolMap(nonterminalSymbols);
		terminalSymbolMap = initTerminalSymbolMap(terminalSymbols);
		for (Map.Entry<Production, Set<TerminalSymbol>> entry : selectMap.entrySet()) {
			Production production = entry.getKey();
			Set<TerminalSymbol> selectSymbols = entry.getValue();
			for (TerminalSymbol terminalSymbol : selectSymbols) {
				table[nonterminalSymbolMap.get(production.getNonterminalSymbol())][terminalSymbolMap
						.get(terminalSymbol)] = production;
			}
		}

	}
	
	public Production getPredict(NonterminalSymbol nonterminalSymbol, TerminalSymbol terminalSymbol) {
		return table[nonterminalSymbolMap.get(nonterminalSymbol)][terminalSymbolMap.get(terminalSymbol)];
	}

	private Map<NonterminalSymbol, Integer> initNonterminalSymbolMap(Set<NonterminalSymbol> nonterminalSymbols) {
		Map<NonterminalSymbol, Integer> nonterminalSymbolMap = new LinkedHashMap<>();
		int nonterminalSymbolsSize = nonterminalSymbols.size();
		List<NonterminalSymbol> tempNonterminalSymbols = new ArrayList<>(nonterminalSymbols);;
		for (int i = 0; i < nonterminalSymbolsSize; i++) {
			NonterminalSymbol nonterminalSymbol = tempNonterminalSymbols.get(i);
			nonterminalSymbolMap.put(nonterminalSymbol, i);
		}
		return nonterminalSymbolMap;
	}

	private Map<TerminalSymbol, Integer> initTerminalSymbolMap(Set<TerminalSymbol> terminalSymbols) {
		Map<TerminalSymbol, Integer> terminalSymbolMap = new LinkedHashMap<>();
		int terminalSymbolsSize = terminalSymbols.size();
		List<TerminalSymbol> tempTerminalSymbols = new ArrayList<>(terminalSymbols);
		for (int i = 0; i < terminalSymbolsSize; i++) {
			TerminalSymbol terminalSymbol = tempTerminalSymbols.get(i);
			terminalSymbolMap.put(terminalSymbol, i);
		}
		return terminalSymbolMap;
	}
	
	private Production[][] initTable(int row, int column){
		Production[][] table = new Production[row][column];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				table[i][j] = null;
			}
		}
		return table;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder =  new StringBuilder();
		for(int i = 0; i < table.length; i++) {
			for(int j = 0; j < table[i].length; j++) {
				if(table[i][j] == null) {
					stringBuilder.append("null======");
				} else {
					stringBuilder.append(table[i][j].toString() + "======");
				}
				
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}

}
