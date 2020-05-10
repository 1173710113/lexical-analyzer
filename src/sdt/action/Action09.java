package sdt.action;

import java.util.Map;
import java.util.Stack;

import grammar.grammarsymbol.GrammarSymbol;
import lexer.token.Tag;
import lexer.token.Type;
import sdt.SDTAnalyzerState;
import sdt.SDTStackItem;

public class Action09 extends BaseAction{

	public Action09(String description) {
		super(description);
	}
	
	@Override
	public void execute(SDTAnalyzerState sdtAnalyzer) {
		Stack<SDTStackItem> stack = sdtAnalyzer.getStack();
		SDTStackItem actionItem = stack.peek();
		Map<String, Object> actionItemValueMap = actionItem.getValueMap();
		Type type = (Type)actionItemValueMap.get(Tag.tagToString(Tag.BASIC) + ".lexeme");
		SDTStackItem targetItem = stack.get(stack.size() - 2);
		GrammarSymbol targetGrammarSymbol = targetItem.getGrammarSymbol();
		Map<String, Object> targetItemValueMap = targetItem.getValueMap();
		targetItemValueMap.put(targetGrammarSymbol.toString() + ".type", type);
		targetItemValueMap.put(targetGrammarSymbol.toString() + ".width", type.width);
	}

}
