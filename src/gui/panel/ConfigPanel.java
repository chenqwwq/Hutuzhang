package gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.listener.ConfigListener;
import service.ConfigService;
import util.ColorUtil;
import util.GUIUtil;

public class ConfigPanel extends WorkingPanel{
	
	private static final long serialVersionUID = 1L;
	
	static{
		GUIUtil.useLNF();
	}
	
	public static ConfigPanel instance = new ConfigPanel();
	
	JLabel lBudget = new JLabel("本月预算(￥)"); 
	public JTextField tfBudget = new JTextField("0");
	
	JLabel lMysql = new JLabel("MySQL安装目录");
	public JTextField tfMysqlPath = new JTextField("");
	
	JButton bUpdate = new JButton("更新");
	
	public ConfigPanel(){
		GUIUtil.setColor(ColorUtil.grayColor,lBudget,lMysql);
		GUIUtil.setColor(ColorUtil.blueColor, bUpdate);
	
		JPanel pInput = new JPanel();
		JPanel pSubmit = new JPanel();
		int gap = 40;
		pInput.setLayout(new GridLayout(4, 1,gap,gap));
		
		pInput.add(lBudget);
		pInput.add(tfBudget);
		pInput.add(lMysql);
		pInput.add(tfMysqlPath);
		
		this.setLayout(new BorderLayout());
		this.add(pInput, BorderLayout.NORTH);
		
		pSubmit.add(bUpdate);
		this.add(pSubmit, BorderLayout.CENTER);
		
		addListener();
		
//		updateDate();
	}
	
	
	
	/**
	 * 为按钮添加监听
	 * @param args
	 */
	public void addListener(){
		ConfigListener c = new ConfigListener();
		bUpdate.addActionListener(c);
	}
	
	
	public static void main(String[] args) {
		GUIUtil.showPanel(ConfigPanel.instance);
	}



	@Override
	public void updateDate() {
		ConfigService cs = new ConfigService();
		//从数据库中获取配置信息
		String budget = cs.getValueByKey(ConfigService.budget);
		String mysqlPath = cs.getValueByKey(ConfigService.mysqlPath);
		//将配置信息显示在界面
		tfBudget.setText(budget);
		tfMysqlPath.setText(mysqlPath);
		
		//输入框获取焦点
		tfBudget.grabFocus();
	}
	
}
