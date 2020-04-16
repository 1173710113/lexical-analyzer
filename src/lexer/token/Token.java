package lexer.token;

import grammar.grammarsymbol.TerminalSymbol;

public class Token implements TerminalSymbol{

	public final int tag;
	
	public Token(int t) {
		tag = t;
	}
	
	@Override
	public String toString() {
		return "<" + Tag.tagToString(tag) + ",_>";
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Token) {
			Token token = (Token)object;
			if(token.tag == tag) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return tag;
	}
}
