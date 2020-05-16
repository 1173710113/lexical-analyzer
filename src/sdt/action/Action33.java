package sdt.action;

import grammar.grammarsymbol.GrammarSymbol;
import lexer.token.Type;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action33 extends BaseAction {

	public Action33(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		SDTStackItem actionItem = sdtAnalyzerState.getStack().peek();
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		Type type = (Type) actionItem.getValue("L'.type");
		if (type == null)
			return;
		String temp = sdtAnalyzerState.newTemp();

		sdtAnalyzerState.gen(temp + " = " + actionItem.getValue("num.lexeme") + " * " + type.width);
		sdtAnalyzerState.addQuadruple("*", actionItem.getValue("num.lexeme"), type.width, temp);
		String oldTemp = (String) actionItem.getValue("L'.arraytemp");
		if (oldTemp != null) {
			String newTemp = sdtAnalyzerState.newTemp();
			sdtAnalyzerState.gen(newTemp + " = " + oldTemp + " + " + temp);
			sdtAnalyzerState.addQuadruple("+", oldTemp, temp, newTemp);
			temp = newTemp;
		}
		targetItem.addValue(grammarSymbol.toString() + ".arraytemp", temp);
	}

}
