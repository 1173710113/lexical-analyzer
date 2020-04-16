package grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import exception.grammar.NullPredictionException;
import grammar.grammarsymbol.EmptyTerminalSymbol;
import grammar.grammarsymbol.EndTerminalSymbol;
import grammar.grammarsymbol.GrammarSymbol;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;
import grammar.predictinganalysis.PredictingAnalysisTable;
import grammar.production.EmptyProduction;
import grammar.production.Production;
import grammar.tree.GrammarAnalysisTreeNode;
import util.readhead.ReadHead;

public class GrammaticalAnalyzer {

	public static List<Production> grammaticalAnalyse(PredictingAnalysisTable predictingAnalysisTable,
			ReadHead<TerminalSymbol> readHead, NonterminalSymbol startSymbol) throws NullPredictionException {
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
			if(terminalSymbol.equals(grammarSymbol)) {
			} else {
				error(stack, terminalSymbol);
			}
		}
		return productions;
	}
	
	private static void error(Stack<GrammarSymbol> stack, TerminalSymbol terminalSymbol) {
		
	}
	
	public static void getGammarTree(PredictingAnalysisTable predictingAnalysisTable,
			ReadHead<TerminalSymbol> readHead, GrammarAnalysisTreeNode grammarAnalysisTreeNode) throws NullPredictionException {
		TerminalSymbol terminalSymbol = geTerminalSymbol(readHead);
		if(grammarAnalysisTreeNode.getNode() instanceof TerminalSymbol) {
			if(grammarAnalysisTreeNode.getNode() instanceof EmptyTerminalSymbol) {
				ReadHeadRollBack(readHead, terminalSymbol);
			}
			if(grammarAnalysisTreeNode.getNode().equals(terminalSymbol)) {
				grammarAnalysisTreeNode.setNode(terminalSymbol);
			}
			return;
		}
		if(grammarAnalysisTreeNode.getNode() instanceof NonterminalSymbol) {
			ReadHeadRollBack(readHead, terminalSymbol);
			Production production = predictingAnalysisTable.getPredict((NonterminalSymbol)grammarAnalysisTreeNode.getNode(),terminalSymbol);
			grammarAnalysisTreeNode.addChildNodeList(production.getGrammarSymbolList());
			for(GrammarAnalysisTreeNode node : grammarAnalysisTreeNode.getChildNodes()) {
				getGammarTree(predictingAnalysisTable, readHead,node);
			}
		}
		return;
	}

	private static TerminalSymbol geTerminalSymbol(ReadHead<TerminalSymbol> readHead) {
		readHead.skipBlank();
		if (readHead.hasNext()) {
			return readHead.next();
		} else {
			return EndTerminalSymbol.getInstance();
		}
	}
	
	private static void ReadHeadRollBack(ReadHead<TerminalSymbol> readHead, TerminalSymbol terminalSymbol) {
		if(!(terminalSymbol instanceof EndTerminalSymbol))readHead.rollBack(1);
	}
}
