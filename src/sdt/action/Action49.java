package sdt.action;

import java.util.ArrayList;
import java.util.List;

import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action49 extends BaseAction{

	public Action49(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		SDTStackItem actionItem = sdtAnalyzerState.getFromTop(0);
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(actionItem.getValue("E.addr"));
		sdtAnalyzerState.addTempVariant("paramlist", paramList);
	}

}
