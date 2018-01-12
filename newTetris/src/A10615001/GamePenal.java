package A10615001;

import controllers.Controller;

import javax.print.attribute.PrintJobAttributeSet;
import javax.swing.*;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePenal extends JPanel implements ActionListener {
	private final int BOARD_WIDTH = 10;
	private final int BOARD_HEIGHT = 22;
	private JLabel statusBar;
	private JButton pauseButton;
	private JButton bstart;
	private JButton bstop;
	private Image img ;
	
	private Controller controller;
	GamePenal(Frame parent) {
		setFocusable(true);
		img = Toolkit.getDefaultToolkit().getImage("\bg.jpg");
		controller = new Controller(BOARD_WIDTH, BOARD_HEIGHT, this);
		statusBar = parent.getStatusBar();
		addKeyListener(new TAdapter());
	}
	
	void start() {
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

	public void drawPiece(Graphics g, int x, int y, models.PieceType.AllType shape) {
		Color colors[] = { new Color(0, 0, 0), new Color(0, 255, 255), // Z形方塊
				new Color(0, 204, 255), // S形方塊
				new Color(0, 153, 255), // I（Line）形方塊
				new Color(0, 102, 255), // T形方塊
				new Color(0, 51, 255), // 方形方塊
				new Color(0, 53, 98), // L形方塊
				new Color(0, 0, 255)// 反L形方塊
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
			int keycode = e.getKeyCode();

			if (!controller.isStarted() || controller.isCurrentPieceEmpty()) {
				return;
			}


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
