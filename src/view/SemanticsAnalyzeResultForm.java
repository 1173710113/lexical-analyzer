package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
import exception.sdt.SDTException;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.StringNonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;
import grammar.predictinganalysis.First;
import grammar.predictinganalysis.Follow;
import grammar.predictinganalysis.PredictingAnalysisTable;
import grammar.predictinganalysis.Select;
import grammar.production.Production;
import grammar.production.ProductionFactory;
import lexer.LexicalAnalyzer;
import lexer.token.Token;
import sdt.Quadruple;
import sdt.SDTAnalyzer;
import sdt.identifiertable.IdentifierTableItem;
import util.readhead.TerminalSymbolReadHead;
import util.readhead.TokenTerminalSymbolReadHead;

public class SemanticsAnalyzeResultForm extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel main_panel;
	private JLabel lb_analysis;
	private JLabel lb_address;
	private JLabel lb_symbol;
	private JLabel lb_error;
	private JScrollPane scrollpane_address, scrollpane_symbol, scrollpane_error;
	private JTable tb_address, tb_symbol, tb_error;

	private String[] addressTitle = { "���", "����ַָ��", "��Ԫʽ", "�к�" };
	private String[][] addressData = {};
	private String[] symboleTitle = { "��ʶ��", "����", "ƫ����", "�к�" };
	private String[][] symbolData = {};
	private String[] errorTitle = {"����ԭ��", "�к�" };
	private String[][] errorData = {};

	private List<String> inputs;

	public SemanticsAnalyzeResultForm(List<String> inputs) throws Exception {
		this.inputs = inputs;
		this.setTitle("�������");
		this.setSize(1700, 800);
		initPanel();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void initPanel() throws Exception {
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

		init();

		tb_address = new JTable(addressData, addressTitle);
		tb_address.setColumnSelectionAllowed(true);
		tb_address.setEnabled(false);
		scrollpane_address = new JScrollPane(tb_address);
		main_panel.add(scrollpane_address);
		scrollpane_address.setBounds(50, 100, 500, 600);

		tb_symbol = new JTable(symbolData, symboleTitle);
		tb_symbol.setColumnSelectionAllowed(true);
		tb_symbol.setEnabled(false);
		scrollpane_symbol = new JScrollPane(tb_symbol);
		main_panel.add(scrollpane_symbol);
		scrollpane_symbol.setBounds(600, 100, 500, 600);

		tb_error = new JTable(errorData, errorTitle);
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

	private void init() throws Exception {
		Set<Production> productions = ProductionFactory.getProductionsFromXMLFile("text\\sdt.xml");
		Map<NonterminalSymbol, Set<TerminalSymbol>> firstMap = First.getNonterminalSymbolFirstSetMap(productions);
		NonterminalSymbol startSymbol = new StringNonterminalSymbol("Program");
		Map<NonterminalSymbol, Set<TerminalSymbol>> followMap = Follow.getFollow(firstMap, startSymbol, productions);
		Map<Production, Set<TerminalSymbol>> selectMap = Select.getProductionSelectSet(productions, firstMap,
				followMap);
		PredictingAnalysisTable predictingAnalysisTable = new PredictingAnalysisTable(selectMap, followMap);
		LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
		lexicalAnalyzer.setReadHeadFromStringList(inputs);
		lexicalAnalyzer.lexicalAnalyse();
		List<Token> resultToken = lexicalAnalyzer.getResultToken();
		TerminalSymbolReadHead readHead = new TokenTerminalSymbolReadHead(resultToken);
		SDTAnalyzer sdtAnalyzer = new SDTAnalyzer();
		sdtAnalyzer.analyse(predictingAnalysisTable, readHead, startSymbol);
		List<String> genList = sdtAnalyzer.getState().getGenList();
		List<Quadruple> quadruples = sdtAnalyzer.getState().getQuadruples();
		int size = genList.size();
		addressData = new String[size][4];
		for (int i = 0; i < size; i++) {
			addressData[i][0] = Integer.toString(i);
			addressData[i][1] = genList.get(i);
			addressData[i][2] = quadruples.get(i).toString();
		}
		List<IdentifierTableItem> identifierTableItems = sdtAnalyzer.getState().getIdentifierTable().getTableItems();
		int idSize = identifierTableItems.size();
		symbolData = new String[idSize][4];
		for(int i = 0; i < idSize; i++) {
			symbolData[i][0] = identifierTableItems.get(i).getId();
			symbolData[i][1] = identifierTableItems.get(i).getType().toString();
			symbolData[i][2] = Integer.toString(identifierTableItems.get(i).getOffset());
		}
		
		List<SDTException> exceptions = sdtAnalyzer.getState().getExceptions();
		int eSize = exceptions.size();
		errorData = new String[eSize][3];
		for(int i = 0; i < eSize; i++) {
			errorData[i][0] = exceptions.get(i).getMessage();
		}
	}

}
