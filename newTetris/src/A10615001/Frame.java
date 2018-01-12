package A10615001;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import A10615001.Frame;
import controllers.Controller;

import java.awt.*;

public class Frame extends JFrame {
	// 声明按钮 开始 、暂停
	private JButton bStart;
	private JButton bPause;
	
	private JLabel statusBar;
	private GamePenal gameBoard;
	private JPanel sidePanel;
	private JPanel container;
	private Controller controller;
	private JTextArea howToPlay;
	private JLabel score;
	

	public JLabel getScore() {
		return score;
	}

	public Frame() {
		
		

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);

		statusBar = new JLabel("Gaming");
		howToPlay = new JTextArea();
		howToPlay.append(" ⬅ : Move Left \r\n ➡ :Move Right \r\n ⬆ : Rotate \r\n ⬇ : dropDown \r\n Space: Speed up");
		howToPlay.setEditable(false);
		
		bStart= new JButton("开始");
		bPause= new JButton("暂停");
		
		score= new JLabel("分数："+ 0);
		gameBoard = new GamePenal(this);

		container = new JPanel();

		setContentPane(container);

		sidePanel = new JPanel();
			

		GroupLayout glcontainer = new GroupLayout(container);
		glcontainer.setHorizontalGroup(glcontainer.createParallelGroup(Alignment.LEADING)
				.addGroup(glcontainer.createSequentialGroup().addContainerGap()
				.addComponent(gameBoard, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(sidePanel, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)));
		glcontainer.setVerticalGroup(glcontainer.createParallelGroup(Alignment.LEADING)
				.addGroup(glcontainer.createSequentialGroup()
				.addGroup(glcontainer.createParallelGroup(Alignment.LEADING)
				.addComponent(sidePanel, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE) 
				.addGroup(glcontainer.createSequentialGroup().addGap(6)
				.addComponent(gameBoard,GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))) 
				.addContainerGap()));
		
		sidePanel.setLayout(new BoxLayout(sidePanel,1));
		sidePanel.add(bStart);
		sidePanel.add(bPause);
		sidePanel.add(howToPlay);
		sidePanel.add(score);
		sidePanel.setBackground(Color.LIGHT_GRAY);
		container.setLayout(glcontainer);

	}

	public void init() {
		setTitle("決戰俄羅斯");
		gameBoard.start();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
	}

	JLabel getStatusBar() {
		return statusBar;
	}
	
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame game = new Frame();
					game.setVisible(true);
					game.setLocationRelativeTo(null);
					game.init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	class Background extends JPanel  {
		private Image IMG_GB_TEMP = new ImageIcon("bg.jpg").getImage();
		public void paint(Graphics g) {
			g.drawImage(IMG_GB_TEMP, 0, 0, 240, 500,null); //背景图片
			
			
		}
			
		
		}
		
		
	
	
}