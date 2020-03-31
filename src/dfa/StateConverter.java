package dfa;

import token.Token;

public interface StateConverter {

	public Token stateConverToToken(String state, String str);
}
