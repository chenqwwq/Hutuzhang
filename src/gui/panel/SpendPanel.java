package gui.panel;
/**
 * 消费统计面板
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.page.SpendPage;
import service.SpendService;
import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

public class SpendPanel extends WorkingPanel{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//加载皮肤
	static{
		GUIUtil.useLNF();
	}
	
	//单例模式 创建唯一实例
	public static SpendPanel instance = new SpendPanel();
	
	public JLabel lMonthSpend = new JLabel("本月消费");
	public JLabel lTodaySpend = new JLabel("今日消费");
	public JLabel lAvgSpendPerDay = new JLabel("日均消费");
	public JLabel lMonthLeft = new JLabel("本月剩余");
	public JLabel lDayAvgAvailable = new JLabel("日均可用");
	public JLabel lMonthLeftDay = new JLabel("距离月末");
	
	public JLabel vMonthSpend = new JLabel();
	public JLabel vTodaySpend = new JLabel();
	public JLabel vAvgSpendPerDay = new JLabel();
	public JLabel vMonthAvailable = new JLabel();
	public JLabel vDayAvgAvailable = new JLabel();
	public JLabel vMonthLeftDay = new JLabel();
	
	//圆形记录
	CircleProgressBar bar;
	
	private SpendPanel(){
		//设置布局模式
		this.setLayout(new BorderLayout());
		//初始化圆周
		bar = new CircleProgressBar();
		//设置圆背景为蓝色
		bar.setBackground(ColorUtil.blueColor);
		//设置一组控件的背景
		GUIUtil.setColor(ColorUtil.grayColor, lMonthSpend, lTodaySpend, lAvgSpendPerDay, lMonthLeft, lDayAvgAvailable,
				lMonthLeftDay, vAvgSpendPerDay, vMonthAvailable, vDayAvgAvailable, vMonthLeftDay);
		GUIUtil.setColor(ColorUtil.blueColor, vMonthSpend, vTodaySpend);
		
		//设置字体
		vMonthSpend.setFont(new Font("微软雅黑", Font.BOLD, 23));
		vTodaySpend.setFont(new Font("微软雅黑", Font.BOLD, 23));
		
		this.add(center(), BorderLayout.CENTER);
		this.add(south(), BorderLayout.SOUTH);
	}
	
	
	private JPanel center(){
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(west(), BorderLayout.WEST);
		p.add(center2(),BorderLayout.CENTER);
		return p;
	}
	
	
	private Component center2(){
		return bar;
	}
	
	private Component west(){
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4, 1));
		p.add(lMonthSpend);
		p.add(vMonthSpend);
		p.add(lTodaySpend);
		p.add(vTodaySpend);
		return p;
	}
	
	private JPanel south(){
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 4));
		
		p.add(lAvgSpendPerDay);
		p.add(lMonthLeft);
		p.add(lDayAvgAvailable);
		p.add(lMonthLeftDay);
		p.add(vAvgSpendPerDay);
		p.add(vMonthAvailable);
		p.add(vDayAvgAvailable);
		p.add(vMonthLeftDay);
		
		return p;
	}
	
	
	@Override
	public void updateDate() {
		SpendPage spend = new SpendService().getSpendPage();
		vMonthSpend.setText(spend.monthSpend);
		vTodaySpend.setText(spend.todaySpend);
		vAvgSpendPerDay.setText(spend.avgSpendPerDay);
		vDayAvgAvailable.setText(spend.avgDayAvailable);
		vMonthAvailable.setText(spend.monthAvailable);
		vMonthLeftDay.setText(spend.monthLeftDay);
		
		bar.setProgress(spend.usagePercentage);
		if (spend.isOverSpend) {
			vMonthAvailable.setForeground(ColorUtil.warningColor);
			vMonthSpend.setForeground(ColorUtil.warningColor);
			vTodaySpend.setForeground(ColorUtil.warningColor);
		} else {
			vMonthAvailable.setForeground(ColorUtil.grayColor);
			vMonthSpend.setForeground(ColorUtil.blueColor);
			vTodaySpend.setForeground(ColorUtil.blueColor);
		}
		bar.setForegroundColor(ColorUtil.getByPercentage(spend.usagePercentage));
		addListener();
	}


	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main(String[] args) {
		GUIUtil.showPanel(SpendPanel.instance);
	}
	
}


















