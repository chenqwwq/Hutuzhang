package entity;
/**
 * 消费类别类
 * @author Administrator
 *
 */

public class Category {
	private int id;                       //主键
	private String name;                   //消费类别名称
	
	public Category() {}
	
	public Category(String name){
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRecordNumber() {
		return recordNumber;
	}
	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}
	
	private int recordNumber;            //该类消费次数
	
	
}
