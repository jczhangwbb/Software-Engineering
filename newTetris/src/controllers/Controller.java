/**
 * 
 */
package controllers;

import javax.swing.*;

import models.Shape;

import java.awt.*;

import views.GameBoard;
/**
 * @author Dengpengyu
 *
 */
public class Controller {
	
	private GameBoard gameBoard;
	private int boardWidth;
	private int boardHeight;
	private boolean isFallingFinished = false;
	private boolean isStarted = false;
	private boolean isPaused = false;

	private int numLinesRemoved = 0;
	private int currentX = 0;
	private int currentY = 0;
	private Timer timer;

	private Shape currentPiece;
	private Shape.Tetrominoes[] board;

	public Controller(int boardWidth, int boardHeight, GameBoard gameBoard) {
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		this.gameBoard = gameBoard;
		currentPiece = new Shape();
		timer = new Timer(400, gameBoard);
		timer.start();
		board = new Shape.Tetrominoes[boardWidth * boardHeight];

		clearBoard();
	}

	public void gameAction() {
		if (isFallingFinished) {
			isFallingFinished = false;
			newPiece();
		} else {
			oneLineDown();
		}
	}

	public boolean isStarted() {
		return isStarted;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public boolean isCurrentPieceEmpty() {
		return currentPiece.getPieceShape() == Shape.Tetrominoes.Empty;
	}

	public void start() {
		if (isPaused)
			return;
		isStarted = true;
		isFallingFinished = false;
		numLinesRemoved = 0;
		clearBoard();
		newPiece();
		timer.start();

	}

	public void pause() {

	}

	public void oneLineDown() {
		if (!tryMove(currentPiece, currentX, currentY - 1))
			pieceDropped();
	}

	private void clearBoard() {  //清板
		for (int i = 0; i < boardHeight * boardWidth; ++i)
			board[i] = Shape.Tetrominoes.Empty;
	}

	public void dropDown() {  //下落
		int newY = currentY;
		while (newY > 0) {
			if (!tryMove(currentPiece, currentX, newY - 1))
				break;
			--newY;
		}
		pieceDropped();
	}

	private Shape.Tetrominoes shapeAt(int x, int y) {
		return board[(y * boardWidth) + x];
	}

	public void paint(Graphics g, double width, double height) {  //繪製

	}

	private void newPiece() { //新產生的方塊

	}

	private boolean tryMove(Shape newPiece, int newX, int newY) {  //移動
		for (int i = 0; i < 4; ++i) {
			int x = newX + newPiece.x(i);
			int y = newY - newPiece.y(i);
			if (x < 0 || x >= boardWidth || y < 0 || y >= boardHeight)
				return false;
			if (shapeAt(x, y) != Shape.Tetrominoes.Empty)
				return false;
		}

		currentPiece = newPiece;
		currentX = newX;
		currentY = newY;
		gameBoard.repaint();
		return true;
	}

	private void pieceDropped() { 

	}

	private void removeFullLines() { //消行方法
		int numFullLines = 0;

		for (int i = boardHeight - 1; i >= 0; --i) {
			boolean lineIsFull = true;

			for (int j = 0; j < boardWidth; ++j) {
				if (shapeAt(j, i) == Shape.Tetrominoes.Empty) {
					lineIsFull = false;
					break;
				}
			}

			if (lineIsFull) {
				++numFullLines;
				for (int k = i; k < boardHeight - 1; ++k) {
					for (int j = 0; j < boardWidth; ++j)
						board[(k * boardWidth) + j] = shapeAt(j, k + 1);
				}
			}
		}

		if (numFullLines > 0) {
			numLinesRemoved += numFullLines;
			isFallingFinished = true;
			currentPiece.setPieceShape(Shape.Tetrominoes.Empty);
			gameBoard.repaint();
		}
	}


	public void moveLeft() { //向左移動方塊的方法
	}

	public void moveRight() { //向右移動方塊的方法
	}

	public void rotate() { //旋轉方塊的方法
	}
}

