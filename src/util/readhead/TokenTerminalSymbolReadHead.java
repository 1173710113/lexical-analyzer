package util.readhead;

import static org.junit.Assert.assertTrue;

import java.util.List;

import grammar.grammarsymbol.TerminalSymbol;
import lexer.token.Tag;
import lexer.token.Token;

public class TokenTerminalSymbolReadHead implements TerminalSymbolReadHead {

	private List<Token> tokens;
	private int cursor = 0;

	public TokenTerminalSymbolReadHead(List<Token> tokens) {
		this.tokens = tokens;
	}

	@Override
	public void skipBlank() {
		while (hasNext() && isNote()) {
			cursor++;
		}
	}

	private boolean isNote() {
		Token token = tokens.get(cursor);
		return token.tag == Tag.NOTE;
	}

	@Override
	public TerminalSymbol next() {
		assertTrue(hasNext());
		Token token = tokens.get(cursor);
		cursor++;
		return token;
	}

	@Override
	public boolean hasNext() {

		int tokensSize = tokens.size();
		return cursor < tokensSize;
	}

	@Override
	public void rollBack(int stepSize) {
		cursor -= stepSize;

	}

	@Override
	public void reset() {
		cursor = 0;
		
	}

}
