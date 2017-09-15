package service;

import dao.ConfigDAO;
import entity.Config;

/**
 * Service是业务类 是监听器直接调用的类 通过service调用DAO层 操作数据库
 * @author Administrator
 *
 */

public class ConfigService {
	
	
	public static final String budget = "budget";
	public static final String mysqlPath = "mysqlPath";
	private static final String default_budget = "500";
	
	
	//创建config的DAO
	private static ConfigDAO dao = new ConfigDAO();
	
	
	//完成初始化工作
	static{
		init();
	}
	
	
	
	/**
	 * 初始化
	 */
	public static void init(){
		init(budget,default_budget);
		init(mysqlPath,"");
	}
	
	
	/**
	 * 根据相应的key值和value初始化配置信息
	 * @param key
	 * @param value
	 */
	private static void init(String key,String value){
		//调用dao层的方法 获取相应记录
		Config config = dao.getBykey(key);
		
		//如果为空表示没有该记录 配置信息没有存入数据库
		if(config == null){
			Config c = new Config();
			c.setKey(key);
			c.setValue(value);
			//调用dao层方法将该配置信息记录存入数据库
			dao.add(c);
		}
	}
	
	
	/**
	 * 根据key获得相应的配置信息
	 * @param key
	 */
	public String getValueByKey(String key){
		//调用dao层方法 通过key获得Config对象实例
		Config config  = dao.getBykey(key);
		return config.getValue();
	}
	
	
	/**
	 * 更新一个配置信息
	 * @param key
	 * @param value
	 */
	public void update(String key,String value){
		Config config = dao.getBykey(key);
		config.setValue(value);
		dao.update(config);
	}
	
	
	/**
	 * 获取预算
	 * @return
	 */
	public int getIntBudget(){
		return Integer.parseInt(getValueByKey(ConfigService.budget));
	}
	
	
	
}
















