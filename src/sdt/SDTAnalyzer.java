package sdt;

import java.util.List;

import grammar.grammarsymbol.EmptyTerminalSymbol;
import grammar.grammarsymbol.EndTerminalSymbol;
import grammar.grammarsymbol.GrammarSymbol;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;
import grammar.predictinganalysis.PredictingAnalysisTable;
import grammar.production.Production;
import util.readhead.ReadHead;

public class SDTAnalyzer {
	
	private SDTAnalyzerState state;
	
	public SDTAnalyzer() {
		this.state = new SDTAnalyzerState();
	}

	public void analyse(PredictingAnalysisTable predictingAnalysisTable,
			ReadHead<TerminalSymbol> readHead, NonterminalSymbol startSymbol) throws Exception {
		state.push(startSymbol);
		while (!state.isStackEmpty()) {
			TerminalSymbol terminalSymbol = getTerminalSymbol(readHead);
			GrammarSymbol grammarSymbol = state.peek();
			if (grammarSymbol instanceof NonterminalSymbol) {
				state.pop();
				Production production = predictingAnalysisTable.getPredict((NonterminalSymbol) grammarSymbol,
						terminalSymbol);
				List<GrammarSymbol> grammarSymbols = production.getGrammarSymbolList();
				int grammarSymbolsSize = grammarSymbols.size();
				for (int i = grammarSymbolsSize - 1; i >= 0; i--) {
					state.push(grammarSymbols.get(i));
				}
				if (!terminalSymbol.equals(EndTerminalSymbol.getInstance()))
					readHead.rollBack(1);
			} else if (grammarSymbol instanceof EmptyTerminalSymbol) {
				state.pop();
				if (!terminalSymbol.equals(EndTerminalSymbol.getInstance()))
					readHead.rollBack(1);
			} else if (grammarSymbol instanceof TerminalSymbol) {
				if (terminalSymbol.equals(grammarSymbol)) {
					if(terminalSymbol.hasLexeme())state.setLexeme(terminalSymbol);
					state.pop();
				} else {
					//TODO error handler
				}
			}
			
		}
		System.out.println(state.getIdentifierTable());
	}

	private static TerminalSymbol getTerminalSymbol(ReadHead<TerminalSymbol> readHead) {
		readHead.skipBlank();
		if (readHead.hasNext()) {
			return readHead.next();
		} else {
			return EndTerminalSymbol.getInstance();
		}
	}

	/**
	 * @return the state
	 */
	public SDTAnalyzerState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(SDTAnalyzerState state) {
		this.state = state;
	}
	
	

}
