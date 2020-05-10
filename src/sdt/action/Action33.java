package sdt.action;

import exception.sdt.NoIdentifierTableItemException;
import grammar.grammarsymbol.GrammarSymbol;
import lexer.token.Type;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action33 extends BaseAction{

	public Action33(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) throws NoIdentifierTableItemException{
		SDTStackItem actionItem = sdtAnalyzerState.getStack().peek();
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		String temp = sdtAnalyzerState.newTemp();
		Type type  = (Type)actionItem.getValue("L'.type");
		System.out.println(temp + " = " + actionItem.getValue("num.lexeme") + " * " + type.width); 
		String oldTemp = (String)actionItem.getValue("L'.arraytemp");
		if(oldTemp != null) {
			String newTemp = sdtAnalyzerState.newTemp();
			System.out.println(newTemp + " = " + oldTemp + " + " + temp);
			temp = newTemp;
		}
		targetItem.addValue(grammarSymbol.toString() + ".arraytemp", temp);
	}

}