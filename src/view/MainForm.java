package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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

class MainForm extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel main_panel;
	
	private JMenuBar main_menu_bar;
	private JMenu menu_file;
	private JMenu menu_lexical;
	private JMenuItem file_open;
	private JMenuItem exit;
	private JMenuItem lexical_regulation;
	private JMenuItem lexical_analysis;
	
	private JLabel lb_text_edit;
	
	private JTextArea ta_input;
	private JScrollPane scrollpane_input;
	
	public MainForm(){
		this.setTitle("编译器");
		this.setSize(700,700);
		initPanel();
	}
	
	public void initPanel(){
		main_menu_bar = new JMenuBar();
		menu_file = new JMenu("文件");
		menu_lexical = new JMenu("词法分析器");
		
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
		//scrollpane_input.setRowHeaderView(new LineNumberHeaderView());
		
		add(main_panel);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ( e.getSource() == lexical_regulation) {
			Form1 form1 = new Form1();
			form1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			form1.setResizable(false);
			form1.setVisible(true);
			
		}
		else if ( e.getSource() == lexical_analysis) {
			if(ta_input.getText().equals("")) {
				JOptionPane.showMessageDialog(main_panel, "您什么都没有输入啊！", "提示", JOptionPane.ERROR_MESSAGE);
				System.out.println("nothing input!");
			}
			else {
				Form2 form2 = new Form2();
				form2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				form2.setResizable(false);
				form2.setVisible(true);
			}
		}
		else if(e.getSource() == file_open){
			String file_name;
			JFileChooser file_open_filechooser = new JFileChooser();
			file_open_filechooser.setCurrentDirectory(new File("."));
			file_open_filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int result = file_open_filechooser.showOpenDialog(main_panel);
			// 证明有选择
			if (result==JFileChooser.APPROVE_OPTION) {
				file_name = file_open_filechooser.getSelectedFile().getPath();
				// 读取文件，写到JTextArea里面
				File file = new File(file_name);
				try{
					InputStream in = new FileInputStream(file);
					int tempbyte;
					while ((tempbyte=in.read()) != -1) {
						ta_input.append(""+(char)tempbyte);
					}
					in.close();
				}
				catch(Exception event){
					event.printStackTrace();
				}
			}
			
		}
		else if(e.getSource() == exit){
			System.exit(1);
		}
		else {
			System.out.println("nothing！");
		}
	}
	
	
}