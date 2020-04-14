package grammar.production;

import java.util.Arrays;

import grammar.grammarsymbol.EmptyTerminalSymbol;
import grammar.grammarsymbol.NonterminalSymbol;

public class EmptyProduction extends Production{

	public EmptyProduction(NonterminalSymbol nonterminalSymbol) {
		super(nonterminalSymbol, Arrays.asList(EmptyTerminalSymbol.getInstance()));
	}

}
