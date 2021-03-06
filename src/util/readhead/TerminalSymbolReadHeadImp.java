package util.readhead;

import static org.junit.Assert.assertTrue;

import java.util.List;

import grammar.grammarsymbol.TerminalSymbol;


public class TerminalSymbolReadHeadImp implements  TerminalSymbolReadHead{

	private  List<TerminalSymbol> terminalSymbols;
	private int cursor = 0;
	
	public TerminalSymbolReadHeadImp(List<TerminalSymbol> terminalSymbols) {
		this.terminalSymbols = terminalSymbols;
	}
	
	@Override
	public TerminalSymbol next() {
		assertTrue(hasNext());
		TerminalSymbol terminalSymbol = terminalSymbols.get(cursor);
		cursor++;
		return terminalSymbol;
	}

	@Override
	public boolean hasNext() {
		int tokensSize = terminalSymbols.size();
		return cursor < tokensSize;
	}

	@Override
	public void rollBack(int stepSize) {
		cursor -= stepSize;
		
	}

	@Override
	public void skipBlank() {
		
	}

	@Override
	public void reset() {
		cursor = 0;
		
	}

}
