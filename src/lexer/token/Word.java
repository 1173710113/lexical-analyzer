package lexer.token;

/**
 * 保留字，标识符，符合语法词素
 * 
 * @author msi-user
 *
 */
public class Word extends Token {

	public String lexeme = "";

	public Word(String s, int tag) {
		super(tag);
		lexeme = s;
	}

	@Override
	public String toString() {
		return "<" + Tag.tagToString(tag) + "," + lexeme + ">";
	}

	@Override
	public boolean hasLexeme() {
		switch (tag) {
		case Tag.EQ:
		case Tag.NE:
		case Tag.LE:
		case Tag.GE:
		case Tag.ID:
			return true;
		default:
			return false;
		}
	}

	@Override
	public Object getLexeme() {
		if (hasLexeme())
			return lexeme;
		return null;
	}

	public static final Word and = new Word("&&", Tag.AND), or = new Word("||", Tag.OR), eq = new Word("==", Tag.EQ),
			ne = new Word("!=", Tag.NE), le = new Word("<=", Tag.LE), ge = new Word(">=", Tag.GE),
			minus = new Word("minus", Tag.MINUS), True = new Word("true", Tag.TRUE),
			False = new Word("false", Tag.FALSE), temp = new Word("t", Tag.TEMP);
}
