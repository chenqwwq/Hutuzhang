package service;

import java.util.Collections;
import java.util.List;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;

public class CategoryService {
	//获取消费分类的DAO层实例
	CategoryDAO categoryDAO = new CategoryDAO();
	/**
	 * 获取RecordDAO的实例
	 * 在获取的消费类别的id（消费记录的cid）后获取每个类别的消费次数
	 */
	RecordDAO recordDAO = new RecordDAO();
	
	
	/**
	 * 获取全部的消费类别信息
	 * 1）从消费类别的表中获取全部的消费类别信息（此时并没有每种类别的消费次数信息）
	 * 2）遍历消费类别 以消费类别为条件查询消费记录表 并计算大小 
	 * @return
	 */
	public List< Category > getCategoryWithRecordNumber(){
		//获取所有的消费分类记录
		List< Category > cs = new CategoryDAO().getCategory();
		
		//遍历所有的消费分类
		for (Category category : cs) {
			//根据消费分类id查询消费记录
			List< Record > rs = recordDAO.getRecord(category.getId());
			category.setRecordNumber(rs.size());
		}
		
		Collections.sort(cs,(c1,c2)->c2.getRecordNumber()-c1.getRecordNumber());
		return cs;
		
	}
	
	/**
	 * 增加一种消费类别
	 * @param name
	 */
	public void add(String name){
		categoryDAO.add(new Category(name));
	}
	
	
	/**
	 * 更新一种消费类别
	 * @param id
	 * @param name
	 */
	public void update(int id,String name){
		Category c  = new Category(name);
		c.setId(id);
		categoryDAO.update(c);
	}
	
	
	/**
	 * 根据id删除一个消费类别
	 * @param id
	 */
	public void delete(int id){
		categoryDAO.deleteById(id);
	}
	

}




























