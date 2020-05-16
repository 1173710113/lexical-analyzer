package sdt.action;

import exception.sdt.DuplicateIdentifierItemException;
import lexer.token.Tag;
import lexer.token.Type;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action05 extends BaseAction {

	public Action05(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		try {
			SDTStackItem actionItem = sdtAnalyzerState.getStack().peek();
			sdtAnalyzerState.addSymbol((String) actionItem.getValue(Tag.tagToString(Tag.ID) + ".lexeme"),
					(Type) sdtAnalyzerState.findTemVariant("t"), sdtAnalyzerState.getOffset());
			sdtAnalyzerState.addOffset((Integer) sdtAnalyzerState.findTemVariant("w"));
			sdtAnalyzerState.addTempVariant("id", actionItem.getValue(Tag.tagToString(Tag.ID) + ".lexeme"));
		} catch (DuplicateIdentifierItemException e) {
			sdtAnalyzerState.addSDTException(e);
		}
	}

}
