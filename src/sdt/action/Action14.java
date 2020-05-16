package sdt.action;

import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action14 extends BaseAction{

	public Action14(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState){
		SDTStackItem actionItem = sdtAnalyzerState.getStack().peek();
		if(actionItem.getValue("L.addr") == null)return;
		sdtAnalyzerState.gen(actionItem.getValue("L.addr") + " = " + actionItem.getValue("E.addr"));
		sdtAnalyzerState.addQuadruple("=", actionItem.getValue("E.addr"), null, actionItem.getValue("L.addr"));
	}

}
