package ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.xml.bind.annotation.XmlAccessOrder;

import org.omg.CORBA.PRIVATE_MEMBER;

public class LayerGame extends Layer {

	private static final Image ACT = new ImageIcon("graphics/game/rect.png").getImage();
	// TODO 配置文件
	/**
	 * 左位移偏移量
	 */
	private static final int SIZE_ROL = 5;

	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		// 获得方块数组集合
		Point[] points = this.dto.getGameAct().getActPoints();
		// 获得方块类型编号(0~6)
		int typeCode = this.dto.getGameAct().getTypeCode();
		// 打印方块
		for (int i = 0; i < points.length; i++) {
			drawActByPoint(points[i].x, points[i].y, typeCode + 1, g);
		}
		// 打印地图
		boolean[][] map = this.dto.getGameMap();
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				if (map[x][y]) {
					drawActByPoint(x, y, 0, g);
				}
			}
		}

	}
	
	private void drawActByPoint(int x, int y, int imgIdx, Graphics g) {
		g.drawImage(ACT, 
				this.x + (x << SIZE_ROL) + 7, 
				this.y + (y << SIZE_ROL) + 7,
				this.x + (x + 1 << SIZE_ROL) + 7, 
				this.y + (y + 1 << SIZE_ROL) + 7,
				imgIdx << SIZE_ROL, 0, (imgIdx + 1) << SIZE_ROL, 1 << SIZE_ROL, null);
	}

}
