package lexer.token;

public class Tag {

	public final static int AND = 256, BASIC = 257, BREAK = 258, DO = 259, ELSE = 260, EQ = 261, FALSE = 262, GE = 263,
			ID = 264, IF = 265, INDEX = 266, LE = 267, MINUS = 268, NE = 269, NUM = 270, OR = 271, REAL = 272,
			TEMP = 273, TRUE = 274, WHILE = 275, OP = 276, ASOP = 277, CHAR = 278, STRING = 279, NOTE = 280, AUTO = 281,
			CASE = 282, CONST = 283, CONTINUE = 284, DEFAULT = 285, ENUM = 286, EXTERN = 287, FOR = 288, GOTO = 288,
			REGISTER = 289, RETURN = 290, SIGNED = 291, SIZEOF = 292, STATIC = 293, STRUCT = 294, SWITCH = 295,
			TYPEDEF = 296, UNION = 297, UNSIGNED = 298, VOID = 299, VOLATILE = 300;

	public static String tagToString(int tag) {
		switch (tag) {
		case ID:
			return "��ʶ��";
		case CHAR:
			return "�ַ�����";
		case STRING:
			return "�ַ�������";
		case ASOP:
		case '=':
			return "��ֵ�����";
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
			return "�����";
		case NOTE:
			return "ע��";
		case NUM:
			return "���ͳ���";
		case REAL:
			return "�����ͳ���";
		case '!':
		case AND:
		case OR:
			return "�߼������";
		case EQ:
		case NE:
		case LE:
		case GE:
		case '>':
		case '<':
			return "�Ƚ������";
		case '{':
		case '}':
		case '[':
		case ']':
		case '(':
		case ')':
		case ':':
		case ';':
		case ',':
			return "���";
		default:
			return "������";
		}
	}
}
