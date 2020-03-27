package dfa;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class DFA {

	private List<String> states;
	private String startState;
	private List<String> endStates;
	private List<Character> inputs;
	private ConversionTable table;
	private String currentState;

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

	public boolean testify(String str) {
		setStateToStartState();
		char[] s = str.toCharArray();
		for (char c : s) {
			assertTrue(inputs.contains(c));
			currentState = table.convert(currentState, c);
			if (currentState == null)return false;
		}
		return isAcceptable();
	}

	public boolean isAcceptable() {
		if (endStates.contains(currentState))return true;
		return false;
	}
	
	public void setStateToStartState() {
		currentState = startState;
	}

	public String getCurrentState() {
		return currentState;
	};

	public void inputChar(Character c) {
		assertTrue(inputs.contains(c));
		currentState = table.convert(currentState, c);
		assertTrue(states.contains(currentState));
	}
}
