package lexer.token;

/**
 * ������
 * 
 * @author msi-user
 *
 */
public class Real extends Token {

	public final float value;

	public Real(float v) {
		super(Tag.REAL);
		value = v;
	}

	@Override
	public String toString() {
		return "<" + Tag.tagToString(tag) + "," + value + ">";
	}

	@Override
	public boolean hasLexeme() {
		return true;
	}

	@Override
	public Float getLexeme() {
		return value;
	}
}
