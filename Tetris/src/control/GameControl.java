package control;

import service.GameService;
import ui.JPanelGame;

/**
 * 接受玩家键盘时间嘛 接受画面 控制游戏逻辑
 * 
 */
public class GameControl {

	/**
	 * 游戏界面层
	 */
	private JPanelGame panelGame;
	/**
	 * 游戏逻辑层
	 */
	private GameService gameService;

	public GameControl(JPanelGame panelGame, GameService gameService) {
		this.panelGame = panelGame;
		this.gameService = gameService;
	}

	/**
	 * 控制器方向键（上）
	 */
	public void keyUp() {
		this.gameService.keyUp();
		this.panelGame.repaint();
	}

	/**
	 * 控制器方向键（下）
	 */
	public void keyDown() {
		this.gameService.keyDown();
		this.panelGame.repaint();
	}

	/**
	 * 控制器方向键（左）
	 */
	public void keyLeft() {
		this.gameService.keyLeft();
		this.panelGame.repaint();
	}

	/**
	 * 控制器方向键（右）
	 */
	public void keyRight() {
		this.gameService.keyRight();
		this.panelGame.repaint();
	}
}
