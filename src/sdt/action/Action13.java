package sdt.action;

import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action13 extends BaseAction {

	public Action13(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzer) {
		SDTStackItem item = sdtAnalyzer.getFromTop(-1);
		GrammarSymbol grammarSymbol = item.getGrammarSymbol();
		item.addValue(grammarSymbol.toString() + ".type", sdtAnalyzer.findTemVariant("t"));
		item.addValue(grammarSymbol.toString() + ".width", sdtAnalyzer.findTemVariant("w"));
	}

}
