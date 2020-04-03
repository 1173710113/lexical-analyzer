package token;

public class Char extends Token{

	public final Character value;
	
	public Char(Character c) {
		super(Tag.CHAR);
		value = c;
	}
	
	@Override
	public String toString() {
		return "<" + tag + "," + value + ">";
	}

}
