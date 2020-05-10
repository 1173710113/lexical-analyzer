package grammar.grammarsymbol;

public interface TerminalSymbol extends GrammarSymbol{
	
	public boolean hasLexeme();
	
	public Object getLexeme();
	
	public String getName();
}
