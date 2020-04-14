package lexer.dfa;

import java.util.List;

import exception.dfa.DFAException;
import exception.dfa.InValidInputException;
import exception.dfa.NullConvertionException;

public class DFA {

	protected List<String> states;
	protected String startState;
	protected List<String> endStates;
	protected List<Character> inputs;
	protected ConversionTable table;
	protected String currentState = startState;

	public DFA setStates(List<String> states) {
		this.states = states;
		return this;
	}
	
	public List<String> getStates(){
		return states;
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
	
	public List<Character> getInputs(){
		return inputs;
	}

	public DFA setConversionTable(ConversionTable table) {
		this.table = table;
		return this;
	}
	
	public ConversionTable getConversionTable() {
		return table;
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
		currentState = table.convert(currentState, c);
	}
}
