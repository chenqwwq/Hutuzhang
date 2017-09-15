package gui.panel;

import javax.swing.JPanel;

public abstract class WorkingPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	/**
	 * 活动的界面保持和数据库的同步
	 */
	public abstract void updateDate();
	/**
	 * 为活动界面添加监听器
	 */
	public abstract void addListener();
}
