package sdt.action;

import java.util.ArrayList;

import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action46 extends BaseAction {

	public Action46(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		SDTStackItem actionItem = sdtAnalyzerState.getFromTop(0);
		sdtAnalyzerState.backPatch((ArrayList<Integer>)actionItem.getValue("S.nextlist"), sdtAnalyzerState.nextQuad());
		
	}

}
