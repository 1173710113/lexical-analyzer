package grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import grammar.grammarsymbol.EmptyProduction;
import grammar.grammarsymbol.EndTerminalSymbol;
import grammar.grammarsymbol.GrammarSymbol;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.Production;
import grammar.grammarsymbol.TerminalSymbol;
import grammar.predictinganalysis.PredictingAnalysisTable;
import util.readhead.ReadHead;

public class GrammaticalAnalyzer {

	public static List<Production> grammaticalAnalyse(PredictingAnalysisTable predictingAnalysisTable,
			ReadHead<TerminalSymbol> readHead, NonterminalSymbol startSymbol) {
		List<Production> productions = new ArrayList<>();
		Stack<GrammarSymbol> stack = new Stack<>();
		stack.push(startSymbol);
		while (!stack.isEmpty()) {
			TerminalSymbol terminalSymbol = geTerminalSymbol(readHead);
			GrammarSymbol grammarSymbol = stack.pop();
			if (grammarSymbol instanceof NonterminalSymbol) {
				Production production = predictingAnalysisTable.getPredict((NonterminalSymbol) grammarSymbol,
						terminalSymbol);
				productions.add(production);
				if (!(production instanceof EmptyProduction)) {
					List<GrammarSymbol> grammarSymbols = production.getGrammarSymbolList();
					int grammarSymbolsSize = grammarSymbols.size();
					for (int i = grammarSymbolsSize - 1; i >= 0; i--) {
						stack.push(grammarSymbols.get(i));
					}
				}
				if (!terminalSymbol.equals(EndTerminalSymbol.getInstance()))
					readHead.rollBack(1);
			}
		}
		return productions;
	}

	private static TerminalSymbol geTerminalSymbol(ReadHead<TerminalSymbol> readHead) {
		if (readHead.hasNext()) {
			return readHead.next();
		} else {
			return EndTerminalSymbol.getInstance();
		}
	}
}
