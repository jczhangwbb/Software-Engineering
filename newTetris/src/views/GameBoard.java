package views;

import javax.print.attribute.PrintJobAttributeSet;
import javax.swing.*;

import controllers.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameBoard extends JPanel implements ActionListener {
	private final int BOARD_WIDTH = 10;
	private final int BOARD_HEIGHT = 22;

	private Controller controller;

	public GameBoard(Frame parent) {
		setFocusable(true);
		controller = new Controller(BOARD_WIDTH, BOARD_HEIGHT, this);
		addKeyListener(new TAdapter());
	}

	public void start() {
		controller.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.gameAction();
	}

	public void paint(Graphics g) {
		super.paint(g);
		controller.paint(g, getSize().getWidth(), getSize().getHeight());

	}

	private int squareWidth() {
		return (int) getSize().getWidth() / BOARD_WIDTH;
	}

	private int squareHeight() {
		return (int) getSize().getHeight() / BOARD_HEIGHT;
	}

	public void drawSquare(Graphics g, int x, int y, models.Shape.Tetrominoes shape) {
		Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), new Color(102, 204, 102),
				new Color(102, 102, 204), new Color(204, 204, 102), new Color(204, 102, 204), new Color(102, 204, 204),
				new Color(218, 170, 0) };

		Color color = colors[shape.ordinal()];

		g.setColor(color);
		g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

		g.setColor(color.brighter());
		g.drawLine(x, y + squareHeight() - 1, x, y);
		g.drawLine(x, y, x + squareWidth() - 1, y);

		g.setColor(color.darker());
		g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
		g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);
	}


	private class TAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {

			if (!controller.isStarted() || controller.isCurrentPieceEmpty()) {
				return;
			}

			int keycode = e.getKeyCode();

			if (keycode == 'p' || keycode == 'P') { //按p暫停 （大小寫都可以）
				controller.pause();
				return;
			}

			if (controller.isPaused())
				return;

			switch (keycode) { //按鍵觸發事件
			case KeyEvent.VK_LEFT:
				controller.moveLeft();//向左按鍵觸發事件
				break;
			case KeyEvent.VK_RIGHT:
				controller.moveRight();//向右按鍵觸發事件
				break;
			case KeyEvent.VK_DOWN:
				controller.dropDown();//向下按鍵觸發事件
				break;
			case KeyEvent.VK_UP:
				controller.rotate();//向上按鍵觸發事件
				break;
			case KeyEvent.VK_SPACE:
				controller.oneLineDown();//空格按鍵觸發事件
				break;

			}

		}
	}
}
