package lexer.token.errorToken;

public class ErrorToken {

	private String content;
	
	public ErrorToken(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return content;
	}
}
