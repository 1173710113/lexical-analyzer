package grammar.grammarsymbol;

import java.util.Arrays;

public class EmptyProduction extends Production{

	public EmptyProduction(NonterminalSymbol nonterminalSymbol) {
		super(nonterminalSymbol, Arrays.asList(EmptyTerminalSymbol.getInstance()));
	}

}
