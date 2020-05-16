package sdt.action;

import java.util.List;

import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action50 extends BaseAction {

	public Action50(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		SDTStackItem actionItem = sdtAnalyzerState.getFromTop(0);
		List<Object> paramList = (List<Object>) sdtAnalyzerState.findTemVariant("paramlist");
		paramList.add(actionItem.getValue("E.addr"));
	}

}
