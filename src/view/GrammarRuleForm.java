package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import exception.dfa.InValidInputException;
import exception.grammar.NullPredictionException;
import exception.grammar.SynchException;
import exception.recognize.RecognizeException;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;
import grammar.predictinganalysis.First;
import grammar.predictinganalysis.Follow;
import grammar.predictinganalysis.PredictingAnalysisTable;
import grammar.predictinganalysis.Select;
import grammar.production.Production;
import grammar.production.ProductionFactory;

public class GrammarRuleForm extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	private String[] fandftitle = {"产生式","first集","follow集"};
	private String[][] fandfdata;
	private String[] selecttitle = {"产生式","select集"};
	private String[][] selectdata;
	private String[] anatitle;
	private String[][] anadata;
	
	public GrammarRuleForm() throws FileNotFoundException, RecognizeException, InValidInputException, SynchException {
		this.setTitle("语法规则");
		this.setSize(1700, 800);
		initPanel();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void initPanel() throws FileNotFoundException, SynchException {
		addData();
		main_panel = new JPanel();
		main_panel.setLayout(null);
		lb_grammar = new JLabel("文法分析");
		main_panel.add(lb_grammar);
		lb_grammar.setBounds(800, 10, 70, 20);
		lb_fandfset = new JLabel("first集和follow集");
		main_panel.add(lb_fandfset);
		lb_fandfset.setBounds(250, 50, 150, 20);
		lb_selectset = new JLabel("select集");
		main_panel.add(lb_selectset);
		lb_selectset.setBounds(800, 50, 70, 20);
		lb_anatable = new JLabel("预测分析表");
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
	
	//添加first集和follow集数据
	private void addData() throws FileNotFoundException, SynchException {
		Set<Production> productions = ProductionFactory.getProductionsFormJSONFile("text\\grammar.json");
		Map<NonterminalSymbol, Set<TerminalSymbol>> firstMap = First.getNonterminalSymbolFirstSetMap(productions);
		NonterminalSymbol startSymbol = (new ArrayList<Production>(productions)).get(0).getNonterminalSymbol();
		Map<NonterminalSymbol, Set<TerminalSymbol>> followMap = Follow.getFollow(firstMap, startSymbol, productions);
		fandfdata = new String[followMap.size()][3];
		int i = 0;
		for(Map.Entry<NonterminalSymbol, Set<TerminalSymbol>> entry : firstMap.entrySet()) {
			fandfdata[i][0] = entry.getKey().toString();
			fandfdata[i][1] = entry.getValue().toString();
			fandfdata[i][2] = followMap.get(entry.getKey()).toString();
			i++;
		}
		Map<Production, Set<TerminalSymbol>> selectMap = Select.getProductionSelectSet(productions, firstMap, followMap);
		selectdata = new String[selectMap.size()][2];
		int j = 0;
		for(Map.Entry<Production, Set<TerminalSymbol>> entry : selectMap.entrySet()) {
			selectdata[j][0] = entry.getKey().toString();
			selectdata[j][1] = entry.getValue().toString();
			j++;
		}
		PredictingAnalysisTable predictingAnalysisTable = new PredictingAnalysisTable(selectMap, followMap);
		Set<TerminalSymbol> terminalSymbols = predictingAnalysisTable.getTerminalSymbolMap().keySet();
		anatitle = new String[terminalSymbols.size() + 1];
		anatitle[0] = "";
		int k = 1;
		for(TerminalSymbol terminalSymbol : terminalSymbols) {
			anatitle[k] = terminalSymbol.toString();
			k++;
		}
		
		Set<NonterminalSymbol> nonterminalSymbols = predictingAnalysisTable.getNonterminalSymbolMap().keySet();
		anadata = new String[nonterminalSymbols.size()][terminalSymbols.size() + 1];
		k = 0;
		for(NonterminalSymbol nonterminalSymbol : nonterminalSymbols) {
			j = 1;
			anadata[k][0] = nonterminalSymbol.toString();
			for(TerminalSymbol terminalSymbol : terminalSymbols) {
				try {
					anadata[k][j] = predictingAnalysisTable.getPredict(nonterminalSymbol, terminalSymbol).toString();
				} catch (NullPredictionException e) {
					anadata[k][j] = "";
				} catch (SynchException e) {
					anadata[k][j] = "synch";
				}
				j++;
			}
			k++;
		}
		
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

}
