package sdt.action;

import java.util.ArrayList;
import java.util.List;

import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action43 extends BaseAction{

	public Action43(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState){
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		List<Integer> falseList = new ArrayList<Integer>();
		falseList.add(sdtAnalyzerState.nextQuad());
		targetItem.addValue(grammarSymbol.toString() + ".falselist", falseList);
		sdtAnalyzerState.gen("goto ");
	}

}
