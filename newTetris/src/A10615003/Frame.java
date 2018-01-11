package A10615003;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;


public class Frame extends JFrame {
    
    private JPanel contentPane;
    private GamePenal gameBoard;
    private JLabel statusBar = new JLabel("Gaming",JLabel.CENTER); //状态现实label初始化文字 并且居中显示
    private JLabel scoreShow = new JLabel("0",JLabel.LEFT); //状态现实label初始化文字 并且居左显示
    private JLabel title = new JLabel("Score : ",JLabel.RIGHT);
    private JLabel hScoreBar = new JLabel("歷史最高分：0",JLabel.CENTER);
    private JPanel infoPanel;
    private JMenuBar menuBar;
    private JMenu mnHelp;
    private JMenuItem mntmHowToPlay;
    private JMenuItem mntmAbout;
    
    public Frame() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);
        
        mntmHowToPlay = new JMenuItem("How to play");
        mntmHowToPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame alert = new JFrame("幫助");
                alert.setSize(300, 250); //彈窗大小 寬x高
                alert.getContentPane().setLayout(null);
                alert.setLocationRelativeTo(null);
                String left = "方向鍵左：左移方塊";
                String right = "方向鍵右：右移方塊";
                String down = "方向鍵下：方塊瞬間落下";
                String up = "方向鍵上：旋轉方塊";
                String space = "空&nbsp;&nbsp;&nbsp;格鍵：加速下降";
                String p = "P&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;鍵：暫停遊戲";
                String msg = "<html><body>"+left+"<br>"+right+"<br>"+down+"<br>"+up+"<br>"+space+"<br>"+p+"<body></html>";
                JLabel how = new JLabel(msg,JLabel.CENTER);
                
                how.setBounds(90, 20, 150, 100);
                
                JButton okButton = new JButton("OK");
                okButton.setBounds(80, 150, 150, 30);
                okButton.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        // TODO Auto-generated method stub
                        alert.dispose();
                    }
                });
                alert.getContentPane().add(how);
                alert.getContentPane().add(okButton);
                alert.setResizable(false);
                alert.setVisible(true);
            }
        });
        mnHelp.add(mntmHowToPlay);
        
        mntmAbout = new JMenuItem("About");
        mntmAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame alert = new JFrame("關於");
                alert.setSize(200, 150); //彈窗大小 寬x高
                alert.getContentPane().setLayout(null);
                alert.setLocationRelativeTo(null);
                String id = "A10615003";
                String name = "張家成";
                String msg = "<html><body>"+id+"<br>"+"&nbsp;&nbsp;&nbsp;&nbsp;"+name+"<body></html>";
                JLabel about = new JLabel(msg);
                
                about.setBounds(65, 10, 200, 50);
                
                JButton okButton = new JButton("OK");
                okButton.setBounds(60, 70, 80, 30);
                okButton.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        // TODO Auto-generated method stub
                        alert.dispose();
                    }
                });
                alert.getContentPane().add(about);
                alert.getContentPane().add(okButton);
                alert.setResizable(false);
                alert.setVisible(true);
            }
        });
        mnHelp.add(mntmAbout);
        contentPane = new JPanel();
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        setContentPane(contentPane);
        
        gameBoard = new GamePenal(this);
        
        infoPanel = new JPanel();
        infoPanel.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                                          gl_contentPane.createParallelGroup(Alignment.LEADING)
                                          .addGroup(gl_contentPane.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(gameBoard, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(ComponentPlacement.RELATED)
                                                    .addComponent(infoPanel, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                                          );
        gl_contentPane.setVerticalGroup(
                                        gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                  .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                            .addComponent(infoPanel, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE) //infopanel 宽度
                                                            .addGroup(gl_contentPane.createSequentialGroup()
                                                                      .addGap(6)
                                                                      .addComponent(gameBoard, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))) //gameBoard宽度
                                                  .addContainerGap())
                                        );
        hScoreBar.setPreferredSize(new Dimension(187, 100));
        statusBar.setPreferredSize(new Dimension(187, 100));
        scoreShow.setPreferredSize(new Dimension(70, 150));
        title.setPreferredSize(new Dimension(100, 150));
        infoPanel.setLayout(new BorderLayout(0, 0));
        infoPanel.add(hScoreBar,BorderLayout.NORTH);
        infoPanel.add(scoreShow,BorderLayout.EAST);
        infoPanel.add(statusBar,BorderLayout.SOUTH);
        infoPanel.add(title,BorderLayout.WEST);
        
        contentPane.setLayout(gl_contentPane);
    }
    
    
    
    public JLabel getStatusBar() {
        return statusBar;
    }
    public JLabel getScoreBar() {
        return scoreShow;
    }
    
    public JLabel getHighestScore() {
        return hScoreBar;
    }
    
    
    
    public void init() {
        setTitle("俄羅斯方塊");
        gameBoard.start();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setResizable(false);
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
}

