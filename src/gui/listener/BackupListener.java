package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import gui.panel.BackupPanel;
import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import service.ConfigService;
import util.MysqlUtil;

public class BackupListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		BackupPanel panel = BackupPanel.instance;
		//获取数据库中的mysql路径
		String mysqlPath = new ConfigService().getValueByKey(ConfigService.mysqlPath);
		//判断mysql的配置路径
		if(0 == mysqlPath.length()){
			JOptionPane.showMessageDialog(panel, "备份前请先配置mysql的安装路径");
			//跳转到配置界面
			MainPanel.instance.workingPanel.show(ConfigPanel.instance);
			//mysql的输入框获取焦点
			ConfigPanel.instance.tfMysqlPath.grabFocus();
			return ;
		}
		
		JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File("hutubill.sql"));
		fc.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return ".sql";
			}
			
			@Override
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".sql");
			}
		});
		
		int returnVal = fc.showSaveDialog(panel);
		File file = fc.getSelectedFile();
//		System.out.println(file);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			if(!file.getName().toLowerCase().endsWith(".sql")){
				file = new File(file.getParent(),file.getName()+".sql");
			}
			
			try{
				MysqlUtil.backup(mysqlPath, file.getAbsolutePath());
				JOptionPane.showMessageDialog(panel,  "备份成功,备份文件位于:\r\n"+file.getAbsolutePath());
			}catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(panel, "备份失败");
			}
		}
	}
	

}
