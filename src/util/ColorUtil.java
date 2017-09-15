package util;

import java.awt.Color;

public class ColorUtil {
	/**
	 * 预先定义的几种常用颜色
	 */
	public static Color blueColor = Color.decode("#3399FF");         //蓝色
 	public static Color grayColor = Color.decode("#999999");          //灰色
	public static Color backgroundColor = Color.decode("#eeeeee");	   //背景色
	public static Color warningColor = Color.decode("#FF3333");      //红色 警告处使用
	
	
	
	/**
	 * 根据进度显示不同的颜色
	 * 当进度接近0时 显示绿色
	 * 当进度接近100的时候 就会显示红色
	 * 并在不同的颜色间过渡
	 * @param per
	 * @return
	 */
	public static Color getByPercentage(int per){
		if(per > 100)
			per = 100;
		int r = 51;
		int g = 255;
		int b = 51;
		
		float rate = per/100f;
		r = (int)(204 * rate + 51);
		g = 255 - r +51;
		Color color = new Color(r,g,b);
		return color;
	}
}

