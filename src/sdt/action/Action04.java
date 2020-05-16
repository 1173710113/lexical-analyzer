package sdt.action;

import exception.sdt.DuplicateIdentifierItemException;
import lexer.token.Type;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action04 extends BaseAction {

	public Action04(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		try {
			SDTStackItem actioItem = sdtAnalyzerState.getFromTop(0);
			String id = (String) actioItem.getValue("id.lexeme");
			sdtAnalyzerState.addSymbol(id, Type.RECORD, sdtAnalyzerState.getOffset());
		} catch (DuplicateIdentifierItemException e) {
			sdtAnalyzerState.addSDTException(e);
		}
	}

}
