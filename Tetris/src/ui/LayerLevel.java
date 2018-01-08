package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerLevel extends Layer {

	/**
	 * 标题图片
	 */

	/**
	 * 数字图片 260 36
	 */
	private static final Image IMG_NUMBER = new ImageIcon("graphics/string/num.png").getImage();

	/**
	 * 数字切片的宽度
	 */
	private static final int IMG_NUMBER_W = IMG_NUMBER.getWidth(null) / 10;

	/**
	 * 数字切片的高度
	 */
	private static final int IMG_NUMBER_H = IMG_NUMBER.getHeight(null);

	public LayerLevel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		// 窗口标题
//		int centerX = this.w - IMG_LV.getWidth(null) >> 1;
		// 显示等级
		this.drawNumber(16, 64, 1, 5, g);
	}

	/**
	 * 显示数字
	 * 
	 * @param x 左上角坐标
	 * @param y
	 * @param num  要显示的的数字
	 * @param maxBit 数字位数
	 * @param g 画笔对象
	 */
	private void drawNumber(int x, int y, int num, int maxBit, Graphics g) {
		// 把要打印的数字转换成字符串
		String strNum = Integer.toString(num);
		// 循环绘制数字右对齐
		for (int i = 0; i < maxBit; i++) {
			// 判断是否满足绘制条件
			if(maxBit - i <= strNum.length()) {
				// 获得数字在字符串的下标
				int idx = i - maxBit + strNum.length();
				// 把数字number中的每一位取出
				int bit = strNum.charAt(idx) - '0';
				// 绘制数字
				g.drawImage(IMG_NUMBER, 
					this.x + x + IMG_NUMBER_W * i, this.y + y, 
					this.x + x + IMG_NUMBER_W * (i + 1), this.y + y + IMG_NUMBER_H, 
					bit * IMG_NUMBER_W, 0, (bit + 1) * IMG_NUMBER_W, IMG_NUMBER_H, null);
			}
			
		}

	}
}
