package sdt.action;

import grammar.grammarsymbol.GrammarSymbol;
import sdt.SDTAnalyzerState;

public interface Action extends GrammarSymbol {

	public void execute(SDTAnalyzerState sdtAnalyzer);
}
