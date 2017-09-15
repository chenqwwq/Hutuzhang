package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import entity.Category;
import gui.panel.CategoryPanel;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import gui.panel.SpendPanel;
import service.RecordService;
import util.GUIUtil;

/**
 * “记一笔”的按钮监听
 * 
 * @author Administrator
 *
 */

public class RecordListener implements ActionListener{
	
	
	/**
	 * 1）首先判断是否有分类信息，如果没有则提示需要先增加消费类别
	 * 2）输入的金额不能为0
	 * 3）调用业务类(Service)中的add函数增加记录
	 * 4）提示成功，并用show跳转到“消费一览”界面
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//获取面板类的实例
		RecordPanel panel = RecordPanel.instance;
		//判断是否有分类信息
		if(0 == panel.cbModel.getSize()){
			JOptionPane.showMessageDialog(panel, "暂无类别信息，请先添加");
			//跳转到添加界面
			MainPanel.instance.workingPanel.show(CategoryPanel.instance);
			return;
		}
		
		//判断输入金额是否有效
		if(!GUIUtil.checkZero(panel.tfSpend, "消费金额")){
			JOptionPane.showMessageDialog(panel, "请输入有效金额");
			return;
		}
		
		//获取消费金额
		int spend = Integer.parseInt(panel.tfSpend.getText());
		//获取消费类别
		Category c = (Category) panel.cbCategory.getSelectedItem();
		/**
		 * 由于默认选择了消费类型 所有不存在没有选择消费类型的情况
		 */
//		if(c == null){
//			JOptionPane.showMessageDialog(panel, "请先选择类型");
//		}
		//获取消费备注 不去空格
		String comment = panel.tfComment.getText();
		//获取时间
		Date date = panel.datepick.getDate();
		
		new RecordService().add(spend, c, comment, date);
		//跳转到消费一览界面
		MainPanel.instance.workingPanel.show(SpendPanel.instance);
		
			
	}
	
	

}
