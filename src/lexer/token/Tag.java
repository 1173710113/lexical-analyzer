package lexer.token;

public class Tag {

	public final static int AND = 256, BASIC = 257, BREAK = 258, DO = 259, ELSE = 260, EQ = 261, FALSE = 262, GE = 263,
			ID = 264, IF = 265, INDEX = 266, LE = 267, MINUS = 268, NE = 269, NUM = 270, OR = 271, REAL = 272,
			TEMP = 273, TRUE = 274, WHILE = 275, OP = 276, ASOP = 277, CHAR = 278, STRING = 279, NOTE = 280, AUTO = 281,
			CASE = 282, CONST = 283, CONTINUE = 284, DEFAULT = 285, ENUM = 286, EXTERN = 287, FOR = 288, REGISTER = 289,
			RETURN = 290, SIGNED = 291, SIZEOF = 292, STATIC = 293, STRUCT = 294, SWITCH = 295, TYPEDEF = 296,
			UNION = 297, UNSIGNED = 298, VOID = 299, VOLATILE = 300, RECORD = 301, THEN = 302, PROC = 303, CALL = 304,
			GOTO = 305;

	public static String tagToTypeString(int tag) {
		switch (tag) {
		case ID:
			return "标识符";
		case CHAR:
			return "字符常量";
		case STRING:
			return "字符串常量";
		case ASOP:
		case '=':
			return "赋值运算符";
		case OP:
		case '+':
		case '-':
		case '*':
		case '/':
		case '&':
		case '|':
		case '^':
		case '?':
		case '%':
			return "运算符" + (char) tag;
		case NOTE:
			return "注释";
		case NUM:
			return "整型常量";
		case REAL:
			return "浮点型常量";
		case '!':
		case AND:
		case OR:
			return "逻辑运算符";
		case EQ:
		case NE:
		case LE:
		case GE:
		case '>':
		case '<':
			return "比较运算符";
		case '{':
		case '}':
		case '[':
		case ']':
		case '(':
		case ')':
		case ':':
		case ';':
		case ',':
			return "界符" + (char) tag;
		default:
			return "保留字";
		}
	}

	public static String tagToString(int tag) {
		if (tag < 256)
			return String.valueOf((char) tag);
		switch (tag) {
		case AND:
			return "and";
		case BASIC:
			return "basic";
		case BREAK:
			return "break";
		case DO:
			return "do";
		case ELSE:
			return "else";
		case EQ:
			return "==";
		case FALSE:
			return "false";
		case GE:
			return ">=";
		case ID:
			return "id";
		case IF:
			return "if";
		case INDEX:
			return "index";
		case LE:
			return "<=";
		case MINUS:
			return "minus";
		case NE:
			return "!=";
		case NUM:
			return "num";
		case OR:
			return "||";
		case REAL:
			return "real";
		case TEMP:
			return "temp";
		case TRUE:
			return "true";
		case WHILE:
			return "while";
		case OP:
			return "op";
		case ASOP:
			return "asop";
		case CHAR:
			return "char";
		case STRING:
			return "string";
		case NOTE:
			return "note";
		case AUTO:
			return "auto";
		case CASE:
			return "case";
		case CONST:
			return "const";
		case CONTINUE:
			return "continue";
		case DEFAULT:
			return "default";
		case ENUM:
			return "enum";
		case EXTERN:
			return "extern";
		case FOR:
			return "for";
		case REGISTER:
			return "register";
		case RETURN:
			return "return";
		case SIGNED:
			return "signed";
		case SIZEOF:
			return "sizeof";
		case STATIC:
			return "static";
		case STRUCT:
			return "struct";
		case SWITCH:
			return "switch";
		case TYPEDEF:
			return "tpyedef";
		case UNION:
			return "union";
		case UNSIGNED:
			return "unsigned";
		case VOID:
			return "void";
		case VOLATILE:
			return "volatitl";
		case RECORD:
			return "record";
		case THEN:
			return "then";
		case PROC:
			return "proc";
		case CALL:
			return "call";
		case GOTO:
			return "goto";
		default:
			throw new IllegalArgumentException("Unexpected value: " + tag);
		}
	}
}
