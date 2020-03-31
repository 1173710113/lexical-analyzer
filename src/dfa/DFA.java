package dfa;

import java.util.List;

import exception.dfa.DFAException;
import exception.dfa.InValidInputException;
import exception.dfa.NullConvertionException;

public class DFA {

	private List<String> states;
	private String startState;
	private List<String> endStates;
	private List<Character> inputs;
	private ConversionTable table;
	private String currentState = startState;

	public DFA setStates(List<String> states) {
		this.states = states;
		return this;
	}

	public DFA setStartState(String startState) {
		this.startState = startState;
		return this;
	}

	public DFA setEndStates(List<String> endStates) {
		this.endStates = endStates;
		return this;
	}

	public DFA setInputs(List<Character> inputs) {
		this.inputs = inputs;
		return this;
	}

	public DFA setConversionTable(ConversionTable table) {
		this.table = table;
		return this;
	}

	public boolean testify(String str) throws InValidInputException {
		setStateToStartState();
		char[] s = str.toCharArray();
		for (char c : s) {
			try {
				currentState = table.convert(currentState, c);
			} catch (DFAException e) {
				return false;
			}
		}
		return isCurrentAcceptable();
	}

	public boolean isCurrentAcceptable() {
		return endStates.contains(currentState);
	}

	public boolean isAcceptable(String state) {
		return endStates.contains(state);
	}

	public void setStateToStartState() {
		currentState = startState;
	}

	public String getCurrentState() {
		return currentState;
	};

	public String getStartState() {
		return startState;
	}

	public void inputChar(Character c) throws InValidInputException, NullConvertionException {

		try {
			currentState = table.convert(currentState, c);
		} catch (InValidInputException e) {
			if(currentState.equals("30")) return;
			if(currentState.equals("29"))return;
			if(currentState.equals("25")) {
				currentState = "26";
				return;
			}
			throw new InValidInputException();
		}
	}
}
