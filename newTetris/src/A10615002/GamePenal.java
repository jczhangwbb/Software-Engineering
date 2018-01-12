package A10615002;

import controllers.Controller;

import javax.print.attribute.PrintJobAttributeSet;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePenal extends JPanel implements ActionListener {
	private final int BOARD_WIDTH = 10;
	private final int BOARD_HEIGHT = 20;
	private JLabel statusBar;

	private JLabel lscore;
	private JLabel llevel;

	private Controller controller;

	GamePenal(Frame parent) {
		setFocusable(true);
		controller = new Controller(BOARD_WIDTH, BOARD_HEIGHT, this);
		statusBar = parent.getStatusBar();
		lscore = parent.getlScore();
		llevel = parent.getlLevel();
		addKeyListener(new TAdapter());
		this.setBorder(new LineBorder(new Color(255, 255, 0), 1, true));
		this.setOpaque(false);
	}

	void start() {
		controller.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.gameAction();
	}

	public void setScoreText(String text) {
		lscore.setText("分数:" + text);

	}
	
	public void setLevelText(String text) {
		llevel.setText("等级:"+ text);
	}

	public void setHighestScoreText(String text) {
		
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

	public void drawPiece(Graphics g, int x, int y, models.PieceType.AllType shape) {
		Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), // Z形方塊 紅色
				new Color(102, 204, 102), // S形方塊 綠色
				new Color(102, 102, 204), // I（Line）形方塊 藏藍
				new Color(204, 204, 102), // T形方塊 黃色
				new Color(204, 102, 204), // 方形方塊 桃紅
				new Color(102, 204, 204), // L形方塊 天藍
				new Color(218, 170, 0)// 反L形方塊 深黃
		};

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

	public void setStatusText(String text) {
		statusBar.setText(text);
	}

	private class TAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {

			if (!controller.isStarted() || controller.isCurrentPieceEmpty()) {
				return;
			}

			int keycode = e.getKeyCode();

			if (keycode == 'p' || keycode == 'P') {
				controller.pause();
				return;
			}

			if (controller.isPaused())
				return;

			switch (keycode) {
			case KeyEvent.VK_LEFT:
				controller.goLeft();
				break;
			case KeyEvent.VK_RIGHT:
				controller.goRight();
				break;
			case KeyEvent.VK_DOWN:
				controller.goDown();
				break;
			case KeyEvent.VK_UP:
				controller.rotate();
				break;
			case KeyEvent.VK_SPACE:
				controller.speedUp();
				break;

			}

		}
	}
}
