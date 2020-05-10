package sdt.action;

import exception.sdt.NoIdentifierTableItemException;
import grammar.grammarsymbol.GrammarSymbol;
import lexer.token.Array;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action34 extends BaseAction{

	public Action34(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) throws NoIdentifierTableItemException{
		SDTStackItem actionItem = sdtAnalyzerState.getStack().peek();
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		String temp = sdtAnalyzerState.newTemp();
		Array type  = (Array)sdtAnalyzerState.findSymbol(grammarSymbol.toString() + ".addr").getType();
		System.out.println(temp + " = " + actionItem.getValue("num.lexeme") + " * " + type.of.width); 
		targetItem.addValue(grammarSymbol.toString() + ".addr", actionItem.getValue("id.lexeme"));
	}

}
