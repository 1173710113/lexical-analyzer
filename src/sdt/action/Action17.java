package sdt.action;

import java.util.ArrayList;

import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action17 extends BaseAction {

	public Action17(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		SDTStackItem actionItem = sdtAnalyzerState.getFromTop(0);
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		targetItem.addValue(grammarSymbol.toString() + ".nextlist", actionItem.getValue("B.falselist"));
		sdtAnalyzerState.backPatch((ArrayList<Integer>) actionItem.getValue("S.nextlist"),
				(Integer) actionItem.getValue("M.quad"));
		sdtAnalyzerState.backPatch((ArrayList<Integer>) actionItem.getValue("B.truelist"),
				(Integer) actionItem.getValue("M.quad'"));
		sdtAnalyzerState.gen("goto " + actionItem.getValue("M.quad"));
		sdtAnalyzerState.addQuadruple("j", null, null, actionItem.getValue("M.quad"));
	}

}
