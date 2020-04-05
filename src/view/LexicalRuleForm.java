package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import dfa.ConversionTable;
import dfa.DFA;
import dfa.factory.DFAFactory;
import exception.dfa.InValidInputException;
import exception.dfa.NullConvertionException;
import exception.recognize.RecognizeException;
import filereader.InputStrategy;

public class LexicalRuleForm extends JFrame implements ActionListener {

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

	private Character[] DFATitle;
	private String[][] DFAData;	

	public LexicalRuleForm() throws FileNotFoundException, RecognizeException, InValidInputException {
		this.setTitle("Form1");
		this.setSize(1500, 800);
		initPanel();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void initPanel() throws FileNotFoundException, RecognizeException, InValidInputException {
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
		addregulation();

		tb_DFA = new JTable(DFAData, DFATitle);
		tb_DFA.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb_DFA.setColumnSelectionAllowed(true);
		scrollpane_DFA = new JScrollPane(tb_DFA);
		main_panel.add(scrollpane_DFA);
		scrollpane_DFA.setBounds(770, 100, 675, 600);

		add(main_panel);
	}

	// 添加DFA转换表数据
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

	// 添加词法规则数据
	public void addregulation() throws FileNotFoundException {
		ta_regulation.setText("");
		String criteriaFile = "text\\词法规则.txt";
		InputStrategy input = InputStrategy.input(new File(criteriaFile));
		List<String> criteriaList = input.getAllStrings();
		for(String str : criteriaList) {
			ta_regulation.append(str + "\n");
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//
	}

}
