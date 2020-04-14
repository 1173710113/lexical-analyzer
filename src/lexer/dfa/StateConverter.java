package lexer.dfa;

import lexer.token.Token;

public interface StateConverter {

	public Token stateConverToToken(String state, String str);
}
