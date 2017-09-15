package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.RecordDAO;
import entity.Record;
import util.DateUtil;

public class RepostService {
	
	/**
	 * 从一个月的消费记录中获取某一天的消费金额
	 * @param d
	 * @param monthRawData
	 * @return
	 */
	public int getDaySpend(Date d,List< Record > monthRawData){
		int daySpend = 0;
		for(Record record : monthRawData){
			if(record.getDate().equals(d)){
				daySpend += record.getSpend();
			}
		}
		return daySpend;
	}
	
	
	public int getDaySpend(Date d){
		List< Record > dayRecordList = new RecordDAO().getRecord(d);
		int daySpend = 0;
		for (Record record : dayRecordList) {
			daySpend += record.getSpend();
		}
		return daySpend;
	}
	
	
	/**
	 * 获取本月的消费记录集合
	 * @return
	 */
	public List< Record > getThisMonthRecords(){
		List< Record > monthAllRecord = new RecordDAO().getThisMonthRecord();
		List< Record > result = new ArrayList< Record >();
		
		//获取本月第一天
		Date monthBegin = DateUtil.monthBegin();
		//获取本月总天数
		int monthTotalDay = DateUtil.thisMonthTotalDay();
		
		//获取实例
		Calendar c = Calendar.getInstance();
		
		//遍历本月 并计算每一天的消费金额
		for(int i = 0;i < monthTotalDay;i ++){
			Record record  = new  Record();
			//计算并获取今日的时间类型实例
			c.setTime(monthBegin);
			c.add(Calendar.DATE, i);
			Date eachDayOfThisMontg = c.getTime();
			
			int todaySpend = getDaySpend(eachDayOfThisMontg, monthAllRecord);
			record.setSpend(todaySpend);
			result.add(record);
			
		}
		
		return result;
				
	}
}






















