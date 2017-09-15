package util;
/**
 * 时间的工具类
 */

import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author Administrator
 *
 */

public class DateUtil {
	//一天的毫秒数
	private static final long MILLISECOND_DAY = 1000*60*60*24;
	
	//将util的日期类型转化为sql的类日期类型
	public static java.sql.Date util2sql(java.util.Date d){
		return new java.sql.Date(d.getTime());
	}
	
	
	/**
	 * 获取今天，并且把时，分，秒和毫秒都置0
	 * @return
	 */
	public static Date today(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 获取月初
	 * 在统计消费信息是，就是从数据库按照日期查找出月初到月末的数据
	 */
	public static Date monthBegin(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DATE, 1);
		
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		return c.getTime();
	}
	
	/**
	 * 获取月末时间
	 */
	public static Date monthEnd(){
		//获得Calendar实例
		Calendar c = Calendar.getInstance();
		//将时间设定为今天包含年-月-日-小时-分钟-秒
		c.setTime(new Date());
		//设定参数中的单位时间为0
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		//时间设置为该月第一天
		c.set(Calendar.DATE, 1);
		c.add(Calendar.MONDAY, 1);
		c.add(Calendar.DATE, -1);
		return c.getTime();
	}
	
	public static int thisMonthTotalDay(){
		long lastDay = monthEnd().getTime();
		long firstDay = monthBegin().getTime();
		return  (int) ((lastDay-firstDay)/MILLISECOND_DAY) +1;
	}
	
	/**
	 * 获取本月还剩下多少天
	 */
	
	public static int thisMonthLeftDay(){
		long lastDay = monthEnd().getTime();
		long toDay = today().getTime();
		return  (int) ((lastDay-toDay)/MILLISECOND_DAY) +1;
	}
	
	
	public static void main(String[] args) {
//		System.out.println(DateUtil.today());
//		System.out.println(DateUtil.monthBegin());
		System.out.println(DateUtil.monthEnd());
//		System.out.println(thisMonthLeftDay());
//		System.out.println(thisMonthTotalDay());
	}
	
}

