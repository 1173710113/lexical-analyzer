package dfa;

import java.util.HashMap;
import java.util.Map;

public class ConversionTableImp implements ConversionTable{

	private Map<String, Integer> states;
	private Map<Character, Integer> symbols;
	private String[][] table;
	
	@Override
	public String convert(String state, Character symbol) {
		return table[states.get(state)][symbols.get(symbol)];
	}
	
	public ConversionTableImp(int row, int column) {
		states = new HashMap<String, Integer>();
		symbols = new HashMap<Character, Integer>();
		table = new String[row][column];
	}
	
	public void addState(String state, int row) {
		states.put(state, row);
	}
	
	public void addSymbol(Character symbol, int column) {
		symbols.put(symbol, column);
	}
	
	public void addConversion(int row, int column, String state) {
		table[row][column] = state;
	}

}
