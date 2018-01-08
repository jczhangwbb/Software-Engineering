package service;

import java.awt.Point;
import java.util.Random;

import javax.swing.text.Document;

import dto.GameDto;
import entity.GameAct;

public class GameService {

	/**
	 * 
	 */
	private GameDto dto;
	/**
	 * 随机数生成数
	 */
	private Random random = new Random();
	/**
	 * 方块种类个数
	 */
	private static final int MAX_TYPE = 6;

	public GameService(GameDto dto) {
		this.dto = dto;
		GameAct act = new GameAct(random.nextInt(MAX_TYPE));
		dto.setGameAct(act);
	}

	/**
	 * 方块操作（上）
	 */
	public void keyUp() {
		this.dto.getGameAct().round(this.dto.getGameMap());
	}

	/**
	 * 方块操作（下）
	 */
	public void keyDown() {
		// 方块向下移动，并判断是否移动成功
		if (this.dto.getGameAct().move(0, 1, this.dto.getGameMap())) {
			return;
		}
		// 获得游戏地图对象
		boolean[][] map = this.dto.getGameMap();
		// 获得方块对象
		Point[] act = this.dto.getGameAct().getActPoints();
		// 将方块堆积到地图数组
		for (int i = 0; i < act.length; i++) {
			map[act[i].x][act[i].y] = true;
		}
		// TODO 判断是否可以消行
		// TODO 消行操作
		// TODO 算分操作
		// TODO 判断是痘可以升级
		// TODO 升级
		// 创建下一个方块
		this.dto.getGameAct().init(this.dto.getNext());
		// 随机生成下一个方块
		this.dto.setNext(random.nextInt(MAX_TYPE));
	}

	/**
	 * 方块操作（左）
	 */
	public void keyLeft() {
		this.dto.getGameAct().move(-1, 0, this.dto.getGameMap());
	}

	/**
	 * 方块操作（右）
	 */
	public void keyRight() {
		this.dto.getGameAct().move(1, 0, this.dto.getGameMap());
	}

}
