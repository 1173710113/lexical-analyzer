package sdt.action;

import java.util.ArrayList;
import java.util.List;

import exception.sdt.SDTException;
import sdt.SDTAnalyzerState;

public class Action22 extends BaseAction{

	public Action22(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) throws SDTException {
		int quad = sdtAnalyzerState.nextQuad();
		List<Integer> exitList = (ArrayList<Integer>)sdtAnalyzerState.findTemVariant("exitlist");
		exitList.add(quad);
		sdtAnalyzerState.gen("goto ");
	}

}
