package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gui.panel.BackupPanel;
import gui.panel.CategoryPanel;
import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import gui.panel.RecoverPanel;
import gui.panel.ReportPanel;
import gui.panel.SpendPanel;

public class ToolBarListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		//获取主面板的实例
		MainPanel mp = MainPanel.instance;
		//获取点击的按钮
		JButton b = (JButton)e.getSource();
		
		/**
		 * 按钮类不能作为switch的参数所以这里全部使用if判断语句
		 *	获取备份面板的实例 并将其显示在工作区面板
		 */
		if(b == mp.bBackup)
			mp.workingPanel.show(BackupPanel.instance);
		if(b == mp.bCategory)
			mp.workingPanel.show(CategoryPanel.instance);
		if(b == mp.bConfig)
			mp.workingPanel.show(ConfigPanel.instance);
		if(b == mp.bRecord)
			mp.workingPanel.show(RecordPanel.instance);
		if(b == mp.bRecover)
			mp.workingPanel.show(RecoverPanel.instance);
		if(b == mp.bReport)
			mp.workingPanel.show(ReportPanel.instance);
		if(b == mp.bSpend)
			mp.workingPanel.show(SpendPanel.instance);
		
	}

}
