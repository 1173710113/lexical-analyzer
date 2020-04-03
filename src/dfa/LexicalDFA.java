package dfa;

import exception.dfa.InValidInputException;
import exception.dfa.NullConvertionException;

public class LexicalDFA extends DFA{
	
	@Override
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
