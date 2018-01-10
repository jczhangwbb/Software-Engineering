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
	
}
