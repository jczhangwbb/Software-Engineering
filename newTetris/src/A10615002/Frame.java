package A10615002;

import javax.swing.*;
import javax.swing.border.LineBorder;

import A10615002.Frame;

import java.awt.*;
import java.util.logging.Level;

public class Frame extends JFrame {

	private GamePenal gameBoard;
	private Next next;
	private Score score;
	private Background background;

	private JLabel statusBar;
	public JLabel lScore;
	public JLabel lLevel;
	public JLabel lhign;

	private int num_score = 0;
	private int num_level = 1;

	public Frame() {
		setTitle("俄罗斯方块");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(604, 625);
		setResizable(false);
		// 居中
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = screen.width - this.getWidth() >> 1;
		int y = (screen.height - this.getHeight() >> 1) - 32;
		this.setLocation(x, y);

		// 窗体可见
		setVisible(true);

		next = new Next();
		next.setBounds(1, 200, 150, 150);

		score = new Score();
		score.setBounds(453, 200, 150, 150);

		background = new Background();
		background.setBounds(0, 0, 604, 625);

		gameBoard = new GamePenal(this);
		gameBoard.setBounds(152, 0, 300, 600);

		this.setLayout(null);
		this.getContentPane().add(gameBoard);
		this.getContentPane().add(next);
		this.getContentPane().add(score);
		this.getContentPane().add(background);

	}

	public void init() {
		gameBoard.start();
		setPreferredSize(new Dimension(400, 800));
	}

	public JLabel getStatusBar() {
		return statusBar;
	}

	public JLabel getlLevel() {
		return lLevel;
	}

	public JLabel getlScore() {
		return lScore;
	}

	public void setlScore(JLabel lScore) {
		this.lScore = lScore;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame game = new Frame();
					game.init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	class Next extends JPanel {

		private JLabel lNext;

		public Next() {
			setLayout(null);
			lNext = new JLabel("下一个");
			lNext.setFont(new Font("宋体", 1, 15));
			lNext.setForeground(Color.WHITE);
			lNext.setBounds(10, 10, 50, 20);
			this.add(lNext);
			this.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			this.setOpaque(false);
		}
		
		public void paint(Graphics g) {
			for (int i = 0; i < 16; i++) {
				g.setColor(Color.GRAY);
				g.fill3DRect((i % 4) * 35, (i / 4) * 35, 35, 35, true);
			}

		}

	}

	class Score extends JPanel {
		public Score() {
			setLayout(null);
			statusBar = new JLabel("Gaming");
			lScore = new JLabel("分数: " + num_score);
			lLevel = new JLabel("等级: " + num_level);
			lScore.setFont(new Font("宋体", 1, 15));
			lScore.setForeground(Color.WHITE);
			lScore.setBounds(10, 20, 60, 20);
			lLevel.setFont(new Font("宋体", 1, 15));
			lLevel.setForeground(Color.WHITE);
			lLevel.setBounds(10, 60, 60, 20);
			statusBar.setBounds(10, 100, 50, 20);
			this.add(lScore);
			this.add(lLevel);
			this.add(statusBar);
			this.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			this.setOpaque(false);
		}
	}

	class Background extends JPanel {

		private Image IMG_GB_TEMP = new ImageIcon("background/bg01.jpg").getImage();

		public void paint(Graphics g) {
			g.drawImage(IMG_GB_TEMP, 0, 0, 604, 625, null);
		}

		public Background() {
		}
	}

}
