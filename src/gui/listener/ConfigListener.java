package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import gui.panel.ConfigPanel;
import service.ConfigService;
import util.GUIUtil;

public class ConfigListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * 1）首先判断输入的预算信息是否为整数
		 * 2）接着判断MySQL得安装路径是否正确（判断该目录的bin目录下是否有mysql.exe）
		 * 3)调用ConfigService的update方法修改数据库内容
		 */
		//获取配置信息的面板实例
		ConfigPanel cp = ConfigPanel.instance;
		
		//判断输入的预算是否为整数 String类参数为输入的含义
		if(!GUIUtil.checkNumber(cp.tfBudget, "当月预算")){
			return;
		}
		
		//获取mysql的安装路径
		String mysqlPath = cp.tfMysqlPath.getText().trim();
		
		//若输入不为空 则进一步检查mysql安装目录是否正确
		if(0 == mysqlPath.length()){
			File f = new File(mysqlPath,"bin/mysql.txt"); 
			//若路径不正确 则输出错误信息
			if(!f.exists()){
				JOptionPane.showMessageDialog(cp, "mysql安装路径不正确");
				//输入框重新获取焦点
				cp.tfMysqlPath.grabFocus();
				return;
			}
		}
		
		//通过双重验证后 将配置信息更新
		ConfigService cs = new ConfigService();
		//更新预算信息
		cs.update(ConfigService.budget, cp.tfBudget.getText());
		cs.update(ConfigService.mysqlPath, mysqlPath);
		JOptionPane.showMessageDialog(cp, "配置修改成功");
	}

}



















