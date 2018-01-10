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
	
	
	
	public void pause() {
		if (!isRunning) {
			return;
		}
		isStoped = !isStoped;
		if (isStoped) {
			timer.stop();
			gameBoard.setStatusText("                Paused");
		} else {
			timer.start();
			gameBoard.setStatusText(String.valueOf("                Gaming"));
		}
		gameBoard.repaint();
	}
	public void speedUp() {
		if (!tryMove(nowPiece, nowX, nowY - 1)) {
			pieceDropped();
		}
	}
	private void clearAll() {
		for (int i = 0; i < penalHeight * penalWidth; ++i)
			board[i] = PieceType.AllType.Empty;
	}
	public void goDown() {
		int newY = nowY;
		while (newY > 0) {
			if (!tryMove(nowPiece, nowX, newY - 1))
				break;
			--newY;
		}
		pieceDropped();
	}
	
	private PieceType.AllType PieceAt(int x, int y) {
		return board[(y * penalWidth) + x];
	}
	public void paint(Graphics g, double width, double height) {
		int squareWidth = (int) width / penalWidth;
		int squareHeight = (int) height / penalHeight;
		int boardTop = (int) height - penalHeight * squareHeight;
		for (int i = 0; i < penalHeight; ++i) {
			for (int j = 0; j < penalWidth; ++j) {
				models.PieceType.AllType shape = PieceAt(j, penalHeight - i - 1);
				//models.PieceType.AllType shape = PieceAt(j, penalHeight - i + 1);
				if (shape != models.PieceType.AllType.Empty)
					gameBoard.drawPiece(g, j * squareWidth, boardTop + i * squareHeight, shape);
			}
		}
		if (nowPiece.getPieceShape() != models.PieceType.AllType.Empty) {
			for (int i = 0; i < 4; ++i) {
				int x = nowX + nowPiece.x(i);
				int y = nowY - nowPiece.y(i);
				gameBoard.drawPiece(g, x * squareWidth, boardTop + (penalHeight - y - 1) * squareHeight,
						nowPiece.getPieceShape());
			}
		}
	}
	
//	public void setSpeed()
//{
//	if(Globle.downTime<=250) return;
//	if(Lines>=6)
//	{
//		Globle.downTime -= 50;
//		Lines -= 6;
//	}
//	
//}
	
}
