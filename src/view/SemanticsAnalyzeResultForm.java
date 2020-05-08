package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.sun.corba.se.spi.orb.StringPair;

import exception.dfa.InValidInputException;
import exception.recognize.RecognizeException;

public class SemanticsAnalyzeResultForm extends JFrame implements ActionListener{
	
	private JPanel main_panel;
	private JLabel lb_analysis;
	private JLabel lb_address;
	private JLabel lb_symbol;
	private JLabel lb_error;
	private JScrollPane scrollpane_address,scrollpane_symbol,scrollpane_error;
	private JTable tb_address,tb_symbol,tb_error;

	private String[] addressTitle = {"���","����ַָ��","��Ԫʽ","�к�"};
	private String[][] addressData={};	
	private String[] symboleTitle = {"��ʶ��","����","ƫ����","�к�"};
	private String[][] symbolData = {};
	private String[] errorTitle = {"������","����ԭ��","�к�"};
	private String[][] errorData = {};
	
	private List<String> inputs;

	public SemanticsAnalyzeResultForm(List<String> inputs) throws FileNotFoundException, RecognizeException, InValidInputException {
		this.inputs = inputs;
		this.setTitle("�������");
		this.setSize(1700, 800);
		initPanel();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void initPanel() throws FileNotFoundException, RecognizeException, InValidInputException {
		main_panel = new JPanel();
		main_panel.setLayout(null);
		lb_analysis = new JLabel("�������");
		main_panel.add(lb_analysis);
		lb_analysis.setBounds(800, 10, 70, 20);
		
		lb_address = new JLabel("����ַָ�����Ԫʽ");
		main_panel.add(lb_address);
		lb_address.setBounds(250, 50, 150, 20);
		
		lb_symbol = new JLabel("���ű�");
		main_panel.add(lb_symbol);
		lb_symbol.setBounds(800, 50, 70, 20);
		
		lb_error = new JLabel("�������");
		main_panel.add(lb_error);
		lb_error.setBounds(1350, 50, 70, 20);
		
		tb_address = new JTable(addressData,addressTitle);
		tb_address.setColumnSelectionAllowed(true);
		tb_address.setEnabled(false);
		scrollpane_address = new JScrollPane(tb_address);
		main_panel.add(scrollpane_address);
		scrollpane_address.setBounds(50, 100, 500, 600);
		
		tb_symbol = new JTable(symbolData,symboleTitle);
		tb_symbol.setColumnSelectionAllowed(true);
		tb_symbol.setEnabled(false);
		scrollpane_symbol = new JScrollPane(tb_symbol);
		main_panel.add(scrollpane_symbol);
		scrollpane_symbol.setBounds(600, 100, 500, 600);
		
		tb_error = new JTable(errorData,errorTitle);
		tb_error.setColumnSelectionAllowed(true);
		tb_error.setEnabled(false);
		scrollpane_error = new JScrollPane(tb_error);
		main_panel.add(scrollpane_error);
		scrollpane_error.setBounds(1150, 100, 500, 600);
		
		add(main_panel);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

}
