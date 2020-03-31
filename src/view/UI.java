package view;

import javax.swing.JFrame;

public class UI{
	public static void main(String[] args){
		new UI();
	}
	public UI() {
		// TODO Auto-generated constructor stub
		MainForm mainForm = new MainForm();
		mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainForm.setResizable(false);
		mainForm.setVisible(true);
	}
}
