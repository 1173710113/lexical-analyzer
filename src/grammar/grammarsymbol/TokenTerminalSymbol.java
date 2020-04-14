package grammar.grammarsymbol;

import lexer.token.Token;

public class TokenTerminalSymbol implements TerminalSymbol{

	private Token token;

	/**
	 * @param token
	 */
	public TokenTerminalSymbol(Token token) {
		super();
		this.token = token;
	}

	/**
	 * @return the token
	 */
	public Token getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(Token token) {
		this.token = token;
	}
	
	
}
