package grammar.production;

import java.util.List;

import grammar.grammarsymbol.GrammarSymbol;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;

public class LookAheadProductionItem extends ProductionItem{
	
	private TerminalSymbol lookahead;

	public LookAheadProductionItem(NonterminalSymbol nonterminalSymbol, List<GrammarSymbol> grammarSymbolsBeforeDot,
			List<GrammarSymbol> grammarSymbolsAfterDot) {
		super(nonterminalSymbol, grammarSymbolsBeforeDot, grammarSymbolsAfterDot);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the lookahead
	 */
	public TerminalSymbol getLookahead() {
		return lookahead;
	}

	/**
	 * @param lookahead the lookahead to set
	 */
	public void setLookahead(TerminalSymbol lookahead) {
		this.lookahead = lookahead;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((lookahead == null) ? 0 : lookahead.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LookAheadProductionItem other = (LookAheadProductionItem) obj;
		if (lookahead == null) {
			if (other.lookahead != null)
				return false;
		} else if (!lookahead.equals(other.lookahead))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + "lookahead:" + lookahead;
	}
	
	

}
