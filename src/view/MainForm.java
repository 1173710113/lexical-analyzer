package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import exception.dfa.InValidInputException;
import exception.grammar.SynchException;
import exception.recognize.RecognizeException;
import util.filereader.InputStrategy;

class MainForm extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel main_panel;

	private JMenuBar main_menu_bar;
	private JMenu menu_file;
	private JMenu menu_lexical;
	private JMenu menu_grammar;
	private JMenu menu_semantics;
	private JMenuItem file_open;
	private JMenuItem exit;
	private JMenuItem lexical_regulation;
	private JMenuItem lexical_analysis;
	private JMenuItem grammar_regulation;
	private JMenuItem grammar_analysis;
	private JMenuItem semantics_regulation;
	private JMenuItem semantics_analysis;

	private JLabel lb_text_edit;

	private JTextArea ta_input;
	private JScrollPane scrollpane_input;
	
	String file_name;

	public MainForm() {
		this.setTitle("编译器");
		this.setSize(700, 700);
		initPanel();
	}

	public void initPanel() {
		main_menu_bar = new JMenuBar();
		menu_file = new JMenu("文件");
		menu_lexical = new JMenu("词法分析器");
		menu_grammar = new JMenu("语法分析器");
		menu_semantics = new JMenu("语义分析器");

		file_open = new JMenuItem("打开");
		exit = new JMenuItem("退出");
		file_open.addActionListener(this);
		exit.addActionListener(this);
		menu_file.add(file_open);
		menu_file.add(exit);
		main_menu_bar.add(menu_file);

		lexical_regulation = new JMenuItem("词法规则");
		lexical_regulation.addActionListener(this);
		lexical_analysis = new JMenuItem("词法分析");
		lexical_analysis.addActionListener(this);
		menu_lexical.add(lexical_regulation);
		menu_lexical.add(lexical_analysis);
		main_menu_bar.add(menu_lexical);
		
		grammar_regulation = new JMenuItem("语法规则");
		grammar_regulation.addActionListener(this);
		grammar_analysis = new JMenuItem("语法分析");
		grammar_analysis.addActionListener(this);
		menu_grammar.add(grammar_regulation);
		menu_grammar.add(grammar_analysis);
		main_menu_bar.add(menu_grammar);
		
		semantics_regulation = new JMenuItem("语义规则");
		semantics_regulation.addActionListener(this);
		semantics_analysis = new JMenuItem("语义分析");
		semantics_analysis.addActionListener(this);
		menu_semantics.add(semantics_regulation);
		menu_semantics.add(semantics_analysis);
		main_menu_bar.add(menu_semantics);
		
		this.setJMenuBar(main_menu_bar);

		main_panel = new JPanel();
		main_panel.setLayout(null);
		lb_text_edit = new JLabel("文本编辑区");
		main_panel.add(lb_text_edit);
		lb_text_edit.setBounds(310, 10, 70, 20);

		ta_input = new JTextArea();
		scrollpane_input = new JScrollPane(ta_input);
		main_panel.add(scrollpane_input);
		scrollpane_input.setBounds(10, 40, 675, 600);
		ta_input.setEditable(true);
		// scrollpane_input.setRowHeaderView(new LineNumberHeaderView());

		add(main_panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == lexical_regulation) {
			LexicalRuleForm form1;
			try {
				form1 = new LexicalRuleForm();
				form1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				form1.setResizable(false);
				form1.setVisible(true);

			} catch (FileNotFoundException | RecognizeException | InValidInputException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		} else if (e.getSource() == lexical_analysis) {
			if (ta_input.getText().equals("")) {
				JOptionPane.showMessageDialog(main_panel, "您什么都没输入", "error", JOptionPane.ERROR_MESSAGE);
				System.out.println("nothing input!");
			} else {
				List<String> inputs = Arrays.asList(ta_input.getText().split("\n"));
				LexicalAnalyzeResultForm form2 = new LexicalAnalyzeResultForm(inputs);
				form2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				form2.setResizable(false);
				form2.setVisible(true);
			}
		} else if (e.getSource() == file_open) {
			
			JFileChooser file_open_filechooser = new JFileChooser();
			file_open_filechooser.setCurrentDirectory(new File("."));
			file_open_filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int result = file_open_filechooser.showOpenDialog(main_panel);

			if (result == JFileChooser.APPROVE_OPTION) {
				file_name = file_open_filechooser.getSelectedFile().getPath();
				try {
					ta_input.setText("");
					InputStrategy input = InputStrategy.input(new File(file_name));
					List<String> lines = input.getAllStrings();
					for(String line : lines) {
						ta_input.append(line + "\n");
					}
					//System.out.println(file_name);
				}catch (Exception event) {
					event.printStackTrace();
				}
				
//				File file = new File(file_name);
//				try {
//					InputStream in = new FileInputStream(file);
//					int tempbyte;
//					while ((tempbyte = in.read()) != -1) {
//						ta_input.append("" + (char) tempbyte);
//					}
//					in.close();
//				} catch (Exception event) {
//					event.printStackTrace();
//				}
			}

		} else if (e.getSource() == exit) {
			System.exit(1);
		} else if(e.getSource() == grammar_regulation) {
			try {
				GrammarRuleForm grForm = new GrammarRuleForm();
				grForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				grForm.setResizable(false);
				grForm.setVisible(true);
			} catch (InValidInputException | FileNotFoundException | RecognizeException | SynchException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}else if(e.getSource() == grammar_analysis) {
			if (ta_input.getText().equals("")) {
				JOptionPane.showMessageDialog(main_panel, "您什么都没输入", "error", JOptionPane.ERROR_MESSAGE);
				System.out.println("nothing input!");
			} else {
				List<String> inputs = Arrays.asList(ta_input.getText().split("\n"));
				GrammarAnalyzeResultForm grammarAnalyzeResultForm;
				try {
					grammarAnalyzeResultForm = new GrammarAnalyzeResultForm(inputs);
					grammarAnalyzeResultForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					grammarAnalyzeResultForm.setResizable(false);
					grammarAnalyzeResultForm.setVisible(true);
				} catch (InValidInputException | FileNotFoundException | RecognizeException  e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		}else if(e.getSource() == semantics_regulation) {
			try {
				SemanticsRuleForm semanticsRuleForm = new SemanticsRuleForm();
				semanticsRuleForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				semanticsRuleForm.setResizable(false);
				semanticsRuleForm.setVisible(true);
			}catch (InValidInputException | FileNotFoundException | RecognizeException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
		}else if(e.getSource() == semantics_analysis) {
			if (ta_input.getText().equals("")) {
				JOptionPane.showMessageDialog(main_panel, "您什么都没输入", "error", JOptionPane.ERROR_MESSAGE);
				System.out.println("nothing input!");
			} else {
				List<String> inputs = Arrays.asList(ta_input.getText().split("\n"));
				SemanticsAnalyzeResultForm semanticsAnalyzeResultForm;
				try {
					semanticsAnalyzeResultForm = new SemanticsAnalyzeResultForm(inputs);
					semanticsAnalyzeResultForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					semanticsAnalyzeResultForm.setResizable(false);
					semanticsAnalyzeResultForm.setVisible(true);
				} catch (InValidInputException | FileNotFoundException | RecognizeException  e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

}