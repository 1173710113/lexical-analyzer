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
		Object lexeme = null;
		if(actionItem.getValue("char.lexeme") != null) {
			lexeme = actionItem.getValue("char.lexeme");
		} else if( actionItem.getValue("num.lexeme") != null) {
			lexeme =  actionItem.getValue("num.lexeme");
		} else if( actionItem.getValue("real.lexeme") != null) {
			lexeme =  actionItem.getValue("real.lexeme");
		}
		
		targetItem.addValue(grammarSymbol.toString() + ".addr",lexeme);
	}

}
