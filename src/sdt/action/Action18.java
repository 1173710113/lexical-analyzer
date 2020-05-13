package sdt.action;

import java.util.ArrayList;
import java.util.List;

import exception.sdt.SDTException;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action18 extends BaseAction{

	public Action18(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) throws SDTException {
		SDTStackItem actionItem = sdtAnalyzerState.getFromTop(0);
		String temp = sdtAnalyzerState.newTemp();
		sdtAnalyzerState.addTempVariant("temp", temp);
		List<Integer> exitList = new ArrayList<Integer>();
		sdtAnalyzerState.addTempVariant("exitlist", exitList);
		sdtAnalyzerState.gen(String.format("%s = %s", temp, actionItem.getValue("E.addr")));
	}

}
