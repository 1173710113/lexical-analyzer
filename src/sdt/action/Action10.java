package sdt.action;

import exception.sdt.DuplicateIdentifierItemException;
import lexer.token.Type;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action10 extends BaseAction {

	public Action10(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		try {
			SDTStackItem actionItem = sdtAnalyzerState.getFromTop(0);
			String id = (String) actionItem.getValue("id.lexeme");
			sdtAnalyzerState.addSymbol(id, (Type) actionItem.getValue("X.type"), sdtAnalyzerState.getOffset());
			sdtAnalyzerState.addOffset((Integer) actionItem.getValue("X.width"));
		} catch (DuplicateIdentifierItemException e) {
			sdtAnalyzerState.addSDTException(e);
		}
	}

}
