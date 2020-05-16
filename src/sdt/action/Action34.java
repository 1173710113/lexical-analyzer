package sdt.action;

import exception.sdt.NoIdentifierTableItemException;
import grammar.grammarsymbol.GrammarSymbol;
import lexer.token.Array;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action34 extends BaseAction {

	public Action34(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		try {
			SDTStackItem actionItem = sdtAnalyzerState.getStack().peek();
			SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
			GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
			String temp = sdtAnalyzerState.newTemp();
			Array type = (Array) sdtAnalyzerState.findSymbol(grammarSymbol.toString() + ".addr").getType();
			sdtAnalyzerState.gen(temp + " = " + actionItem.getValue("num.lexeme") + " * " + type.of.width);
			sdtAnalyzerState.addQuadruple("*", actionItem.getValue("num.lexeme"), type.of.width, temp);
			targetItem.addValue(grammarSymbol.toString() + ".addr", actionItem.getValue("id.lexeme"));
		} catch (NoIdentifierTableItemException e) {
			sdtAnalyzerState.addSDTException(e);
		}
	}

}
