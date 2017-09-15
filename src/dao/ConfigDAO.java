package dao;
/**
 * 配置信息的DAO类
 * 保存配置信息数据库和类的RAM映射
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import entity.Config;
import util.DBUtil;

public class ConfigDAO {
	
	private static final String SQL_ADD = "INSERT INTO config VALUES(DEFAULT,?,?);";
	private static final String SQL_UPDATE = "UPDATE config SET key_=?,value=? WHERE id=?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM config WHERE id = ?";
	private static final String SQL_GET = "SELECT * FROM config WHERE id=?";
	private static final String SQL_GET_ALL_WITH_PAGE = "SELECT * FROM config order by id desc limit ?,?";
	private static final String SQL_GET_TOTAL = "SELECT COUNT(*) FROM config";
	private static final String SQL_GET_BY_KEY = "SELECT * FROM config WHERE key_=?";
	
	/**
	 * 增加
	 * @param config
	 */
	public void add(Config config){
		//在try括号里面的连接语句 在结束时和ResultSet对应的Statement会自动关闭 Connection也关闭
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement prep = conn.prepareStatement(SQL_ADD,new String[]{"id"});){
			prep.setString(1, config.getKey());
			prep.setString(2, config.getValue());
			/**
			 * 获取Config对象在数据库里面的id并赋值给Config中的id
			 * 尚不清楚用途
			 */
			if(	prep.executeUpdate()>0){
				ResultSet rs = prep.getGeneratedKeys();
				if(rs.next()){
					int id = rs.getInt(1);
					config.setId(id);
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新
	 * @param config
	 */
	public void update(Config config){
		try(Connection conn =DBUtil.getConnection();
			PreparedStatement prep = conn.prepareStatement(SQL_UPDATE)){
			
			prep.setString(1, config.getKey());
			prep.setString(2, config.getValue());
			prep.setInt(3, config.getId());
			
			prep.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id删除一个对象
	 * @param id
	 */
	public void deleteById(int id){
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(SQL_DELETE_BY_ID)){
			prep.setInt(1, id);
			prep.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id 获取一个Config对象
	 * @param id
	 */
	public Config getById(int id){
		Config config = null;
		
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(SQL_GET)){
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if(rs.next()){
				config = new Config();
				config.setId(id);
				config.setKey(rs.getString("key_"));
				config.setValue(rs.getString("value"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return config;
	}
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Config> getConfig(){
		return getConfig(0, Short.MAX_VALUE);
	}
	
	
	/**
	 * 分页查询
	 * @return
	 */
	public List<Config> getConfig(int start,int count){
		List<Config> configList = new ArrayList<Config>();
		Config config = null;
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(SQL_GET_ALL_WITH_PAGE)){
			prep.setInt(1, start);
			prep.setInt(2, count);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
				config  = new Config();
				config.setId(rs.getInt("id"));
				config.setKey(rs.getString("key_"));
				config.setValue(rs.getString("value"));
				configList.add(config);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return configList;
	}
	
	
	/**
	 * 统计总数
	 * @return
	 */
	public int getTotal(){
		int total = 0;
		try(Connection conn = DBUtil.getConnection();
				Statement state = conn.createStatement()){
			ResultSet rs = state.executeQuery(SQL_GET_TOTAL);
			if(rs.next()){
				total = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return total;
		
	}
	

	/**
	 * 通过key获取Config实例
	 * @param key
	 * @return
	 */
	public Config getBykey(String key){
		Config config = null;
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(SQL_GET_BY_KEY)){
			prep.setString(1, key);
			ResultSet rs = prep.executeQuery();
			
			if(rs.next()){
				config = new Config();
				config.setId(rs.getInt("id"));
				config.setKey(key);
				config.setValue(rs.getString("value"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return config;
	}
}




























