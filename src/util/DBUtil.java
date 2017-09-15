package util;
/**
 * 数据库工具类
 * 1）提供一个获取数据库连接的方法
 * 2)关闭连接的操作在DAO层实现
 * */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String driver = "com.mysql.jdbc.Driver";
//	private static final String url = "jdbc:mysql://127.0.0.1:3306/hutubill?useSSL=false&characterEncoding=utf-8";
	static String setSSL = "false";
	static String ip = "127.0.0.1";
	static int port = 3306;
	static String database = "hutubill";
	static String encoding = "UTF-8";
	static String loginName = "root";
	static String password = "19951217";
	
	/**
	 * 在静态代码块中加载驱动 
	 */
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("驱动加载失败");
		}
	}
	
	
	/**
	 * 返回一个连接 若连接创建失败则返回一个空值
	 * @return
	 */
	public static Connection getConnection(){
			try {
				String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s&useSSL=%s", ip, port, database, encoding,setSSL);
				return DriverManager.getConnection(url, loginName, password);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	
	

}
