package grammarsymboltest;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import exception.grammar.NullPredictionException;
import exception.grammar.SynchException;
import exception.grammar.TerminalSymbolNotMatchException;
import exception.recognize.RecognizeException;
import grammar.GrammaticalAnalyzer;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;
import grammar.predictinganalysis.First;
import grammar.predictinganalysis.Follow;
import grammar.predictinganalysis.PredictingAnalysisTable;
import grammar.predictinganalysis.Select;
import grammar.production.Production;
import grammar.production.ProductionFactory;
import grammar.tree.GrammarAnalysisTreeNode;
import lexer.LexicalAnalyzer;
import lexer.token.Token;
import util.readhead.TerminalSymbolReadHead;
import util.readhead.TokenTerminalSymbolReadHead;

class LL1Test {

	@Test
	void test() throws FileNotFoundException, RecognizeException, NullPredictionException, TerminalSymbolNotMatchException, SynchException {
		Set<Production> productions = ProductionFactory.getProductionsFormJSONFile("text\\grammar.json");
		System.out.println(productions);
		Map<NonterminalSymbol, Set<TerminalSymbol>> firstMap = First.getNonterminalSymbolFirstSetMap(productions);
		for(Map.Entry<NonterminalSymbol, Set<TerminalSymbol>> entry : firstMap.entrySet()) {
			System.out.println("FIRST:" + entry);
		}
		NonterminalSymbol startSymbol = (new ArrayList<Production>(productions)).get(0).getNonterminalSymbol();
		Map<NonterminalSymbol, Set<TerminalSymbol>> followMap = Follow.getFollow(firstMap, startSymbol, productions);
		for(Map.Entry<NonterminalSymbol, Set<TerminalSymbol>> entry : followMap.entrySet()) {
			System.out.println("FOLLOW:" + entry);
		}
		Map<Production, Set<TerminalSymbol>> selectMap = Select.getProductionSelectSet(productions, firstMap, followMap);
		for(Map.Entry<Production, Set<TerminalSymbol>> entry : selectMap.entrySet()) {
			System.out.println("SELECT:" + entry);
		}
		PredictingAnalysisTable predictingAnalysisTable = new PredictingAnalysisTable(selectMap, followMap);
		System.out.println(predictingAnalysisTable);
		LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
		lexicalAnalyzer.lexicalAnalyse();
		List<Token> tokens = lexicalAnalyzer.getResultToken();
		TerminalSymbolReadHead readHead = new TokenTerminalSymbolReadHead(tokens);
		System.out.println(tokens);
		List<Production> grammatAnalyseResult = GrammaticalAnalyzer.grammaticalAnalyse(predictingAnalysisTable, readHead, startSymbol);
		System.out.println(grammatAnalyseResult);
		readHead.reset();
		GrammaticalAnalyzer grammaticalAnalyzer = new GrammaticalAnalyzer();
		GrammarAnalysisTreeNode grammarAnalysisTreeNode = grammaticalAnalyzer.analyze(readHead);
	
		printTree(grammarAnalysisTreeNode, 0);
		
	}
	
	private void printTree(GrammarAnalysisTreeNode node, int count) {
		for(int i = 0; i < count ; i++) {
			System.out.print("-");
		}
		System.out.println(node.getNode() + "\n");
		for(GrammarAnalysisTreeNode childNode : node.getChildNodes()) {
			printTree(childNode, count+1);
			
		}
	}

}
