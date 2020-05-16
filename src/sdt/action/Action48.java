package sdt.action;

import java.util.ArrayList;

import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action48 extends BaseAction {

	public Action48(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		int quad = sdtAnalyzerState.nextQuad();
		sdtAnalyzerState.backPatch((ArrayList<Integer>) targetItem.getValue(grammarSymbol.toString() + ".nextlist"),
				quad);
	}

}
