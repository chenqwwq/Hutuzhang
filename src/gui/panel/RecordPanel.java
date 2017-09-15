package gui.panel;
/*
 * 消费记录面板
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;

import entity.Category;
import gui.listener.RecordListener;
import gui.model.CategoryComboBoxModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

public class RecordPanel extends WorkingPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//加载皮肤
	static{
		GUIUtil.useLNF();
	}
	
	public static RecordPanel instance = new RecordPanel();
	
	//初始化组件
	public JLabel lSpend = new JLabel("花费(￥)");
	public JLabel lCategory = new JLabel("分类");
	public JLabel lComment = new JLabel("备注");
	public JLabel lDate = new JLabel("日期");
	
	public JTextField tfSpend = new JTextField("0");
	
	public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
	public JComboBox<Category> cbCategory = new JComboBox<Category>(cbModel);
	public JTextField tfComment = new JTextField();
	public JXDatePicker datepick = new JXDatePicker(new Date());
	
	JButton bSubmit = new JButton("记一笔");
	
	private RecordPanel(){
		GUIUtil.setColor(ColorUtil.grayColor, lSpend,lCategory,lComment,lDate);
		GUIUtil.setColor(ColorUtil.blueColor, bSubmit);
		JPanel pInput =new JPanel();
		JPanel pSubmit = new JPanel();
		int gap = 40;
		pInput.setLayout(new GridLayout(4,2,gap,gap));
		pInput.add(lSpend);
		pInput.add(tfSpend);
		pInput.add(lCategory);
		pInput.add(cbCategory);
		pInput.add(lComment);
		pInput.add(tfComment);
		pInput.add(lDate);
		pInput.add(datepick);

		pSubmit.add(bSubmit);

		this.setLayout(new BorderLayout());
		this.add(pInput,BorderLayout.NORTH);
		this.add(pSubmit,BorderLayout.CENTER);
		
		
		//添加监听
		addListener();
	}
	
//	
//	/**
//	 * 获取jComboBox中选中的选项
//	 * @param agrs
//	 */
//	public Category getSelectedCategory(){
//		return (Category) cbCategory.getSelectedItem();
//	}
	
	public static void main(String[] agrs){
		GUIUtil.showPanel(RecordPanel.instance);
	}

	@Override
	public void updateDate() {
		//重新赋值cbModel中的cs数组
		cbModel.cs = new CategoryService().getCategoryWithRecordNumber();
		//重新渲染
		cbCategory.updateUI();
		
		//重置其他的控件
		tfSpend.setText("");
		tfComment.setText("");
		if(0 != cbModel.getSize()){
			cbCategory.setSelectedItem(0);
		}
		
		datepick.setDate(new Date());
	}

	@Override
	public void addListener() {
		RecordListener listener = new RecordListener();
		
		bSubmit.addActionListener( listener);
	}
}
