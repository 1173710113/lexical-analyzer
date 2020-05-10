package sdt.action;

import grammar.grammarsymbol.GrammarSymbol;
import lexer.token.Array;
import lexer.token.Type;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action40 extends BaseAction{

	public Action40(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState){
		SDTStackItem srcItem = sdtAnalyzerState.getFromTop(-7);
		Array type = (Array)srcItem.getValue("L'.type");
		Type subType = type.of;
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-5);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		targetItem.addValue(grammarSymbol.toString() + ".type", subType);
	}

}
