package A10515003;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import java.awt.TextField;
import java.awt.Font;

public class TetrisFrame extends JFrame {

	private JPanel contentPane;
	private GamePenal gameBoard;
	private JLabel scoreShow = new JLabel("0  "); //init score
	private JLabel Title = new JLabel(" Score : ");
	private JPanel scorePanel;
	private Image backgroundImage;
	private JLabel lblNewLabel_1;
	
	
	public TetrisFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 312, 697);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 255));
		contentPane.setBorder(new CompoundBorder(new LineBorder(new Color(255, 200, 0), 1, true), new BevelBorder(BevelBorder.LOWERED, new Color(255, 204, 102), new Color(255, 204, 51), new Color(255, 204, 0), new Color(255, 255, 153))));
		setContentPane(contentPane);
		new TimerText();
		scorePanel = new JPanel();
		scorePanel.setBackground(new Color(255, 204, 255));
		scorePanel.setBorder(null);
				gameBoard = new GamePenal(this);
		
		JLabel lblNewLabel = new JLabel("↑:change ↓:down ←:left →:right\r\n");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		
		lblNewLabel_1 = new JLabel("p:pause");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 15));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addComponent(gameBoard, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(77)
							.addComponent(scorePanel, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(119)
							.addComponent(lblNewLabel_1)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scorePanel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(gameBoard, GroupLayout.PREFERRED_SIZE, 569, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(lblNewLabel_1)
					.addContainerGap())
		);
		scorePanel.setLayout(null);
		scoreShow.setBackground(new Color(255, 204, 255));
		scoreShow.setFont(new Font("Clarendon", Font.PLAIN, 15));
		scoreShow.setBounds(100, -3, 75, 24);
		scorePanel.add(scoreShow);
		Title.setFont(new Font("Clarendon", Font.PLAIN, 15));
		Title.setBounds(14, 0, 72, 18);
		scorePanel.add(Title);
		contentPane.setLayout(gl_contentPane);
	}

	public JLabel getScoreBar() {
		return scoreShow;
	}

	public void init() {
		gameBoard.start();
		setTitle("Tetris-A10515003");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TetrisFrame game = new TetrisFrame();
					game.setVisible(true);
					game.setLocationRelativeTo(null);
					game.init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
