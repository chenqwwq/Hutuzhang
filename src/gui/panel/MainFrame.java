package gui.panel;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	public static MainFrame instance = new MainFrame();
	
	private MainFrame(){
		//设置窗体大小
		this.setSize(500,450);
		//设置窗口标题
		this.setTitle("一本糊涂账");
		//设置床架的主面板
		this.setContentPane(MainPanel.instance);
		//窗体居中显示
		this.setLocationRelativeTo(null);
     	//设置窗体大小是否可调
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		instance.setVisible(true);
	}
}
