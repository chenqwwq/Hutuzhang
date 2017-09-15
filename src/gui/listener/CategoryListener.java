package gui.listener;
/**
 * 消费分类界面的监听
 * 		在监听器中识别点击的按钮，而不是分别设置监听
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import entity.Category;
import gui.panel.CategoryPanel;
import service.CategoryService;

public class CategoryListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		CategoryPanel p = CategoryPanel.instance;
		
		//获取触发源的按钮
		JButton button = (JButton)e.getSource();
		if(button == p.bAdd){
			String name = JOptionPane.showInputDialog(null);
			//预防输入为空的错误
			if(0 == name.length()){
				JOptionPane.showMessageDialog(p, "分类名称不能为空");
				return;
			}
			new CategoryService().add(name);
			
		}
		
		if(button == p.bEdit){
			//获取被选中的消费类别
			Category c = p.getSelectCategory();
			/*********************/
			String name = JOptionPane.showInputDialog("修改分类名称",c.getName());
			if(0 == name.length()){
				JOptionPane.showMessageDialog(p, "分类名称不能为空");
				return;
			}
			new CategoryService().update(c.getId(), name);
		}
		
		if(button == p.bDelete){
			//获取想要删除的分类
			Category c = p.getSelectCategory();
			
			//判断该类消费次数 若有存在消费则不能删除
			if(0 != c.getRecordNumber() ){
				JOptionPane.showMessageDialog(p, "该分类有消费记录不能删除");
				return;
			}
			//重复确认是否要删除
			if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确认要删除？"))
				return;
			
			new CategoryService().delete(c.getId());
		}
		
		p.updateDate();
		
	}
	

}
