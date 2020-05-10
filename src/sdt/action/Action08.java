package sdt.action;

import java.util.Stack;

import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action08 extends BaseAction {

	public Action08(String description) {
		super(description);
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzer) {
		Stack<SDTStackItem> stack = sdtAnalyzer.getStack();
		SDTStackItem actionItem = stack.peek();
		SDTStackItem tagetItem = sdtAnalyzer.getFromTop(-1);
		GrammarSymbol grammarSymbol = tagetItem.getGrammarSymbol();
		tagetItem.addValue(grammarSymbol.toString() + ".type", actionItem.getValue("C.type"));
		tagetItem.addValue(grammarSymbol.toString() + ".width", actionItem.getValue("C.width"));
	}

}
