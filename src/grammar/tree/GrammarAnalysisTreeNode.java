package grammar.tree;

import java.util.ArrayList;
import java.util.List;

import grammar.grammarsymbol.GrammarSymbol;

public class GrammarAnalysisTreeNode {

	private GrammarSymbol node;
	private List<GrammarAnalysisTreeNode> childNodes;
	
	public GrammarAnalysisTreeNode(GrammarSymbol node) {
		this.node = node;
		this.childNodes = new ArrayList<>();
	}
	
	public void addChildNode(GrammarSymbol childNode) {
		childNodes.add(new GrammarAnalysisTreeNode(childNode));
	}
	
	public void addChildNodeList(List<GrammarSymbol> childNodes) {
		for(GrammarSymbol childNode : childNodes) {
			this.childNodes.add(new GrammarAnalysisTreeNode(childNode));
		}
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(node.toString() + "\n");
		for(GrammarAnalysisTreeNode grammarAnalysisTreeNode : childNodes) {
			stringBuilder.append("--" + grammarAnalysisTreeNode.toString());
		}
		return stringBuilder.toString();
	}

	/**
	 * @return the node
	 */
	public GrammarSymbol getNode() {
		return node;
	}

	/**
	 * @return the childNodes
	 */
	public List<GrammarAnalysisTreeNode> getChildNodes() {
		return childNodes;
	}

	/**
	 * @param node the node to set
	 */
	public void setNode(GrammarSymbol node) {
		this.node = node;
	}

	/**
	 * @param childNodes the childNodes to set
	 */
	public void setChildNodes(List<GrammarAnalysisTreeNode> childNodes) {
		this.childNodes = childNodes;
	}
	
	
	
	
}
