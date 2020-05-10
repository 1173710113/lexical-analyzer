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
		System.out.println(actionItem.getValue("L.addr") + " = " + actionItem.getValue("E.addr"));
	}

}
