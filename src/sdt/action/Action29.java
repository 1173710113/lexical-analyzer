package sdt.action;

import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action29 extends BaseAction{

	public Action29(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState){
		SDTStackItem actionItem = sdtAnalyzerState.getStack().peek();
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-2);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		targetItem.addValue(grammarSymbol.toString() + ".addr", actionItem.getValue("F.addr"));
	}

}
