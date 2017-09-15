package gui.panel;
/**
 * 消费报表面板
 * 用柱形图表示这个月的消费记录
 */

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import entity.Record;
import service.RepostService;
import util.ChartUtil;
import util.GUIUtil;

public class ReportPanel extends WorkingPanel{

	private static final long serialVersionUID = 1L;
	
	static {
		GUIUtil.useLNF();
	}
	
	//创建唯一的实例
	public static ReportPanel instance = new ReportPanel();
	 
	public JLabel l = new JLabel();
	
	private ReportPanel(){
		this.setLayout(new BorderLayout());
		List< Record > rs = new RepostService().getThisMonthRecords();
		Image i = ChartUtil.getImage(rs,400, 300);
		ImageIcon icon = new ImageIcon(i);
		l.setIcon(icon);
		this.add(l);
		addListener();
	}
	
	@Override
	public void updateDate() {
		List< Record > rs = new RepostService().getThisMonthRecords();
		Image i = ChartUtil.getImage(rs, 350,250);
		ImageIcon icon = new ImageIcon(i);
		l.setIcon(icon);
		
	}
	
	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] agrs){
		GUIUtil.showPanel(ReportPanel.instance);
	}
}
