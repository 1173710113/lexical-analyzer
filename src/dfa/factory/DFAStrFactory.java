package dfa.factory;

import java.util.ArrayList;
import java.util.List;

public class DFAStrFactory {
	
	private static final int STATESSTARTROW = 0;
	private static final int STARTSTARTROW = 1;
	private static final int ENDSTARTROW = 2;
	private static final int INPUTSTARTROW = 3;
	private static final int CONVERTIONSTARTROW = 4;
	
	public static DFAStr constructDFAStr(List<String> stringList) {
		DFAStr dfaStr = new DFAStr();
		String statesStr = stringList.get(STATESSTARTROW);
		dfaStr.setStatesStr(statesStr);
		String startStr = stringList.get(STARTSTARTROW);
		dfaStr.setStartStr(startStr);
		String endStr = stringList.get(ENDSTARTROW);
		dfaStr.setEndStr(endStr);
		String inputStr = stringList.get(INPUTSTARTROW);
		dfaStr.setInputStr(inputStr);
		List<String> convertionStr = new ArrayList<String>();
		int stringListSize = stringList.size();
		for(int i = CONVERTIONSTARTROW; i < stringListSize; i++) {
			convertionStr.add(stringList.get(i));
		}
		dfaStr.setConvertionStr(convertionStr);
		return dfaStr;
	};
}
