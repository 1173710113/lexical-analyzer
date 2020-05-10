package sdt.action;

import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action28 extends BaseAction{

	public Action28(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState){
		SDTStackItem actionItem = sdtAnalyzerState.getStack().peek();
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		targetItem.addValue(grammarSymbol.toString() + ".addr", actionItem.getValue("num.lexeme"));
	}

}
