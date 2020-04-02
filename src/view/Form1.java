package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class Form1 extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel main_panel;
	private JLabel lb_lexical;
	private JLabel lb_regulation;
	private JLabel lb_DFA;
	private JTextArea ta_regulation;
	private JScrollPane scrollpane_regulation;
	private JScrollPane scrollpane_DFA;
	private JTable tb_DFA;
	
	private String[] DFATitle;
	private String[][] DFAData;
	
	public Form1(){
		this.setTitle("Form1");
		this.setSize(1500,800);
		initPanel();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void initPanel() {
		main_panel = new JPanel();
		main_panel.setLayout(null);
		lb_lexical = new JLabel("词法规则");
		main_panel.add(lb_lexical);
		lb_lexical.setBounds(715, 10, 70, 20);
		lb_regulation = new JLabel("词法规则");
		main_panel.add(lb_regulation);
		lb_regulation.setBounds(350, 50, 70, 20);
		lb_DFA = new JLabel("DFA转换表");
		main_panel.add(lb_DFA);
		lb_DFA.setBounds(1100, 50, 70, 20);
		
		ta_regulation = new JTextArea();
		scrollpane_regulation = new JScrollPane(ta_regulation);
		main_panel.add(scrollpane_regulation);
		scrollpane_regulation.setBounds(40, 100, 675, 600);
		
		addDFA();
		
		tb_DFA = new JTable(DFAData,DFATitle);
		tb_DFA.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb_DFA.setColumnSelectionAllowed(true);
		scrollpane_DFA = new JScrollPane(tb_DFA);
		main_panel.add(scrollpane_DFA);
		scrollpane_DFA.setBounds(770, 100, 675, 600);
		
		add(main_panel);
	}
	
	public void addDFA() {
		DFATitle = new String[] {"数据","内容","1","2","3","4","5","6","7","8","9","10"};
		DFAData = new String[][] {{"编号一","数据一","1","2","3","4","5","6","7","8","9","10"}};
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
