package sdt.action;

import java.util.ArrayList;
import java.util.List;

import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action35 extends BaseAction {

	public Action35(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		SDTStackItem actionItem = sdtAnalyzerState.getFromTop(0);
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		List<Integer> trueList = new ArrayList<Integer>();
		trueList.add(sdtAnalyzerState.nextQuad());
		List<Integer> falseList = new ArrayList<Integer>();
		falseList.add(sdtAnalyzerState.nextQuad() + 1);
		targetItem.addValue(grammarSymbol.toString() + ".truelist", trueList);
		targetItem.addValue(grammarSymbol.toString() + ".falselist", falseList);
		sdtAnalyzerState.gen(String.format("if %s %s %s goto ", actionItem.getValue("E.addr"),
				actionItem.getValue("relop.addr"), actionItem.getValue("E.addr'")));
		sdtAnalyzerState.addQuadruple("j" + actionItem.getValue("relop.addr"), actionItem.getValue("E.addr"),
				actionItem.getValue("E.addr'"), null);
		sdtAnalyzerState.gen("goto ");
		sdtAnalyzerState.addQuadruple("j", null, null, null);
	}

}
