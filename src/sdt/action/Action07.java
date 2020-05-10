package sdt.action;

import java.util.Map;

import sdt.SDTAnalyzerState;

public class Action07 extends BaseAction {

	public Action07(String description) {
		super(description);
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzer) {
		Map<String, Object> actionItemValueMap = sdtAnalyzer.getStack().peek().getValueMap();
		sdtAnalyzer.addTempVariant("t", actionItemValueMap.get("X.type"));
		sdtAnalyzer.addTempVariant("w", actionItemValueMap.get("X.width"));
	}

}
