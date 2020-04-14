package grammarsymboltest;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import grammar.grammarsymbol.EmptyTerminalSymbol;
import grammar.grammarsymbol.EndTerminalSymbol;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.Production;
import grammar.grammarsymbol.StringNonterminalSymbol;
import grammar.grammarsymbol.StringTerminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;
import grammar.predictinganalysis.First;
import grammar.predictinganalysis.Follow;
import grammar.predictinganalysis.PredictingAnalysisTable;
import grammar.predictinganalysis.Select;

class FirstTest {

	@Test
	void test() {
		
		NonterminalSymbol E = new StringNonterminalSymbol("E");
		NonterminalSymbol T = new StringNonterminalSymbol("T");
		NonterminalSymbol E_ = new StringNonterminalSymbol("E'");
		NonterminalSymbol F = new StringNonterminalSymbol("F");
		NonterminalSymbol T_ = new StringNonterminalSymbol("T'");
		TerminalSymbol plus = new StringTerminalSymbol("+");
		TerminalSymbol empty = EmptyTerminalSymbol.getInstance();
		TerminalSymbol multi = new StringTerminalSymbol("*");
		TerminalSymbol leftBracket = new StringTerminalSymbol("(");
		TerminalSymbol rightBracket = new StringTerminalSymbol(")");
		TerminalSymbol id = new StringTerminalSymbol("id");
		Production production1 = new Production(E, Arrays.asList(T,E_));
		Production production2 = new Production(E_, Arrays.asList(plus,T,E_));
		Production production3 = new Production(E_, Arrays.asList(empty));
		Production production4 = new Production(T, Arrays.asList(F, T_));
		Production production5 = new Production(T_, Arrays.asList(multi,F,T_));
		Production production6 = new Production(T_, Arrays.asList(empty));
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
		Follow follow = new Follow(firstMap, E, productions);
		Map<NonterminalSymbol, Set<TerminalSymbol>> followMap = follow.getFollow();
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
		PredictingAnalysisTable predictingAnalysisTable = new PredictingAnalysisTable(selectMap, nonterminalSymbols, terminalSymbols);
		System.out.println(predictingAnalysisTable.toString());
	}

}
