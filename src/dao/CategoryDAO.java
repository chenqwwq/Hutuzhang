package dao;
/**
 * 消费类别的DAO层
 * <---------少更新方法 ：4/24   over------------>
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import entity.Category;
import util.DBUtil;

public class CategoryDAO {
	private static final String SQL_GET_TOTAL = "SELECT COUNT(*) FROM category";
	private static final String SQL_ADD = "INSERT INTO category VALUES(DEFAULT,?);";
	private static final String SQL_DELETE_BY_ID ="DELETE FROM category WHERE id = ?";
	private static final String SQL_GET_BY_ID = "SELECT * FROM category WHERE id = ?";
	private static final String SQL_GET_ALL_WITH_PAGE = "SELECT * FROM category ORDER BY id DESC limit ?,?";
	private static final String SQL_UPDATE = "UPDATE category SET name=? WHERE id=?";
	
	/**
	 * 更新一个消费类别
	 * @return
	 */
	public boolean update(Category category){
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(SQL_UPDATE)){
			prep.setString(1, category.getName());
			prep.setInt(2, category.getId());
			
			if(prep.executeUpdate()>0){
				return true;
			}else{
				return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 获取分类总数
	 */
	public int getTotal(){
		int total = 0;
		try(Connection conn = DBUtil.getConnection();
				Statement state = conn.createStatement();){
			ResultSet rs = state.executeQuery(SQL_GET_TOTAL);
			if(rs.next()){
				total = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	
	/**
	 * 增加一条记录
	 */
	public boolean add(Category category){
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(SQL_ADD,new String[]{"id"})){
			prep.setString(1, category.getName());
			
			if(prep.executeUpdate()>0){
				//取得插入的记录的自动生成的id
				ResultSet rs = prep.getGeneratedKeys();
				if(rs.next()){
					category.setId(rs.getInt(1));
				}
			}
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 根据id删除一条记录
	 * @param id
	 * @return
	 */
	public boolean deleteById(int id){
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(SQL_DELETE_BY_ID)){
			prep.setInt(1, id);
			prep.executeUpdate();
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	
	/**
	 * 根据id获取一条分类记录
	 * @param id
	 * @return
	 */
	public Category getById(int id){
		Category category = null;
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(SQL_GET_BY_ID)){
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if(rs.next()){
				category = new Category();
				category.setId(id);
				category.setName(rs.getString("name"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
	
	/**
	 * 以List形式获取全部的分类信息记录  记录中并没有RecordNumber
	 * @return
	 */
	public List<Category> getCategory(){
		return getCategory(0, Short.MAX_VALUE);
	}
	
	
	/**
	 * 分页查询
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Category> getCategory(int start, int count){
		List< Category > categoryList = new ArrayList<Category>();
		
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(SQL_GET_ALL_WITH_PAGE)){
			prep.setInt(1, start);
			prep.setInt(2, count);
			
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				categoryList.add(category);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return categoryList;
	}
	
	
	public static void main(String[] args) {
		CategoryDAO c = new CategoryDAO();
//		System.out.println(c.getTotal());
//		
//		System.out.println(c.add(new Category("酒水")));
//		
//		System.out.println(c.getTotal());
//		
//		List<Category> list = c.getCategory(0, Short.MAX_VALUE);
//		
//		for (Category category : list) {
//			System.out.println(category);
//		}
		
		Category c1 = new Category("旅游");
		c1.setId(1);
		c.update(c1);
//		
	}
	
	
}































