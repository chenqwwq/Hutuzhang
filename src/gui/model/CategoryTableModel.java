package gui.model;


import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import entity.Category;
import service.CategoryService;

public class CategoryTableModel implements TableModel{
	
	String[] columnNames = new String[]{"分类名称","消费次数"};
	public List< Category > cs = new CategoryService().getCategoryWithRecordNumber();
	
//	public CategoryTableModel(){
//		cs = new CategoryService().getCategoryWithRecordNumber();
//	}
	
	
	
//	/**
//	 * 根据位置获取消费类别的名字
//	 * @param index
//	 * @return
//	 */
//	public Category getCategory(int index){
//		return cs.get(index);
//	}


	@Override
	public int getRowCount() {
		return cs.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Category h = cs.get(rowIndex);
		if(0 == columnIndex){
			return h.getName();
		}
		if(1 == columnIndex)
			return h.getRecordNumber();
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

}
