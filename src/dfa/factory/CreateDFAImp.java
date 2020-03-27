package dfa.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import dfa.DFA;
import exception.RecognizeException;
import filereader.InputStrategy;
import regulation.RecognizeConvertion;
import regulation.RecognizeEnd;
import regulation.RecognizeInput;
import regulation.RecognizeStart;
import regulation.RecognizeStates;

public class CreateDFAImp implements CreateDFA{

	private String statesStr;
	private String startStr;
	private String endStr;
	private String inputStr;
	private List<String> convertionStr;
	private static final int STATESSTARTROW = 0;
	private static final int STARTSTARTROW = 1;
	private static final int ENDSTARTROW = 2;
	private static final int INPUTSTARTROW = 3;
	private static final int CONVERTIONSTARTROW = 4;
	
	@Override
	public DFA createDFAByFile(String fileName) throws FileNotFoundException, RecognizeException {
		getStringFromFile(fileName);
		DFA dfa = new DFA();
		createDFA(dfa);
		return dfa;
	}
	
	private void getStringFromFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		InputStrategy reader = InputStrategy.input(file);
		List<String> stringList = reader.getAllStrings();
		getStringFromStringList(stringList);
	}
	
	private void getStringFromStringList(List<String> stringList) {
		statesStr = stringList.get(STATESSTARTROW);
		startStr = stringList.get(STARTSTARTROW);
		endStr = stringList.get(ENDSTARTROW);
		inputStr = stringList.get(INPUTSTARTROW);
		convertionStr = new ArrayList<String>();
		int stringListSize = stringList.size();
		for(int i = CONVERTIONSTARTROW; i < stringListSize; i++) {
			convertionStr.add(stringList.get(i));
		}
	}
	
	private void createDFA(DFA dfa) throws RecognizeException {
		RecognizeStates rss = RecognizeStates.getInstance();
		RecognizeStart rs = RecognizeStart.getInstance();
		RecognizeEnd re = RecognizeEnd.getInstance();
		RecognizeInput ri = RecognizeInput.getInstance();
		RecognizeConvertion rc = RecognizeConvertion.getInstance();
		dfa.setStates(rss.recognize(statesStr))
		.setStartState(rs.recognize(startStr))
		.setEndStates(re.recognize(endStr))
		.setInputs(ri.recognize(inputStr))
		.setConversionTable(rc.recognize(convertionStr));
	}

}
