package A10615003;
import javax.swing.*;

import org.omg.CORBA.FREE_MEM;

import controllers.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePenal extends JPanel implements ActionListener {
    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 22;
    private JLabel statusBar;
    private JLabel scoreBar;
    private JLabel hScoreBar;
    private ImageIcon icon;
    private Image img;
    
    private Controller controller;
    
    public GamePenal(Frame frame) {
        setFocusable(true);
        icon = new ImageIcon(getClass().getResource("coordsbg.png"));
        img = icon.getImage();
//      setBackground(Color.WHITE);
        controller = new Controller(BOARD_WIDTH, BOARD_HEIGHT, this);
        statusBar = frame.getStatusBar();
        scoreBar = frame.getScoreBar();
        hScoreBar = frame.getHighestScore();
        addKeyListener(new TAdapter());
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        //        g.drawImage(img, 0, 0, 240, 500, this);
    }
    
    public void start() {
        controller.start();
    }
    
    public void pause() {
        controller.pause();
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
            new Color(189,183,107), //Z形方塊 DarkKhaki
            new Color(135,206,235), //S形方塊 SkyBlue
            new Color(60,179,113),//I形方塊 SpringGreen
            new Color(245,222,179), //T形方塊 Wheat
            new Color(240,230,140),//方形方塊 Khaki
            new Color(240,248,255), //L形方塊 AliceBlue
            new Color(107,142,35)//反L形方塊 Beige
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
    
    public void setHighestScoreText(String text) {
        hScoreBar.setText(text);
    }
    
    private class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            
            if (!controller.isStarted() || controller.isCurrentPieceEmpty()) {
                if (keycode == 'r' || keycode == 'R') { //按p暫停 （大小寫都可以）
                    controller.start();
                    statusBar.setText("Gaming");
                    return;
                }
            }
            if (keycode == 'p' || keycode == 'P') { //按p暫停 （大小寫都可以）
                controller.pause();
                return;
            }
            
            if (controller.isPaused())
                return;
            
            switch (keycode) {          //按鍵觸發事件
                case KeyEvent.VK_LEFT:
                    controller.goLeft(); //向左按鍵觸發事件
                    break;
                case KeyEvent.VK_RIGHT:
                    controller.goRight(); //向右按鍵觸發事件
                    break;
                case KeyEvent.VK_DOWN:
                    controller.goDown(); //向下按鍵觸發事件
                    break;
                case KeyEvent.VK_UP:
                    controller.rotate(); //向上按鍵觸發事件
                    break;
                case KeyEvent.VK_SPACE:
                    controller.speedUp(); //空格按鍵觸發事件
                    break;
            }
        }
        
    }
}

