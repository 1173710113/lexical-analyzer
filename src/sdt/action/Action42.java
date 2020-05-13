package sdt.action;

import java.util.ArrayList;
import java.util.List;

import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action42 extends BaseAction{

	public Action42(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState){
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		List<Integer> trueList = new ArrayList<Integer>();
		trueList.add(sdtAnalyzerState.nextQuad());
		targetItem.addValue(grammarSymbol.toString() + ".truelist", trueList);
		sdtAnalyzerState.gen("goto ");
	}

}
