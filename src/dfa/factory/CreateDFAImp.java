package dfa.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import dfa.DFA;
import exception.recognize.RecognizeException;
import filereader.InputStrategy;
import regulation.RecognizeConvertion;
import regulation.RecognizeEnd;
import regulation.RecognizeInput;
import regulation.RecognizeStart;
import regulation.RecognizeStates;

public class CreateDFAImp implements CreateDFA{
	
	@Override
	public DFA createDFAByFile(String fileName) throws FileNotFoundException, RecognizeException {
		getStringFromFile(fileName);
		DFAStr dfaStr = getStringFromFile(fileName); 
		DFA dfa = new DFA();
		createDFA(dfa, dfaStr);
		return dfa;
	}
	
	private DFAStr getStringFromFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		InputStrategy reader = InputStrategy.input(file);
		List<String> stringList = reader.getAllStrings();
		return getStringFromStringList(stringList);
	}
	
	private DFAStr getStringFromStringList(List<String> stringList) {
		return DFAStrFactory.constructDFAStr(stringList);
	}
	
	private void createDFA(DFA dfa, DFAStr dfaStr) throws RecognizeException {
		RecognizeStates rss = RecognizeStates.getInstance();
		RecognizeStart rs = RecognizeStart.getInstance();
		RecognizeEnd re = RecognizeEnd.getInstance();
		RecognizeInput ri = RecognizeInput.getInstance();
		RecognizeConvertion rc = RecognizeConvertion.getInstance();
		dfa.setStates(rss.recognize(dfaStr.getStatesStr()))
		.setStartState(rs.recognize(dfaStr.getStartStr()))
		.setEndStates(re.recognize(dfaStr.getEndStr()))
		.setInputs(ri.recognize(dfaStr.getInputStr()))
		.setConversionTable(rc.recognize(dfaStr.getConvertionStr()));
	}

}
