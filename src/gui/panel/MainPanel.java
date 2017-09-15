package gui.panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import gui.listener.ToolBarListener;
import util.CenterPanel;
import util.GUIUtil;

public class MainPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//在界面渲染前加载皮肤
	static{
		GUIUtil.useLNF();
	}
	
	//采用单例模式 
	public static MainPanel instance = new MainPanel();
	//为了监听器使用的方便 各种组件使用public修饰
	//工具栏实例
	public JToolBar tb  = new JToolBar();
	public JButton bSpend = new JButton();
	public JButton bRecord = new JButton();
	public JButton bCategory = new JButton();
	public JButton bReport = new JButton();
	public JButton bConfig = new JButton();
	public JButton bBackup = new JButton();
	public JButton bRecover = new JButton();
	
	public CenterPanel workingPanel;
	
	
	//私有化其构造函数
	private MainPanel(){
		GUIUtil.setImageIcon(bSpend, "home.png","消费一览");
		GUIUtil.setImageIcon(bRecord, "record.png","记一笔");
		GUIUtil.setImageIcon(bCategory, "category2.png", "消费分类");
		GUIUtil.setImageIcon(bReport, "report.png", "月消费报表");
		GUIUtil.setImageIcon(bConfig, "config.png", "设置");
		GUIUtil.setImageIcon(bBackup, "backup.png", "备份");
		GUIUtil.setImageIcon(bRecover, "restore.png", "恢复");
		
		tb.add(bSpend);
		tb.add(bRecord);
		tb.add(bCategory);
		tb.add(bReport);
		tb.add(bConfig);
		tb.add(bBackup);
		tb.add(bRecover);
		
		//用户不能移动工具栏
		tb.setFloatable(false);
		
		workingPanel = new CenterPanel(0.8);
		
		setLayout(new BorderLayout());
		//将工具栏在界面上方显示
		add(tb,BorderLayout.NORTH);
		//主要的工作区域在中间
		add(workingPanel,BorderLayout.CENTER);
		
		ButtonListener();
	}
	
	
	public void ButtonListener(){
		ToolBarListener tbl  = new ToolBarListener();
		bBackup.addActionListener(tbl);
		bSpend.addActionListener(tbl);
		bCategory.addActionListener(tbl);
		bConfig.addActionListener(tbl);
		bRecord.addActionListener(tbl);
		bReport.addActionListener(tbl);
		bRecover.addActionListener(tbl);
		
	}
	
	
	public static void main(String[] args) {
		GUIUtil.showPanel(MainPanel.instance,1);
	}

}






















