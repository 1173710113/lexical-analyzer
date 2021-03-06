package lexer.token;

/**
 * ����
 * @author msi-user
 *
 */
public class Num extends Token{
	
	public final int value;
	
	public Num(int v) {
		super(Tag.NUM);
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
	public Integer getLexeme() {
		return value;
	}
}
