package lexer.token;

public class Token {

	public final int tag;
	
	public Token(int t) {
		tag = t;
	}
	
	@Override
	public String toString() {
		return "<" + (char)tag + ",_>";
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
