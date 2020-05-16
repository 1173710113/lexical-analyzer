package sdt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import exception.sdt.DuplicateIdentifierItemException;
import exception.sdt.NoIdentifierTableItemException;
import exception.sdt.SDTException;
import grammar.grammarsymbol.EmptyTerminalSymbol;
import grammar.grammarsymbol.GrammarSymbol;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.NonterminalSymbolSyn;
import grammar.grammarsymbol.TerminalSymbol;
import lexer.token.Type;
import sdt.action.Action;
import sdt.identifiertable.IdentifierTable;
import sdt.identifiertable.IdentifierTableItem;

public class SDTAnalyzerState {
	private int offset;
	private IdentifierTable identifierTable;
	private Map<String, Object> tempVariantMap;
	private Stack<SDTStackItem> stack;
	private int tempCount;
	private List<String> genList;
	private List<SDTException> exceptions;
	private List<Quadruple> quadruples;

	public SDTAnalyzerState() {
		this.offset = 0;
		this.identifierTable = new IdentifierTable();
		this.tempVariantMap = new HashMap<String, Object>();
		this.stack = new Stack<SDTStackItem>();
		this.tempCount = 0;
		this.genList = new ArrayList<String>();
		this.exceptions = new ArrayList<SDTException>();
		this.quadruples = new ArrayList<Quadruple>();
	}

	public GrammarSymbol peek() {
		int stackSize = stack.size();
		for (int i = stackSize - 1; i >= 0; i--) {
			if (stack.get(i).getGrammarSymbol() instanceof TerminalSymbol
					|| stack.get(i).getGrammarSymbol() instanceof NonterminalSymbol) {
				return stack.get(i).getGrammarSymbol();
			}
		}
		return null;
	}

	public void push(GrammarSymbol grammarSymbol) {
		if (grammarSymbol instanceof NonterminalSymbol) {
			NonterminalSymbol nonterminalSymbol = (NonterminalSymbol) grammarSymbol;
			stack.push(new SDTStackItem(new NonterminalSymbolSyn(nonterminalSymbol)));
		}
		stack.push(new SDTStackItem(grammarSymbol));
	}

	public GrammarSymbol pop() throws SDTException {
		if (!stack.isEmpty()) {
			SDTStackItem sdtStackItem = stack.peek();
			GrammarSymbol grammarSymbol = sdtStackItem.getGrammarSymbol();
			if (grammarSymbol instanceof EmptyTerminalSymbol) {
				stack.pop();
			} else if (grammarSymbol instanceof TerminalSymbol) {
				TerminalSymbol terminalSymbol = (TerminalSymbol) sdtStackItem.getGrammarSymbol();
				if (terminalSymbol.hasLexeme()) {
					copyAttributeToAction(terminalSymbol.getName() + ".lexeme", terminalSymbol.getLexeme());
				}
				stack.pop();
			} else if (grammarSymbol instanceof NonterminalSymbol) {
				if (!sdtStackItem.getValueMap().isEmpty()) {
					copyAttributeToAction(sdtStackItem.getValueMap());
				}
				stack.pop();
			} else if (grammarSymbol instanceof NonterminalSymbolSyn) {
				if (!sdtStackItem.getValueMap().isEmpty()) {
					copyAttributeToAction(sdtStackItem.getValueMap());
				}
				stack.pop();
				grammarSymbol = pop();
			} else if (grammarSymbol instanceof Action) {
				((Action) sdtStackItem.getGrammarSymbol()).execute(this);
				stack.pop();
				grammarSymbol = pop();
			}
			return grammarSymbol;
		} else {
			return null;
		}
	}

	public void setLexeme(TerminalSymbol terminalSymbol) {
		int stackSize = stack.size();
		for (int i = stackSize - 1; i >= 0; i--) {
			if (stack.get(i).getGrammarSymbol() instanceof TerminalSymbol) {
				stack.get(i).setGrammarSymbol(terminalSymbol);
				return;
			}
		}
	}

	private void copyAttributeToAction(Map<String, Object> src) {
		SDTStackItem sdtStackItem = findNearestAction();
		if (sdtStackItem != null) {
			for (Map.Entry<String, Object> entry : src.entrySet()) {
				sdtStackItem.addValue(entry.getKey(), entry.getValue());
			}
		}
	}

