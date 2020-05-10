package sdt.action;

import java.util.ArrayList;
import java.util.List;

import exception.sdt.NoIdentifierTableItemException;
import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action35 extends BaseAction{

	public Action35(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) throws NoIdentifierTableItemException{
		SDTStackItem targetItem = sdtAnalyzerState.getFromTop(-1);
		GrammarSymbol grammarSymbol = targetItem.getGrammarSymbol();
		List<Integer> trueList = new ArrayList<Integer>();
		trueList.add(sdtAnalyzerState.nextQuad());
		List<Integer> falseList = new ArrayList<Integer>();
		falseList.add(sdtAnalyzerState.nextQuad() + 1);
		targetItem.addValue(grammarSymbol.toString() + ".truelist", trueList);
		targetItem.addValue(grammarSymbol.toString() + ".falselist", falseList);
		System.out.println(" go to ");
		// TODO
	}

}