package sdt.action;

import java.util.ArrayList;

import exception.sdt.SDTException;
import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action16 extends BaseAction {

	public Action16(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) throws SDTException {
		SDTStackItem actionItem = sdtAnalyzerState.getFromTop(0);
		sdtAnalyzerState.backPatch((ArrayList<Integer>) actionItem.getValue("B.truelist"),
				(Integer) actionItem.getValue("M.quad"));
		sdtAnalyzerState.backPatch((ArrayList<Integer>) actionItem.getValue("B.falselist"),
				(Integer) actionItem.getValue("M.quad'"));
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		targetItem.addValue(grammarSymbol.toString() + ".nextlist",
				sdtAnalyzerState.merge(
						sdtAnalyzerState.merge((ArrayList<Integer>) actionItem.getValue("S.nextlist"),
								(ArrayList<Integer>) actionItem.getValue("N.nextlist")),
						(ArrayList<Integer>) actionItem.getValue("S.nextlist'")));
	}

}
