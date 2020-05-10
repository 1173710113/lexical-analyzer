package sdt.action;

import exception.sdt.NoIdentifierTableItemException;
import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

//done
public class Action15 extends BaseAction{

	public Action15(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) throws NoIdentifierTableItemException  {
		SDTStackItem actionItem = sdtAnalyzerState.getStack().peek();
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		String arrayTemp = (String)actionItem.getValue("L'.arraytemp");	
	if(arrayTemp != null) {
		targetItem.addValue(grammarSymbol.toString() + ".addr", actionItem.getValue("L'.addr") + "[" + arrayTemp +"]");
	} else {
		targetItem.addValue(grammarSymbol.toString() + ".addr", actionItem.getValue("L'.addr"));
	}
		
	}

}
