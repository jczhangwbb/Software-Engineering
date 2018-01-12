package views;

import controllers.Controller;

import javax.print.attribute.PrintJobAttributeSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePenal extends JPanel implements ActionListener {
    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 20;
    private JLabel statusBar;
    

    private Controller controller;


    GamePenal(Frame parent) {
        setFocusable(true);
        //controller = new Controller(BOARD_WIDTH, BOARD_HEIGHT, this);
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

    private int squareWidth() { return (int) getSize().getWidth() / BOARD_WIDTH; }
    private int squareHeight() { return (int) getSize().getHeight() / BOARD_HEIGHT; }

    public void drawPiece(Graphics g, int x, int y, models.PieceType.AllType shape)
    {
        Color colors[] = 
        	{ 		
        			new Color(0, 0, 0), 
        			new Color(64, 64, 64), //Z形方塊 紅色
        			new Color(64, 64, 64), //S形方塊 綠色
        			new Color(64, 64, 64),//I（Line）形方塊 藏藍
        			new Color(64, 64, 64), //T形方塊 黃色
        			new Color(64, 64, 64),//方形方塊 桃紅
        			new Color(64, 64, 64), //L形方塊 天藍
        			new Color(64, 64, 64)//反L形方塊 深黃
        };


        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + 1);
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
