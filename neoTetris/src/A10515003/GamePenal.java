package A10515003;
import javax.swing.*;

import controllers.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePenal extends JPanel implements ActionListener {
	private final int BOARD_WIDTH = 14;
	private final int BOARD_HEIGHT = 30;
	private JLabel statusBar;
	private JLabel scoreBar;
	private JLabel jlTimer=new JLabel();
	private Timer timer;
	private Controller controller;

	public GamePenal(TetrisFrame frame) {
		setFocusable(true);
		setBackground(Color.lightGray);
		controller = new Controller(BOARD_WIDTH, BOARD_HEIGHT, this);
		scoreBar = frame.getScoreBar();
		addKeyListener(new TAdapter());
	}

	public void start() {
		controller.start();
	}

	public void TimerText() {
		// TODO Auto-generated constructor stub
		setSize(50,50);
		add(jlTimer);
		//设置Timer定时器并启动
		timer=new Timer(500,this);
		timer.start();
		setVisible(true);
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
		Color colors[] = 
			{ 
    			new Color(0, 0, 0), 
    			new Color(167, 34, 48), //Z形方塊 murasaki
    			new Color(167, 203, 48), //S形方塊 grass green
    			new Color(59, 203, 163),//I（Line）形方塊 light blue
    			new Color(14, 65, 34), //T形方塊 dark green
    			new Color(156, 65, 114),//方形方塊 brown red
    			new Color(156, 152, 114), //L形方塊 grey
    			new Color(248, 241, 114)//反L形方塊 yellow
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
	
	public void setScoreText(String text) {
		scoreBar.setText(text);
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
				controller.goLeft();//向左按鍵觸發事件
				break;
			case KeyEvent.VK_RIGHT:
				controller.goRight();//向右按鍵觸發事件
				break;
			case KeyEvent.VK_DOWN:
				controller.goDown();//向下按鍵觸發事件
				break;
			case KeyEvent.VK_UP:
				controller.rotate();//向上按鍵觸發事件
				break;
			case KeyEvent.VK_SPACE:
				controller.speedUp();//空格按鍵觸發事件
				break;
			case KeyEvent.VK_P:
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}
}
