package service;
/**
 * 消费一览界面的业务类
 * @author Administrator
 *
 */

import java.util.List;

import dao.RecordDAO;
import entity.Record;
import gui.page.SpendPage;
import util.DateUtil;

public class SpendService {
	
	/**
	 * 获取SpendPage界面用于在界面显示
	 * @return
	 */
	public SpendPage getSpendPage(){
		
		//获取消费记录的DAO层实例
		RecordDAO dao = new RecordDAO();
		//获取本月消费记录数据
		List< Record > thisMonthRecords = dao.getThisMonthRecord();
		
		
		
		//获取今日消费记录数据
		List< Record > toDayRecords = dao.getToDayRecord();
		//获取本月总天数
		int thisMonthTotalDay = DateUtil.thisMonthTotalDay();
		
		//定义获取的的数据
		int monthSpend = 0;
		int todaySpend = 0;
		int avgSpendPerDay = 0;
		int monthAvailable = 0;
		int dayAvgAvailable = 0;
		int monthLeftDay = 0;
		int usagePercentage = 0;
		
		//获得预算
		int monthBudget = new ConfigService().getIntBudget();
		
		/**
		 * 统计本月消费额
		 * 遍历本月的没一条消费记录 获取金额并相加
		 */
		for (Record record : thisMonthRecords) {
			monthSpend += record.getSpend();
		}
		
		//统计今日消费
		for (Record record : toDayRecords) {
			todaySpend += record.getSpend();
		}
		//计算日均消费
		avgSpendPerDay = monthSpend / thisMonthTotalDay;
		
		
		//计算本月剩余
		monthAvailable = monthBudget - monthSpend;
		//计算距离月末多少天
		monthLeftDay = DateUtil.thisMonthLeftDay();
		//计算日均可用
		dayAvgAvailable = monthAvailable / monthLeftDay;
		//计算使用比例
		usagePercentage = monthSpend * 100 / monthBudget;  
		//生成SpendPage 并return
		return new SpendPage(monthSpend, todaySpend, avgSpendPerDay, monthAvailable,
				dayAvgAvailable, monthLeftDay, usagePercentage);
		
		
		
		
	}
	

}
