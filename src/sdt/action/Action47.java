package sdt.action;

import sdt.SDTAnalyzerState;

public class Action47 extends BaseAction {

	public Action47(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		sdtAnalyzerState.gen("exit");
		
	}

}
