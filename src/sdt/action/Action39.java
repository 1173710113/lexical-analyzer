package sdt.action;

import java.util.ArrayList;
import java.util.List;

import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action39 extends BaseAction{

	public Action39(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		List<Integer> nextList = new ArrayList<Integer>();
		nextList.add(sdtAnalyzerState.nextQuad());
		targetItem.addValue(grammarSymbol.toString() + ".nextlist", nextList);
		sdtAnalyzerState.gen("goto ");
		sdtAnalyzerState.addQuadruple("j", null, null, null);
	}

}
