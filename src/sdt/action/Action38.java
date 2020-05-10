package sdt.action;

import exception.sdt.NoIdentifierTableItemException;
import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action38 extends BaseAction{

	public Action38(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) throws NoIdentifierTableItemException{
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		targetItem.addValue(grammarSymbol.toString() + ".quad", sdtAnalyzerState.nextQuad());
	}

}
