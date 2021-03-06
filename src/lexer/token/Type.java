package lexer.token;

import lexer.token.Tag;
import lexer.token.Word;

/**
 * ���͹ؼ���int,float, bool, char
 * 
 * @author msi-user
 *
 */
public class Type extends Word {

	public int width = 0;

	public Type(String s, int tag, int w) {
		super(s, tag);
		width = w;
	}

	@Override
	public boolean hasLexeme() {
		return true;
	}

	@Override
	public Object getLexeme() {
		return this;
	}

	public static final Type Int = new Type("int", Tag.BASIC, 4), Long = new Type("long", Tag.BASIC, 8),
			Short = new Type("short", Tag.BASIC, 2), Float = new Type("float", Tag.BASIC, 4),
			Double = new Type("double", Tag.BASIC, 8), Char = new Type("char", Tag.BASIC, 1),
			Bool = new Type("bool", Tag.BASIC, 1), PROC = new Type("proc", Tag.PROC, 0),
			RECORD = new Type("record", Tag.RECORD, 0);

}
