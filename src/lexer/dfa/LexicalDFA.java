package lexer.dfa;

import exception.dfa.InValidInputException;
import exception.dfa.NullConvertionException;

public class LexicalDFA extends DFA{
	
	@Override
	public void inputChar(Character c) throws InValidInputException, NullConvertionException {
		try {
			currentState = table.convert(currentState, c);
		} catch (InValidInputException e) {
			if(currentState.equals("30")) return;
			if(currentState.equals("29") && c != '\n')return;
			if(currentState.equals("25") && c != '\n') {
				currentState = "26";
				return;
			}if(currentState.equals("31")) {
				currentState = "30";
				return;
			}
			throw new InValidInputException();
		}
	}
}
