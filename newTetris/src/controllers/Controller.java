/**
 * 
 */
package controllers;


import javax.swing.*;

import models.PieceType;
import views.GameBoard;

import java.awt.*;

/**
 * @author Dengpengyu
 *
 */
public class Controller {

//	private GamePanel gamePanel;
//	private static Gameboard gameboard;
//	private int Blocks;

	private GameBoard gameBoard;
	private int score = 0;
	private int nowX = 0;
	private int nowY = 0;
	private Timer timer;
	private int penalWidth;
	private int penalHeight;
	private boolean isReachBottom = false;
	private boolean isRunning = false;
	private boolean isStoped = false;
	private PieceType nowPiece;
	private PieceType.AllType[] board;
	
	public Controller(int penalWidth, int penalHeight, GameBoard gameBoard) {
		this.penalWidth = penalWidth;
		this.penalHeight = penalHeight;
		this.gameBoard = gameBoard;
		nowPiece = new PieceType();
		timer = new Timer(500, gameBoard); // 下落速度
		timer.start();
		board = new PieceType.AllType[penalWidth * penalHeight];
		clearAll();
	}
	
	private boolean tryMove(PieceType newPiece, int newX, int newY) {
		for (int i = 0; i < 4; ++i) {
			int x = newX + newPiece.x(i);
			int y = newY - newPiece.y(i);
			if (x < 0 || x >= penalWidth || y < 0 || y >= penalHeight)
				return false;
			if (PieceAt(x, y) != PieceType.AllType.Empty)
				return false;
		}
		//this.nowpiece = newPiece;
		//this.nowX = newX;
		//this.nowY = newY;
		nowPiece = newPiece;
		nowX = newX;
		nowY = newY;
		gameBoard.repaint();
		return true;
	}
	public void goLeft() { // 向左移動方塊的方法
		tryMove(nowPiece, nowX - 1, nowY);
	}
	public void goRight() { // 向右移動方塊的方法
		tryMove(nowPiece, nowX + 1, nowY);
	}
	public void rotate() { // 旋轉方塊的方法
		tryMove(nowPiece.rotate(), nowX, nowY);
	}
	public void gameAction() {
		if (isReachBottom) {
			isReachBottom = false;
			newPiece();
		} else {
			speedUp();
		}
	}
	public boolean isStarted() {
		return isRunning;
	}
	public boolean isPaused() {
		return isStoped;
	}
	public boolean isCurrentPieceEmpty() {
		return nowPiece.getPieceShape() == PieceType.AllType.Empty;
	}
	public void start() {
		if (isStoped)
			return;
		isRunning = true;
		isReachBottom = false;
		score = 0;
		clearAll();
		newPiece();
		timer.start();
	}
	
//	public void newGame()
//{
//	gameBoard = new GameBoadrd(this);
//	//System.out.println("newgame"+ score);
//	gameBoard.start;
//}
	
}
