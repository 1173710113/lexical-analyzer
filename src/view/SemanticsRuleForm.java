package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import exception.dfa.InValidInputException;
import exception.recognize.RecognizeException;
import grammar.production.Production;
import grammar.production.ProductionFactory;

public class SemanticsRuleForm extends JFrame implements ActionListener{
	
	private JPanel main_panel;
	private JLabel lb_regulation;
	private JScrollPane scrollpane_regulation;
	private JTable tb_regulation;

	private String[] regulationTitle = {"产生式","SDT"};
	private String[][] regulationData={};	
	
	public SemanticsRuleForm() throws FileNotFoundException, RecognizeException, InValidInputException {
		this.setTitle("语义规则");
		this.setSize(1000, 800);
		initPanel();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void initPanel() throws FileNotFoundException, RecognizeException, InValidInputException {
		main_panel = new JPanel();
		main_panel.setLayout(null);
		lb_regulation = new JLabel("语义规则");
		main_panel.add(lb_regulation);
		lb_regulation.setBounds(500, 10, 70, 20);
		
		addData();

		tb_regulation = new JTable(regulationData, regulationTitle);
		tb_regulation.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb_regulation.setColumnSelectionAllowed(true);
		scrollpane_regulation = new JScrollPane(tb_regulation);
		main_panel.add(scrollpane_regulation);
		scrollpane_regulation.setBounds(50, 50, 900, 700);
		tb_regulation.getColumnModel().getColumn(0).setPreferredWidth(150);
		tb_regulation.getColumnModel().getColumn(1).setPreferredWidth(750);
		
		add(main_panel);
	}
	
	//添加语法规则数据
	private void addData() {
		try {
			Set<Production> productions = ProductionFactory.getProductionsFromXMLFile("text\\sdt.xml");
			int size = productions.size();
			regulationData = new String[size][2];
			int count = 0;
			for(Production p : productions) {
				regulationData[count][0] = Integer.toString(count);
				regulationData[count][1] = p.toString();
				count++;
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

}
