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
	
	private String[] errortitle = {"������","����ԭ��","����"};
	private String[][] errordata = {};
	
	private List<String> inputs;
	
	public GrammarAnalyzeResultForm(List<String> inputs) throws FileNotFoundException, RecognizeException, InValidInputException {
		this.inputs = inputs;
		this.setTitle("�﷨����");
		this.setSize(1700, 800);
		initPanel();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void initPanel() throws FileNotFoundException, RecognizeException, InValidInputException{
		main_panel = new JPanel();
		main_panel.setLayout(null);
		lb_analyze = new JLabel("�﷨����");
		main_panel.add(lb_analyze);
		lb_analyze.setBounds(800, 10, 70, 20);
		lb_grammar = new JLabel("�ķ�");
		main_panel.add(lb_grammar);
		lb_grammar.setBounds(250, 50, 150, 20);
		lb_anatree = new JLabel("�﷨������");
		main_panel.add(lb_anatree);
		lb_anatree.setBounds(800, 50, 70, 20);
		lb_error = new JLabel("�﷨����");
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
		render.setLeafIcon(null);	//����ͼ��Ϊ��
		render.setClosedIcon(null);
		render.setOpenIcon(null);
		expandAll(tree, new TreePath(root), true);		//Ĭ��ȫչ��
		JScrollPane sp_tree = new JScrollPane(tree);
		main_panel.add(sp_tree);
		sp_tree.setBounds(600, 100, 500, 600);
		
		add(main_panel);
	}
	
	//����ķ�
	private void addGrammar() {
		
	}
	
	//����﷨��������
	private void addErrorData() {
		
	}
	
	//��ӷ���������
	private void addTree() {
		root = new DefaultMutableTreeNode("�й�");
		DefaultMutableTreeNode guangdong = new DefaultMutableTreeNode("�㶫");
		DefaultMutableTreeNode guangxi = new DefaultMutableTreeNode("����");
		DefaultMutableTreeNode foshan = new DefaultMutableTreeNode("��ɽ");
		DefaultMutableTreeNode shantou = new DefaultMutableTreeNode("��ͷ");
		DefaultMutableTreeNode guilin = new DefaultMutableTreeNode("����");
		DefaultMutableTreeNode nanning = new DefaultMutableTreeNode("����");
	    // ͨ��add()�����������ڵ�֮��ĸ��ӹ�ϵ
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
		// TODO �Զ����ɵķ������
		
	}

}
