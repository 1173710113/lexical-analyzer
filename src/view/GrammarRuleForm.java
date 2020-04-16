package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import exception.dfa.InValidInputException;
import exception.recognize.RecognizeException;

public class GrammarRuleForm extends JFrame implements ActionListener{
	
	private JPanel main_panel;
	private JLabel lb_grammar;
	private JLabel lb_fandfset;
	private JLabel lb_selectset;
	private JLabel lb_anatable;
	private JTable tb_fandfset;
	private JTable tb_selectset;
	private JTable tb_anatable;
	private JScrollPane sp_fandfset;
	private JScrollPane sp_selectset;
	private JScrollPane sp_anatable;
	
	private String[] fandftitle = {"����ʽ","first��","follow��"};
	private String[][] fandfdata = {};
	private String[] selecttitle = {"����ʽ","select��"};
	private String[][] selectdata = {};
	private String[] anatitle = {"1","2","3"};
	private String[][] anadata = {};
	
	public GrammarRuleForm() throws FileNotFoundException, RecognizeException, InValidInputException {
		this.setTitle("�﷨����");
		this.setSize(1700, 800);
		initPanel();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void initPanel() {
		main_panel = new JPanel();
		main_panel.setLayout(null);
		lb_grammar = new JLabel("�ķ�����");
		main_panel.add(lb_grammar);
		lb_grammar.setBounds(800, 10, 70, 20);
		lb_fandfset = new JLabel("first����follow��");
		main_panel.add(lb_fandfset);
		lb_fandfset.setBounds(250, 50, 150, 20);
		lb_selectset = new JLabel("select��");
		main_panel.add(lb_selectset);
		lb_selectset.setBounds(800, 50, 70, 20);
		lb_anatable = new JLabel("Ԥ�������");
		main_panel.add(lb_anatable);
		lb_anatable.setBounds(1350, 50, 70, 20);
		
		tb_fandfset = new JTable(fandfdata,fandftitle);
		tb_fandfset.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb_fandfset.setColumnSelectionAllowed(true);
		sp_fandfset = new JScrollPane(tb_fandfset);
		main_panel.add(sp_fandfset);
		sp_fandfset.setBounds(50, 100, 500, 600);
		
		tb_selectset = new JTable(selectdata,selecttitle);
		tb_selectset.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb_selectset.setColumnSelectionAllowed(true);
		sp_selectset = new JScrollPane(tb_selectset);
		main_panel.add(sp_selectset);
		sp_selectset.setBounds(600, 100, 500, 600);
		
		tb_anatable = new JTable(anadata,anatitle);
		tb_anatable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb_anatable.setColumnSelectionAllowed(true);
		sp_anatable = new JScrollPane(tb_anatable);
		main_panel.add(sp_anatable);
		sp_anatable.setBounds(1150, 100, 500, 600);
		
		addFandfData();
		addSelectData();
		addAnaTitle();
		addAnaData();
		
		tb_fandfset.getColumnModel().getColumn(0).setPreferredWidth(150);
		tb_fandfset.getColumnModel().getColumn(1).setPreferredWidth(700);
		tb_fandfset.getColumnModel().getColumn(2).setPreferredWidth(700);
		
		tb_selectset.getColumnModel().getColumn(0).setPreferredWidth(150);
		tb_selectset.getColumnModel().getColumn(1).setPreferredWidth(700);
		
		for(int i=0;i<anatitle.length;i++) {
			tb_anatable.getColumnModel().getColumn(i).setPreferredWidth(120);
		}
		
		add(main_panel);
	}
	
	//���first����follow������
	private void addFandfData() {
		
	}
	
	//���select������
	private void addSelectData() {
		
	}
	
	//���Ԥ��������ͷ����
	private void addAnaTitle() {
		
	}
	
	//���Ԥ�����������
	private void addAnaData() {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

}
