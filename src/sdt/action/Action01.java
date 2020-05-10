package sdt.action;

import sdt.SDTAnalyzerState;

public class Action01 extends BaseAction {

	public Action01(String description) {
		super(description);
	}

	@Override
	public void execute(SDTAnalyzerState sdtAnalyzer) {
		sdtAnalyzer.setOffset(0);

	}

}
