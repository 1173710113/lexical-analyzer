package token;

import token.Tag;
import token.Word;

/**
 * ÀàÐÍ¹Ø¼ü×Öint,float, bool, char
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

	public static final 
		Type Int = new Type("int", Tag.BASIC, 4), 
			Long = new Type("long", Tag.BASIC, 8),
			 Short = new Type("short", Tag.BASIC, 2),
			 Float = new Type("float", Tag.BASIC, 4), 
			 Double = new Type("double", Tag.BASIC, 8),
			 Char = new Type("char", Tag.BASIC, 1), 
			 Bool = new Type("bool", Tag.BASIC, 1);

}
