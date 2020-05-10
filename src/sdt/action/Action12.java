package sdt.action;

import grammar.grammarsymbol.GrammarSymbol;
import lexer.token.Array;
import lexer.token.Type;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action12 extends BaseAction {

	public Action12(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState)  {
		SDTStackItem actioItem = sdtAnalyzerState.getStack().peek();
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		Array array = new Array((Integer)actioItem.getValue("num.lexeme"), (Type)actioItem.getValue("C.type"));
		targetItem.addValue(grammarSymbol.toString()+".type", array);
		targetItem.addValue(grammarSymbol.toString() + ".width", array.width);
	}

}
