package grammarsymboltest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import exception.grammar.NullPredictionException;
import grammar.GrammaticalAnalyzer;
import grammar.grammarsymbol.EndTerminalSymbol;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.StringNonterminalSymbol;
import grammar.grammarsymbol.StringTerminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;
import grammar.predictinganalysis.First;
import grammar.predictinganalysis.Follow;
import grammar.predictinganalysis.PredictingAnalysisTable;
import grammar.predictinganalysis.Select;
import grammar.production.EmptyProduction;
import grammar.production.Production;
import util.readhead.ReadHead;
import util.readhead.TerminalSymbolReadHeadImp;

class FirstTest {

	@Test
	void test() throws NullPredictionException {
		
		NonterminalSymbol E = new StringNonterminalSymbol("E");
		NonterminalSymbol T = new StringNonterminalSymbol("T");
		NonterminalSymbol E_ = new StringNonterminalSymbol("E'");
		NonterminalSymbol F = new StringNonterminalSymbol("F");
		NonterminalSymbol T_ = new StringNonterminalSymbol("T'");
		TerminalSymbol plus = new StringTerminalSymbol("+");
		TerminalSymbol multi = new StringTerminalSymbol("*");
		TerminalSymbol leftBracket = new StringTerminalSymbol("(");
		TerminalSymbol rightBracket = new StringTerminalSymbol(")");
		TerminalSymbol id = new StringTerminalSymbol("id");
		TerminalSymbol end = EndTerminalSymbol.getInstance();
		Production production1 = new Production(E, new ArrayList<>(Arrays.asList(T,E_)));
		Production production2 = new Production(E_, Arrays.asList(plus,T,E_));
		Production production3 = new EmptyProduction(E_);
		Production production4 = new Production(T, Arrays.asList(F, T_));
		Production production5 = new Production(T_, Arrays.asList(multi,F,T_));
		Production production6 = new EmptyProduction(T_);
		Production production7 = new Production(F, Arrays.asList(leftBracket, E, rightBracket));
		Production production8 = new Production(F, Arrays.asList(id));
		Set<Production> productions = new LinkedHashSet<>();
		productions.add(production1);
		productions.add(production2);
		productions.add(production3);
		productions.add(production4);
		productions.add(production5);
		productions.add(production6);
		productions.add(production7);
		productions.add(production8);
		Map<NonterminalSymbol, Set<TerminalSymbol>> firstMap =  First.getNonterminalSymbolFirstSetMap(productions);
		for(Map.Entry<NonterminalSymbol, Set<TerminalSymbol>> entry : firstMap.entrySet()) {
			System.out.print("FIRST(" + entry.getKey().toString() + ")={");
			for(TerminalSymbol terminalSymbol : entry.getValue()) {
				System.out.print(terminalSymbol.toString() + " ");
			}
			System.out.println("}");
		}
		Map<NonterminalSymbol, Set<TerminalSymbol>> followMap = Follow.getFollow(firstMap, E, productions);
		System.out.println(followMap.toString());
		Map<Production, Set<TerminalSymbol>> selectMap = Select.getProductionSelectSet(productions, firstMap, followMap);
		System.out.println(selectMap);
		Set<TerminalSymbol> terminalSymbols = new LinkedHashSet<>();
		terminalSymbols.add(id);
		terminalSymbols.add(plus);
		terminalSymbols.add(multi);
		terminalSymbols.add(leftBracket);
		terminalSymbols.add(rightBracket);
		terminalSymbols.add(EndTerminalSymbol.getInstance());
		Set<NonterminalSymbol> nonterminalSymbols = new LinkedHashSet<>();
		nonterminalSymbols.add(E);
		nonterminalSymbols.add(E_);
		nonterminalSymbols.add(T);
		nonterminalSymbols.add(T_);
		nonterminalSymbols.add(F);
		PredictingAnalysisTable predictingAnalysisTable = new PredictingAnalysisTable(selectMap);
		System.out.println(predictingAnalysisTable.toString());
		assertTrue(predictingAnalysisTable.getPredict(E, id).equals(production1));
		assertTrue(predictingAnalysisTable.getPredict(E, leftBracket).equals(production1));
		assertTrue(predictingAnalysisTable.getPredict(E_, plus).equals(production2));
		assertTrue(predictingAnalysisTable.getPredict(E_, rightBracket).equals(production3));
		assertTrue(predictingAnalysisTable.getPredict(E_, end).equals(production3));
		ReadHead<TerminalSymbol> readHead = new TerminalSymbolReadHeadImp(Arrays.asList(id, plus, id, multi, id));
		System.out.println(GrammaticalAnalyzer.grammaticalAnalyse(predictingAnalysisTable, readHead, E));
	}

}
