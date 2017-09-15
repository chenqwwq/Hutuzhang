package gui.page;

public class SpendPage {
	public String monthSpend;       //本月消费
	public String todaySpend;		//今日消费
	public String avgSpendPerDay;;		//日均消费
	public String monthAvailable;	//本月剩余
	public String avgDayAvailable;	//日均可用
	public String monthLeftDay;		//距离月末
	public int usagePercentage;	//使用比例
	public boolean isOverSpend;		//是否超支  默认为false
	
	
	/**
	 * 构造函数 传入int 显示方便转化为String
	 */
	public SpendPage(int monthSpend, int todaySpend, int avgSpendPerDay, int monthAvailable, 
			int dayAvgAvailable,int monthLeftDay, int usagePercentage) {
		this.monthSpend = "￥" + monthSpend;
		this.todaySpend = "￥" + todaySpend;
		this.avgSpendPerDay = "￥" + avgSpendPerDay;
		//判断是否超支
		if(monthAvailable < 0){
			this.isOverSpend = true;
		}
		
		
		if(isOverSpend){
			this.monthAvailable = "超支" + (0 - monthAvailable);
			this.avgDayAvailable = "￥0";
		}else{
			this.monthAvailable ="￥" + monthAvailable;
			this.avgDayAvailable ="￥" + dayAvgAvailable;
		}
		
		this.monthLeftDay = monthLeftDay + "天";
		this.usagePercentage = usagePercentage;

	}
}
