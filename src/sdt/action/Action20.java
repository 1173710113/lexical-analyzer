package sdt.action;

import java.util.ArrayList;
import java.util.List;

import sdt.SDTAnalyzerState;

public class Action20 extends BaseAction {

	public Action20(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		int quad = sdtAnalyzerState.nextQuad();
		List<Integer> exitList = (ArrayList<Integer>) sdtAnalyzerState.findTemVariant("exitlist");
		sdtAnalyzerState.backPatch(exitList, quad);
	}

}
