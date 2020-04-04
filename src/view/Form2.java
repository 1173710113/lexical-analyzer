package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

import lexer.*;
import token.Tag;
import token.Token;
import token.errorToken.ErrorToken;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dfa.ConversionTable;
import dfa.DFA;
import dfa.factory.DFAFactory;
import exception.dfa.InValidInputException;
import exception.dfa.NullConvertionException;
import exception.recognize.RecognizeException;

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
	
	private String[] unitTitle = {"token序列","类型"};
	private String[][] unitData = {{}};
	private Character[] DFATitle;
	private String[][] DFAData = {{"1","2"}};
	private String[] errorTitle = {"错误"};
	private String[][] errorData;
	
	private List<String> inputs;
	private LexicalAnalyzer lexicalAnalyzer;
	
	
	public Form2(List<String> inputs){
		this.inputs = inputs;
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
		
		try {
			addDFA();
		} catch (InValidInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RecognizeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			addUnit();
			addError();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecognizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	public void addDFA() throws FileNotFoundException, RecognizeException, InValidInputException {
		DFAFactory dfaFactory = DFAFactory.getInstance();
		String dfaFilePath = "text\\text2.txt";
		DFA dfa = dfaFactory.createDFAByFile(dfaFilePath);
		List<Character> inputList = dfa.getInputs();
		inputList.add(0, ' ');
		DFATitle = new Character[inputList.size()];
		inputList.toArray(DFATitle);
		List<String> statesList = dfa.getStates();
		DFAData = new String[statesList.size()][inputList.size()];
		ConversionTable table = dfa.getConversionTable();
		for (int i = 0; i < statesList.size(); i++) {
			String state = statesList.get(i);
			DFAData[i][0] = state;
			if(dfa.isAcceptable(state)) {
				DFAData[i][0] += " (终止状态)";
			}
			for (int j = 1; j < inputList.size(); j++) {
				try {
					DFAData[i][j] = table.convert(state, inputList.get(j));
				} catch (NullConvertionException e) {
					DFAData[i][j] = "";
				} 
			}
		}
	}
	
	//添加token序列数据
	public void addUnit() throws FileNotFoundException, RecognizeException {
		lexicalAnalyzer = new LexicalAnalyzer();
		lexicalAnalyzer.setReadHeadFromStringList(inputs);
		lexicalAnalyzer.lexicalAnalyse();
		List<Token> resultToken = lexicalAnalyzer.getResultToken();
		unitData = new String[resultToken.size()][2];
		for(int i =0; i < resultToken.size(); i++) {
			unitData[i][0] = resultToken.get(i).toString();
			unitData[i][1] = Tag.tagToString(resultToken.get(i).tag);
		}
		
	}
	
	//添加错误数据
	public void addError() {
		List<ErrorToken> errorTokenList = lexicalAnalyzer.getErrorToken();
		errorData = new String[errorTokenList.size()][1];
		for(int i = 0 ; i < errorTokenList.size(); i++) {
			errorData[i][0] = errorTokenList.get(i).toString();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
