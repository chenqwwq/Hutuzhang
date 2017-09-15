package util;
/**
 * 居中面板
 */

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;

import gui.panel.WorkingPanel;

public class CenterPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double rate;     //拉伸比例
	private JComponent c;      //显示的组件
	private boolean strech;        //是否拉伸

	public CenterPanel(double rate,boolean strech){
		this.setLayout(null);
		this.rate = rate;
		this.strech = strech;
	}
	
	//strech默认为true;
	public CenterPanel(double rate){
		this(rate, true);
	}
	
	public void repaint(){
		if( null != c){
			Dimension containerSize = this.getSize();
			Dimension componentSize = c.getPreferredSize();
			
			if(strech)
				c.setSize((int)(containerSize.width * rate),(int)(containerSize.height * rate));
			else
				c.setSize(componentSize);
			c.setLocation(containerSize.width / 2 - c.getSize().width / 2,
					containerSize.height / 2 - c.getSize().height / 2);
		}
		super.repaint();
	}
	
	
	/**
	 * 显示面板
	 * @param p
	 */
	public void show(JComponent p ){
		this.c = p;
		//获取在显示框架上的全部面板  并移除
		Component[] cs = getComponents();
		for ( Component c : cs ){
			remove(c);
		}
		
		/**
		 * 判断需要显示的面板是否是WorkingPanel
		 * 是 则需要用updateDate()来让界面显示和数据库同步
		 */
		if(p instanceof WorkingPanel){
			((WorkingPanel) p).updateDate();
		}
		add(p);
		
		this.updateUI();
	}
}
