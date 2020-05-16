package sdt.action;

import java.util.List;

import exception.sdt.NoIdentifierTableItemException;
import exception.sdt.NotProcException;
import lexer.token.Tag;
import lexer.token.Type;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action51 extends BaseAction {

	public Action51(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		try {
		SDTStackItem actionItem = sdtAnalyzerState.getFromTop(0);
		List<Object> paramList = (List<Object>) sdtAnalyzerState.findTemVariant("paramlist");
		Type type = (Type)sdtAnalyzerState.findSymbol((String)actionItem.getValue("id.lexeme")).getType();
		if(type.tag != Tag.PROC) {
			sdtAnalyzerState.addSDTException(new NotProcException(type));
			return;
		}
		for (Object o : paramList) {
			sdtAnalyzerState.gen("param " + o);
			sdtAnalyzerState.addQuadruple("param", null, null, o);
		}
		sdtAnalyzerState.gen(String.format("call %s , %s", actionItem.getValue("id.lexeme"), paramList.size()));
		sdtAnalyzerState.addQuadruple("call", null, null, actionItem.getValue("id.lexeme"));}catch (NoIdentifierTableItemException e) {
			sdtAnalyzerState.addSDTException(e);
		}
	}

}