	private void copyAttributeToAction(String key, Object value) {
		SDTStackItem sdtStackItem = findNearestAction();
		if (sdtStackItem != null) {
			sdtStackItem.addValue(key, value);
			;
		}
	}

	private SDTStackItem findNearestAction() {
		int stackSize = stack.size();
		for (int i = stackSize - 1; i >= 0; i--) {
			SDTStackItem item = stack.get(i);
			if (item.getGrammarSymbol() instanceof Action) {
				return item;
			}
		}
		return null;
	}

	public boolean isStackEmpty() throws SDTException {
		if (peek() == null) {
			pop();
		}
		return peek() == null;
	}

	public void addTempVariant(String key, Object value) {
		tempVariantMap.put(key, value);
	}

	public Object findTemVariant(String key) {
		return tempVariantMap.get(key);
	}

	public void addSymbol(String id, Type type, int offset) throws DuplicateIdentifierItemException {
		IdentifierTableItem item = new IdentifierTableItem(id, type, offset);
		identifierTable.addItem(item);
	}

	public IdentifierTableItem findSymbol(String id) throws NoIdentifierTableItemException {
		return identifierTable.findItem(id);
	}

	public void addOffset(int increasement) {
		offset += increasement;
	}

	public String newTemp() {
		tempCount++;
		return "t" + tempCount;
	}

	public SDTStackItem getFromTop(int decrease) {
		return stack.get(stack.size() - 1 + decrease);
	}

	public int nextQuad() {
		return genList.size();
	}

	public void gen(String triple) {
		System.out.println(genList.size() + ":" + triple);
		genList.add(triple);
	}

	public void backPatch(List<Integer> list, Integer quad) {
		if (list == null)
			return;
		for (int num : list) {
			genList.set(num, genList.get(num).concat(quad.toString()));
			quadruples.get(num).setResult(quad);
			System.out.println(genList.get(num));
			System.out.println(quadruples.get(num));
		}
	}

	public List<Integer> merge(List<Integer> list1, List<Integer> list2) {
		List<Integer> dst = new ArrayList<Integer>();
		if (list1 != null)
			dst.addAll(list1);
		if (list2 != null)
			dst.addAll(list2);
		return dst;
	}
	
	public void addSDTException(SDTException e) {
		exceptions.add(e);
		System.out.println(e);
	}
	
	public void addQuadruple(Object op, Object arg1, Object arg2, Object result) {
		Quadruple quadruple = new Quadruple(op, arg1, arg2, result);
		System.out.println(quadruple);
		quadruples.add(quadruple);
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @return the identifierTable
	 */
	public IdentifierTable getIdentifierTable() {
		return identifierTable;
	}

	/**
	 * @return the tempVariantMap
	 */
	public Map<String, Object> getTempVariantMap() {
		return tempVariantMap;
	}

	/**
	 * @return the stack
	 */
	public Stack<SDTStackItem> getStack() {
		return stack;
	}

	/**
	 * @return the tempCount
	 */
	public int getTempCount() {
		return tempCount;
	}

	/**
	 * @return the genList
	 */
	public List<String> getGenList() {
		return genList;
	}

	/**
	 * @return the exceptions
	 */
	public List<SDTException> getExceptions() {
		return exceptions;
	}

	/**
	 * @return the quadruples
	 */
	public List<Quadruple> getQuadruples() {
		return quadruples;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @param identifierTable the identifierTable to set
	 */
	public void setIdentifierTable(IdentifierTable identifierTable) {
		this.identifierTable = identifierTable;
	}

	/**
	 * @param tempVariantMap the tempVariantMap to set
	 */
	public void setTempVariantMap(Map<String, Object> tempVariantMap) {
		this.tempVariantMap = tempVariantMap;
	}

	/**
	 * @param stack the stack to set
	 */
	public void setStack(Stack<SDTStackItem> stack) {
		this.stack = stack;
	}

	/**
	 * @param tempCount the tempCount to set
	 */
	public void setTempCount(int tempCount) {
		this.tempCount = tempCount;
	}

	/**
	 * @param genList the genList to set
	 */
	public void setGenList(List<String> genList) {
		this.genList = genList;
	}

	/**
	 * @param exceptions the exceptions to set
	 */
	public void setExceptions(List<SDTException> exceptions) {
		this.exceptions = exceptions;
	}

	/**
	 * @param quadruples the quadruples to set
	 */
	public void setQuadruples(List<Quadruple> quadruples) {
		this.quadruples = quadruples;
	}

	

}
