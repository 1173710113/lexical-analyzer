package sdt.action;

import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action06 extends BaseAction{

	public Action06(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState)  {
		SDTStackItem actionItem = sdtAnalyzerState.getStack().peek();
		sdtAnalyzerState.gen(sdtAnalyzerState.findTemVariant("id") + "=" + actionItem.getValue("F.addr"));
		sdtAnalyzerState.addQuadruple("=", actionItem.getValue("F.addr"), null, sdtAnalyzerState.findTemVariant("id"));
	}

}
