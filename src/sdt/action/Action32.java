package sdt.action;

import exception.sdt.NoIdentifierTableItemException;
import grammar.grammarsymbol.GrammarSymbol;
import lexer.token.Type;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;
import sdt.identifiertable.IdentifierTableItem;

public class Action32 extends BaseAction {

	public Action32(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		try {
			SDTStackItem actionItem = sdtAnalyzerState.getStack().peek();
			SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-2);
			GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
			IdentifierTableItem identifierTableItem = sdtAnalyzerState
					.findSymbol((String) actionItem.getValue("id.lexeme"));
			String id = identifierTableItem.getId();
			Type type = identifierTableItem.getType();
			targetItem.addValue(grammarSymbol.toString() + ".addr", id);
			targetItem.addValue(grammarSymbol.toString() + ".type", type);
		} catch (NoIdentifierTableItemException e) {
			sdtAnalyzerState.addSDTException(e);
		}
	}

}
