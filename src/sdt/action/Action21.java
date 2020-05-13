package sdt.action;

import java.util.ArrayList;
import java.util.List;

import exception.sdt.SDTException;
import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action21 extends BaseAction {

	public Action21(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) throws SDTException {
		SDTStackItem actioItem = sdtAnalyzerState.getFromTop(0);
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-6);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		int quad = sdtAnalyzerState.nextQuad();
		sdtAnalyzerState.backPatch((ArrayList<Integer>) targetItem.getValue(grammarSymbol.toString() + ".nextlist"),
				quad);
		targetItem = sdtAnalyzerState.getFromTop(-5);
		grammarSymbol = targetItem.getGrammarSymbol();
		List<Integer> nextlist = new ArrayList<Integer>();
		nextlist.add(quad);
		targetItem.addValue(grammarSymbol.toString() + ".nextlist", nextlist);
		sdtAnalyzerState.gen(String.format("if %s != %s goto ", sdtAnalyzerState.findTemVariant("temp"),
				actioItem.getValue("num.lexeme")));
	}

}
