package dfa;

import token.Char;
import token.Num;
import token.Real;
import token.Tag;
import token.Token;
import token.Type;
import token.Word;

public class StateConverterImp implements StateConverter{
	
	@Override
	public Token stateConverToToken(String state, String str) {
		switch (state) {
		case "1":
		case "2":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case "10":
		case "12":
			return new Token(str.charAt(0));
		case "3":
			switch(str) {
			case "++":
			case "--":
				return new Word(str, Tag.OP);
			case "&&":
				return Word.and;
			case "||":
				return Word.or;
			}
		case "4":
			switch(str) {
			case "==":
				return Word.eq;
			case "!=":
				return Word.ne;
			case ">=":
				return Word.ge;
			case "<=":
				return Word.le;
			case "?":
				return new Token('?');
			}
		case "11":
			return new Word(str, Tag.ASOP);
		case "13":
		case "17":
			return new Num(Integer.parseInt(str));
		case "19":
		case "22":
			return new Real(Float.parseFloat(str));
		case "14":
			return new Num(Integer.parseInt(str.substring(1), 8));
		case "16":
			return new Num(Integer.parseInt(str.substring(2), 16));
		case "23":
			return isReservedWord(str);
		case "24":
			return new Word(str, Tag.ID);
		case "27":
			return new Char(str.charAt(1));
		case "29":
			return new Word(str, Tag.STRING);
		case "32":
			return new Word(str, Tag.NOTE);
		default:
			throw new IllegalArgumentException("Unexpected value: " + state);
		}
	}
	
	private Token isReservedWord(String str) {
		switch (str) {
		case "auto":
			return new Word(str, Tag.AUTO);
		case "break":
			return new Word(str, Tag.BREAK);
		case "case":
			return new Word(str, Tag.CASE);
		case "char":
			return Type.Char;
		case "const":
			return new Word(str, Tag.CONST);
		case "continue":
			return new Word(str, Tag.CONTINUE);
		case "default":
			return new Word(str, Tag.DEFAULT);
		case "do":
			return new Word(str, Tag.DO);
		case "double":
			return Type.Double;
		case "else":
			return new Word(str, Tag.ELSE);
		case "enum":
			return new Word(str, Tag.ENUM);
		case "extern":
			return new Word(str, Tag.EXTERN);
		case "float":
			return Type.Float;
		case "for":
			return new Word(str, Tag.FOR);
		case "goto":
			return new Word(str,Tag.GOTO);
		case "if":
			return new Word(str, Tag.IF);
		case "int":
			return Type.Int;
		case "long":
			return Type.Long;
		case "register":
			return new Word(str, Tag.REGISTER);
		case "return":
			return new Word(str, Tag.RETURN);
		case "short":
			return Type.Short;
		case "signed":
			return new Word(str, Tag.SIGNED);
		case "sizeof":
			return new Word(str, Tag.SIZEOF);
		case "static":
			return new Word(str, Tag.STATIC);
		case "struct":
			return new Word(str, Tag.STRUCT);
		case "switch":
			return new Word(str, Tag.SWITCH);
		case "typedef":
			return new Word(str, Tag.TYPEDEF);
		case "union":
			return new Word(str, Tag.UNION);
		case "unsigned":
			return new Word(str, Tag.UNSIGNED);
		case "void":
			return new Word(str, Tag.VOID);
		case "volatile":
			return new Word(str, Tag.VOLATILE);
		case "while":
			return new Word(str, Tag.WHILE);
		case "true":
			return Word.True;
		case "false":
			return Word.False;
		default:
			return new Word(str, Tag.ID);
		}
	}

}
