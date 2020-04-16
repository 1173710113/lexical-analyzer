package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import exception.dfa.InValidInputException;
import exception.recognize.RecognizeException;

public class GrammarAnalyzeResultForm extends JFrame implements ActionListener{
	
	private JPanel main_panel;
	private JLabel lb_analyze;
	private JLabel lb_grammar;
	private JLabel lb_anatree;
	private JLabel lb_error;
	private JTextArea ta_grammar;
	//private JTextArea ta_anaterr;
	private JTable tb_error; 
	private JScrollPane sp_grammar;
	private JScrollPane sp_error;
	private JFrame frame_tree;
	private JTree tree;
    private DefaultMutableTreeNode root;
	
	private String[] errortitle = {"错误项","错误原因","行数"};
	private String[][] errordata = {};
	
	private List<String> inputs;
	
	public GrammarAnalyzeResultForm(List<String> inputs) throws FileNotFoundException, RecognizeException, InValidInputException {
		this.inputs = inputs;
		this.setTitle("语法分析");
		this.setSize(1700, 800);
		initPanel();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void initPanel() throws FileNotFoundException, RecognizeException, InValidInputException{
		main_panel = new JPanel();
		main_panel.setLayout(null);
		lb_analyze = new JLabel("语法分析");
		main_panel.add(lb_analyze);
		lb_analyze.setBounds(800, 10, 70, 20);
		lb_grammar = new JLabel("文法");
		main_panel.add(lb_grammar);
		lb_grammar.setBounds(250, 50, 150, 20);
		lb_anatree = new JLabel("语法分析树");
		main_panel.add(lb_anatree);
		lb_anatree.setBounds(800, 50, 70, 20);
		lb_error = new JLabel("语法错误");
		main_panel.add(lb_error);
		lb_error.setBounds(1350, 50, 70, 20);
		
		ta_grammar = new JTextArea();
		sp_grammar = new JScrollPane(ta_grammar);
		main_panel.add(sp_grammar);
		sp_grammar.setBounds(50, 100, 500, 600);
		
		tb_error = new JTable(errordata,errortitle);
		tb_error.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb_error.setColumnSelectionAllowed(true);
		sp_error = new JScrollPane(tb_error);
		main_panel.add(sp_error);
		sp_error.setBounds(1150, 100, 500, 600);
		
		tb_error.getColumnModel().getColumn(0).setPreferredWidth(140);
		tb_error.getColumnModel().getColumn(1).setPreferredWidth(300);
		tb_error.getColumnModel().getColumn(2).setPreferredWidth(60);
		
		addTree();
		
		DefaultTreeCellRenderer render=(DefaultTreeCellRenderer)(tree.getCellRenderer());
		render.setLeafIcon(null);	//设置图标为空
		render.setClosedIcon(null);
		render.setOpenIcon(null);
		expandAll(tree, new TreePath(root), true);		//默认全展开
		JScrollPane sp_tree = new JScrollPane(tree);
		main_panel.add(sp_tree);
		sp_tree.setBounds(600, 100, 500, 600);
		
		add(main_panel);
	}
	
	//添加文法
	private void addGrammar() {
		
	}
	
	//添加语法错误数据
	private void addErrorData() {
		
	}
	
	//添加分析树数据
	private void addTree() {
		root = new DefaultMutableTreeNode("中国");
		DefaultMutableTreeNode guangdong = new DefaultMutableTreeNode("广东");
		DefaultMutableTreeNode guangxi = new DefaultMutableTreeNode("广西");
		DefaultMutableTreeNode foshan = new DefaultMutableTreeNode("佛山");
		DefaultMutableTreeNode shantou = new DefaultMutableTreeNode("汕头");
		DefaultMutableTreeNode guilin = new DefaultMutableTreeNode("桂林");
		DefaultMutableTreeNode nanning = new DefaultMutableTreeNode("南宁");
	    // 通过add()方法建立树节点之间的父子关系
	    guangdong.add(foshan);
	    guangdong.add(shantou);
	    guangxi.add(guilin);
	    guangxi.add(nanning);
	    root.add(guangdong);
	    root.add(guangxi);
		this.tree = new JTree(root);
	}
	
	private void expandAll(JTree tree, TreePath parent, boolean expand) {
	     TreeNode node = (TreeNode) parent.getLastPathComponent();
	     if (node.getChildCount() > 0) {
	    	 for (Enumeration e = node.children(); e.hasMoreElements();) {
	    		 TreeNode n = (TreeNode) e.nextElement();
	    		 TreePath path = parent.pathByAddingChild(n);
	    		 expandAll(tree, path, expand);
	    	 }
	     }
	     if (expand) {
	    	 tree.expandPath(parent);
	     } else {
	    	 tree.collapsePath(parent);
	     }
	} 

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
	}

}
