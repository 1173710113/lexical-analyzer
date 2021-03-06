package sdt.action;

import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

//done
public class Action25 extends BaseAction {

	public Action25(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		String temp = sdtAnalyzerState.newTemp();
		SDTStackItem actionItem = sdtAnalyzerState.getStack().peek();
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		sdtAnalyzerState.gen(temp + " = " + actionItem.getValue("E'.addr") + " + "
				+ targetItem.getValue(grammarSymbol.toString() + ".addr"));
		sdtAnalyzerState.addQuadruple("+", actionItem.getValue("E'.addr"),
				targetItem.getValue(grammarSymbol.toString() + ".addr"), temp);
		targetItem.addValue(grammarSymbol.toString() + ".addr", temp);
	}

}
