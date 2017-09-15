package gui.panel;
/**
 * 备份界面
 */

import javax.swing.JButton;
import gui.listener.BackupListener;
import util.ColorUtil;
import util.GUIUtil;

public class BackupPanel extends WorkingPanel{

	private static final long serialVersionUID = 1L;
	
	static{
		GUIUtil.useLNF();
	}
	
	public static BackupPanel instance  = new BackupPanel();
	JButton bBuckup = new JButton("备份");
	
	private BackupPanel(){
		GUIUtil.setColor(ColorUtil.blueColor,bBuckup);
		this.add(bBuckup);
		addListener();
	}
	
	public static void main(String[] args){
		GUIUtil.showPanel(BackupPanel.instance);
	}
	
	@Override
	public void updateDate() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addListener() {
		BackupListener listener = new BackupListener();
		bBuckup.addActionListener(listener);
	}
	
}
