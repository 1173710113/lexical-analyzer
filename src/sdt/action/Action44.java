package sdt.action;

import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action44 extends BaseAction {

	public Action44(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		SDTStackItem actionItem = sdtAnalyzerState.getFromTop(0);
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		Object lexeme = null;
		if (actionItem.getValue("<.lexeme") != null) {
			lexeme = actionItem.getValue("<.lexeme");
		} else if (actionItem.getValue(">.lexeme") != null) {
			lexeme = actionItem.getValue(">.lexeme");
		} else if (actionItem.getValue("<=.lexeme") != null) {
			lexeme = actionItem.getValue("<=.lexeme");
		} else if (actionItem.getValue(">=.lexeme") != null) {
			lexeme = actionItem.getValue(">=.lexeme");
		} else if (actionItem.getValue("==.lexeme") != null) {
			lexeme = actionItem.getValue("==.lexeme");
		} else if (actionItem.getValue("!=.lexeme") != null) {
			lexeme = actionItem.getValue("!=.lexeme");
		}
		targetItem.addValue(grammarSymbol.toString() + ".addr", lexeme);
	}

}
