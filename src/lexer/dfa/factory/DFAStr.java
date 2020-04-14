package lexer.dfa.factory;

import java.util.List;

public class DFAStr {
	private String statesStr;
	private String startStr;
	private String endStr;
	private String inputStr;
	private List<String> convertionStr;
	
	public String getStatesStr() {
		return statesStr;
	}
	public String getStartStr() {
		return startStr;
	}
	public String getEndStr() {
		return endStr;
	}
	public String getInputStr() {
		return inputStr;
	}
	public List<String> getConvertionStr() {
		return convertionStr;
	}
	public void setStatesStr(String statesStr) {
		this.statesStr = statesStr;
	}
	public void setStartStr(String startStr) {
		this.startStr = startStr;
	}
	public void setEndStr(String endStr) {
		this.endStr = endStr;
	}
	public void setInputStr(String inputStr) {
		this.inputStr = inputStr;
	}
	public void setConvertionStr(List<String> convertionStr) {
		this.convertionStr = convertionStr;
	}
	
	
}
