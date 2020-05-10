package sdt.action;

import exception.sdt.DuplicateIdentifierItemException;
import lexer.token.Tag;
import lexer.token.Type;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action02 extends BaseAction{
	
	
	public Action02(String description) {
		super(description);
	}

	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) throws DuplicateIdentifierItemException {
		SDTStackItem actionItem = sdtAnalyzerState.getStack().peek();
		sdtAnalyzerState.addSymbol((String)actionItem.getValue(Tag.tagToString(Tag.ID) + ".lexeme"), (Type)actionItem.getValue("T.type"), sdtAnalyzerState.getOffset());
		sdtAnalyzerState.addOffset((Integer)actionItem.getValue("T.width"));
		sdtAnalyzerState.addTempVariant("id", actionItem.getValue(Tag.tagToString(Tag.ID) + ".lexeme"));
	}

}
