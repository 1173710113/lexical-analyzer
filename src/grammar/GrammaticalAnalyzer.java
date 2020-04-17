package grammar;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import exception.grammar.GrammarException;
import exception.grammar.NullPredictionException;
import exception.grammar.SynchException;
import exception.grammar.TerminalSymbolNotMatchException;
import grammar.grammarsymbol.EmptyTerminalSymbol;
import grammar.grammarsymbol.EndTerminalSymbol;
import grammar.grammarsymbol.GrammarSymbol;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;
import grammar.predictinganalysis.First;
import grammar.predictinganalysis.Follow;
import grammar.predictinganalysis.PredictingAnalysisTable;
import grammar.predictinganalysis.Select;
import grammar.production.EmptyProduction;
import grammar.production.Production;
import grammar.production.ProductionFactory;
import grammar.tree.GrammarAnalysisTreeNode;
import util.readhead.ReadHead;

public class GrammaticalAnalyzer {

	private PredictingAnalysisTable predictingAnalysisTable;
	private NonterminalSymbol startSymbol;
	private ReadHead<TerminalSymbol> readHead;
	private List<String> errorList;

	public GrammaticalAnalyzer() throws FileNotFoundException {
		Set<Production> productions = ProductionFactory.getProductionsFormJSONFile("text\\grammar.json");
		Map<NonterminalSymbol, Set<TerminalSymbol>> firstMap = First.getNonterminalSymbolFirstSetMap(productions);
		startSymbol = (new ArrayList<Production>(productions)).get(0).getNonterminalSymbol();
		Map<NonterminalSymbol, Set<TerminalSymbol>> followMap = Follow.getFollow(firstMap, startSymbol, productions);
		Map<Production, Set<TerminalSymbol>> selectMap = Select.getProductionSelectSet(productions, firstMap,
				followMap);
		predictingAnalysisTable = new PredictingAnalysisTable(selectMap, followMap);
	}

	public GrammarAnalysisTreeNode analyze(ReadHead<TerminalSymbol> readHead) {
		this.readHead = readHead;
		this.errorList = new ArrayList<String>();
		GrammarAnalysisTreeNode grammarAnalysisTreeNode = new GrammarAnalysisTreeNode(startSymbol);
		try {
			getGammarTree(grammarAnalysisTreeNode);
		} catch (NullPredictionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TerminalSymbolNotMatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SynchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grammarAnalysisTreeNode;
	}

	public static List<Production> grammaticalAnalyse(PredictingAnalysisTable predictingAnalysisTable,
			ReadHead<TerminalSymbol> readHead, NonterminalSymbol startSymbol)
			throws NullPredictionException, SynchException {
		List<Production> productions = new ArrayList<>();
		Stack<GrammarSymbol> stack = new Stack<>();
		stack.push(startSymbol);
		while (!stack.isEmpty()) {
			TerminalSymbol terminalSymbol = getTerminalSymbol(readHead);
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
			if (terminalSymbol.equals(grammarSymbol)) {
			} else {
				error(stack, terminalSymbol);
			}
		}
		return productions;
	}

	private static void error(Stack<GrammarSymbol> stack, TerminalSymbol terminalSymbol) {

	}

	private void getGammarTree(GrammarAnalysisTreeNode grammarAnalysisTreeNode)
			throws TerminalSymbolNotMatchException, NullPredictionException, SynchException {
		TerminalSymbol terminalSymbol = getTerminalSymbol(readHead);
		if (grammarAnalysisTreeNode.getNode() instanceof TerminalSymbol) {
			terminalSymbolProcedure(grammarAnalysisTreeNode, terminalSymbol);
		} else if (grammarAnalysisTreeNode.getNode() instanceof NonterminalSymbol) {
			nonterminalSymbolException(grammarAnalysisTreeNode, terminalSymbol);
		}
		return;
	}

	private void terminalSymbolProcedure(GrammarAnalysisTreeNode grammarAnalysisTreeNode, TerminalSymbol terminalSymbol)
			throws TerminalSymbolNotMatchException {
		if (grammarAnalysisTreeNode.getNode() instanceof EmptyTerminalSymbol) {
			ReadHeadRollBack(readHead, terminalSymbol);
		} else if (grammarAnalysisTreeNode.getNode().equals(terminalSymbol)) {
			grammarAnalysisTreeNode.setNode(terminalSymbol);
		} else {
			throw new TerminalSymbolNotMatchException((TerminalSymbol) grammarAnalysisTreeNode.getNode(),
					terminalSymbol);
		}
	}

	private void nonterminalSymbolException(GrammarAnalysisTreeNode grammarAnalysisTreeNode,
			TerminalSymbol terminalSymbol) throws NullPredictionException, SynchException {
		ReadHeadRollBack(readHead, terminalSymbol);
		Production production = predictingAnalysisTable
				.getPredict((NonterminalSymbol) grammarAnalysisTreeNode.getNode(), terminalSymbol);
		grammarAnalysisTreeNode.addChildNodeList(production.getGrammarSymbolList());
		Iterator<GrammarAnalysisTreeNode> grammarTreeNodeIterator = grammarAnalysisTreeNode.getChildNodes().iterator();
		while (grammarTreeNodeIterator.hasNext()) {
			try {
				GrammarAnalysisTreeNode childNode = grammarTreeNodeIterator.next();
				getGammarTree(childNode);
			} catch (GrammarException e) {
				grammarExceptionHandler(e, grammarTreeNodeIterator);
			}
		}
	}

	private void grammarExceptionHandler(GrammarException e,
			Iterator<GrammarAnalysisTreeNode> grammarTreeNodeIterator) {
		if (e instanceof TerminalSymbolNotMatchException) {
			errorList.add(e.getMessage() + " µ¯³öÖÕ½á·û");
			System.out.println(e.getMessage() + " µ¯³öÖÕ½á·û");
			grammarTreeNodeIterator.remove();
		}
		if (e instanceof NullPredictionException) {
			getTerminalSymbol(readHead);
			System.out.println(e.getMessage() + " ºöÂÔÖÕ½á·û");
			errorList.add(e.getMessage() + " ºöÂÔÖÕ½á·û");
		}
		if (e instanceof SynchException) {
			grammarTreeNodeIterator.remove();
			System.out.println(e.getMessage() + " ºöÂÔ·ÇÖÕ½á·û");
			errorList.add(e.getMessage() + " ºöÂÔ·ÇÖÕ½á·û");
		}
	}

	private static TerminalSymbol getTerminalSymbol(ReadHead<TerminalSymbol> readHead) {
		readHead.skipBlank();
		if (readHead.hasNext()) {
			return readHead.next();
		} else {
			return EndTerminalSymbol.getInstance();
		}
	}

	private static void ReadHeadRollBack(ReadHead<TerminalSymbol> readHead, TerminalSymbol terminalSymbol) {
		if (!(terminalSymbol instanceof EndTerminalSymbol))
			readHead.rollBack(1);
	}
	
	public List<String> getErrorResult(){
		return errorList;
	}
}
