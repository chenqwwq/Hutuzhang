package service;
/**
 * 记一笔界面
 * 设置监听器 获取界面上输入的内容
 * 		 并调用dao层方法将输入的内容存入数据库
 */

import java.util.Date;

import dao.RecordDAO;
import entity.Category;
import entity.Record;

public class RecordService {
	
	
	/**
	 * 向数据库存储一条消费记录
	 * @param record
	 */
	public void add(int spend, Category c, String comment,Date date){
		new RecordDAO().add(new Record(spend,c.getId(),comment,date));
	}

}
