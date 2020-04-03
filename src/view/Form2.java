package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import lexer.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Form2 extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel main_panel;
	private JLabel lb_lexical;
	private JLabel lb_unit;
	private JLabel lb_DFA;
	private JLabel lb_error;
	private JScrollPane scrollpane_unit;
	private JScrollPane scrollpane_DFA;
	private JScrollPane scrollpane_error;
	private JTable tb_unit;
	private JTable tb_DFA;
	private JTable tb_error;
	
	private String[] unitTitle = {"输入项","token序列","类型","行号"};
	private String[][] unitData ;
	private String[] DFATitle = {"内容一","内容二"};
	private String[][] DFAData = {{"1","2"}};
	private String[] errorTitle = {"内容一","内容二","内容三"};
	private String[][] errorData = {{"1","2","3"}};
	
	private String file_name;
	
	
	public Form2(String file_name){
		this.file_name = file_name;
		this.setTitle("Form2");
		this.setSize(1800,900);
		initPanel();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void initPanel() {
		main_panel = new JPanel();
		main_panel.setLayout(null);
		lb_lexical = new JLabel("词法分析");
		main_panel.add(lb_lexical);
		lb_lexical.setBounds(875, 10, 70, 20);
		lb_unit = new JLabel("词法单元");
		main_panel.add(lb_unit);
		lb_unit.setBounds(275, 50, 70, 20);
		lb_DFA = new JLabel("DFA转换图");
		main_panel.add(lb_DFA);
		lb_DFA.setBounds(875, 50, 70, 20);
		lb_error = new JLabel("词法错误");
		main_panel.add(lb_error);
		lb_error.setBounds(1475, 50, 70, 20);
		
		addDFA();
		addUnit();
		
		tb_unit = new JTable(unitData,unitTitle);
//		tb_unit.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		tb_unit.setColumnSelectionAllowed(true);
		scrollpane_unit = new JScrollPane(tb_unit);
		main_panel.add(scrollpane_unit);
		scrollpane_unit.setBounds(10, 100, 580, 750);
		
		tb_DFA = new JTable(DFAData,DFATitle);
		tb_DFA.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb_DFA.setColumnSelectionAllowed(true);
		scrollpane_DFA = new JScrollPane(tb_DFA);
		main_panel.add(scrollpane_DFA);
		scrollpane_DFA.setBounds(610, 100, 580, 750);
		
		tb_error = new JTable(errorData,errorTitle);
//		tb_error.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		tb_error.setColumnSelectionAllowed(true);
		scrollpane_error = new JScrollPane(tb_error);
		main_panel.add(scrollpane_error);
		scrollpane_error.setBounds(1210, 100, 580, 750);
		
		add(main_panel);
	}
	
	//添加DFA转换图数据
	public void addDFA() {
		DFATitle = new String[] {"数据一","内容一","1","2","3","4","5","6","7","8","9","10"};
		DFAData = new String[][] {{"一","二","1","2","3","4","5","6","7","8","9","10"}};
	}
	
	//添加token序列数据
	public void addUnit() {
		LexicalAnalyzer lexicalanalyzer = new LexicalAnalyzer(this.file_name);
	}
	
	//添加错误数据
	public void addError() {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
