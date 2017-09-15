package entity;
/**
 * 配置信息类
 * @author Administrator
 *
 */

public class Config {
	private int id;
	private String key;
	private String value;
	
	
	/**
	 * 构造函数
	 */
	public Config(){}
	
	public Config(int id,String key,String value){
		this.id = id;
		this.key = key;
		this.value = value;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
