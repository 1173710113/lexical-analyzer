package sdt;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import exception.recognize.RecognizeException;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.NonterminalSymbolSyn;
import grammar.grammarsymbol.StringNonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;
import grammar.predictinganalysis.First;
import grammar.predictinganalysis.Follow;
import grammar.predictinganalysis.PredictingAnalysisTable;
import grammar.predictinganalysis.Select;
import grammar.production.Production;
import grammar.production.ProductionFactory;
import lexer.LexicalAnalyzer;
import lexer.token.Token;
import util.readhead.ReadHead;
import util.readhead.TerminalSymbolReadHead;
import util.readhead.TokenTerminalSymbolReadHead;

public class SDTTest {

	@Test
	public void test() throws Exception {
		Set<Production> productions = ProductionFactory.getProductionsFromXMLFile("text\\sdt.xml");
		Map<NonterminalSymbol, Set<TerminalSymbol>> firstMap = First.getNonterminalSymbolFirstSetMap(productions);
		for(Map.Entry<NonterminalSymbol, Set<TerminalSymbol>> entry : firstMap.entrySet()) {
			//System.out.println(entry);
		}
		NonterminalSymbol startSymbol = new StringNonterminalSymbol("Program");

		Map<NonterminalSymbol, Set<TerminalSymbol>> followMap = Follow.getFollow(firstMap, startSymbol, productions);
		for(Map.Entry<NonterminalSymbol, Set<TerminalSymbol>> entry : followMap.entrySet()) {
			//System.out.println(entry);
		}
		Map<Production, Set<TerminalSymbol>> selectMap = Select.getProductionSelectSet(productions, firstMap, followMap);
		for(Map.Entry<Production, Set<TerminalSymbol>> entry : selectMap.entrySet()) {
			//System.out.println(entry);
		}
		PredictingAnalysisTable predictingAnalysisTable = new PredictingAnalysisTable(selectMap, followMap);
		LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
		List<String> inputs = new ArrayList<String>();
		//inputs.add("int[5][2] x; x[2][1] = 5; int y; y = x[2][1]; y = y + 1;");
		//inputs.add("int x = 5; while x < 10 do x = x + 1;");
		//inputs.add("int x = 0; switch(x){ case 1: x = x + 1; case 2: x = x + 2; default: x = 3;}");
		inputs.add("int m,z=0x12;");
		inputs.add("m = 2+3*4;");
		inputs.add("char c= 'a';");
		inputs.add("float b = 1;");
		inputs.add("int[2][4] h;");
		inputs.add("int[3] a;");
		inputs.add("a[0] = 2;");
		inputs.add("if m <8 then m = m + 1; else m=m*2;");
		//inputs.add("switch(m){case 1:m = m+1;case 2:m=m+3;default:m=m+6;}");
		//inputs.add("proc int getSum(int x, int y){ int j = x; int k = y; return j+k;}");
		//inputs.add("call getSum(1,2);");
		//inputs.add("record stack {int num;char value;}");
		//inputs.add("int x;int x;");
		//inputs.add(" b = 3;");
		//inputs.add("x[1] = 3;");
		lexicalAnalyzer.setReadHeadFromStringList(inputs);
		lexicalAnalyzer.lexicalAnalyse();
		List<Token> resultToken = lexicalAnalyzer.getResultToken();
		TerminalSymbolReadHead readHead = new TokenTerminalSymbolReadHead(resultToken);
		SDTAnalyzer sdtAnalyzer = new SDTAnalyzer();
		sdtAnalyzer.analyse(predictingAnalysisTable, readHead, startSymbol);
		
	}

}
