package controllers;

import javax.swing.*;

import models.PieceType;
//import A10515003.GamePenal;
//import studentno.Gamepenal; change view
import A10615003.GamePenal;
import java.awt.*;
import java.util.Set;

public class Controller {
    private GamePenal gameBoard;
    private int score = 0;
    private int highestScore = 0;
    private int nowX = 0;
    private int nowY = 0;
    private Timer timer;
    private int penalWidth;
    private int penalHeight;
    private boolean isReachBottom = false;
    private boolean isRunning = false;
    private boolean isStoped = false;
    private PieceType nowPiece;
    private PieceType.AllType[] board;
    private PieceType nextOne;
    
    //    private GamePanel gamePanel;
    //    private static Gameboard gameboard;
    //    private int Blocks;
    
    public Controller(int penalWidth, int penalHeight, GamePenal gameBoard) {
        this.penalWidth = penalWidth;
        this.penalHeight = penalHeight;
        this.gameBoard = gameBoard;
        nowPiece = new PieceType();
        timer = new Timer(500, gameBoard); // 界面刷新时间（下落速度）
        timer.start();
        board = new PieceType.AllType[penalWidth * penalHeight];
        clearAll();
    }
    private boolean tryMove(PieceType newPiece, int newX, int newY) {
        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= penalWidth || y < 0 || y >= penalHeight)
                return false;
            if (PieceAt(x, y) != PieceType.AllType.Empty)
                return false;
        }
        //this.nowpiece = newPiece;
        //this.nowX = newX;
        //this.nowY = newY;
        nowPiece = newPiece;
        nowX = newX;
        nowY = newY;
        gameBoard.repaint();
        return true;
    }
    public void goLeft() { // 向左移動方塊的方法
        tryMove(nowPiece, nowX - 1, nowY);
    }
    public void goRight() { // 向右移動方塊的方法
        tryMove(nowPiece, nowX + 1, nowY);
    }
    public void rotate() { // 旋轉方塊的方法
        tryMove(nowPiece.rotate(), nowX, nowY);
    }
    public void gameAction() {
        if (isReachBottom) {
            isReachBottom = false;
            newPiece();
        } else {
            speedUp();
        }
    }
    public boolean isStarted() {
        return isRunning;
    }
    public boolean isPaused() {
        return isStoped;
    }
    public boolean isCurrentPieceEmpty() {
        return nowPiece.getPieceShape() == PieceType.AllType.Empty;
    }
    public void start() {
        if (isStoped)
            return;
        isRunning = true;
        isReachBottom = false;
        score = 0;
        gameBoard.setScoreText("0");
        clearAll();
        newPiece();
        timer.start();
    }
    
    //    public void newGame()
    //{
    //    gameBoard = new GameBoadrd(this);
    //    //System.out.println("newgame"+ score);
    //    gameBoard.start;
    //}
    
    public void pause() {
        if (!isRunning) {
            return;
        }
        isStoped = !isStoped;
        if (isStoped) {
            timer.stop();
            gameBoard.setStatusText("Paused");
        } else {
            timer.start();
            gameBoard.setStatusText(String.valueOf("Gaming"));
        }
        gameBoard.repaint();
    }
    public void speedUp() {
        if (!tryMove(nowPiece, nowX, nowY - 1)) {
            pieceDropped();
        }
    }
    private void clearAll() {
        for (int i = 0; i < penalHeight * penalWidth; ++i)
            board[i] = PieceType.AllType.Empty;
    }
    public void goDown() {
        int newY = nowY;
        while (newY > 0) {
            if (!tryMove(nowPiece, nowX, newY - 1))
                break;
            --newY;
        }
        pieceDropped();
    }
    private PieceType.AllType PieceAt(int x, int y) {
        return board[(y * penalWidth) + x];
    }
    public void paint(Graphics g, double width, double height) {
        int squareWidth = (int) width / penalWidth;
        int squareHeight = (int) height / penalHeight;
        int boardTop = (int) height - penalHeight * squareHeight;
        for (int i = 0; i < penalHeight; ++i) {
            for (int j = 0; j < penalWidth; ++j) {
                models.PieceType.AllType shape = PieceAt(j, penalHeight - i - 1);
                //models.PieceType.AllType shape = PieceAt(j, penalHeight - i + 1);
                if (shape != models.PieceType.AllType.Empty)
                    gameBoard.drawPiece(g, j * squareWidth, boardTop + i * squareHeight, shape);
            }
        }
        if (nowPiece.getPieceShape() != models.PieceType.AllType.Empty) {
            for (int i = 0; i < 4; ++i) {
                int x = nowX + nowPiece.x(i);
                int y = nowY - nowPiece.y(i);
                gameBoard.drawPiece(g, x * squareWidth, boardTop + (penalHeight - y - 1) * squareHeight,
                                    nowPiece.getPieceShape());
            }
        }
    }
    
    //    public void setSpeed()
    //{
    //    if(Globle.downTime<=250) return;
    //    if(Lines>=6)
    //    {
    //        Globle.downTime -= 50;
    //        Lines -= 6;
    //    }
    //
    //}
    
    public PieceType getNextPieceType() {
        return nextOne;
    }
    
    public void showHighestScore() {
        if (highestScore <= score) {
            highestScore = score;
            gameBoard.setHighestScoreText(String.valueOf("歷史最高分："+highestScore));
        }else {
            return;
        }
    }
    
    private void newPiece() { // 新產生的方塊
        nowPiece.setRandomShape();
        nowX = penalWidth / 2 + 1;
        nowY = penalHeight - 1 + nowPiece.minY();
        if (!tryMove(nowPiece, nowX, nowY)) {
            nowPiece.setPieceType(PieceType.AllType.Empty);
            timer.stop();
            isRunning = false;
            String ms1 = "Game Over";
            String ms2 = "(push 'R' to retry)";
            String msg = "<html><body>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ms1+"<br>"+ms2+"</body></html>";
            gameBoard.setStatusText(msg);
        }
    }
    private void pieceDropped() {
        for (int i = 0; i < 4; ++i) {
            int x = nowX + nowPiece.x(i);
            int y = nowY - nowPiece.y(i);
            board[(y * penalWidth) + x] = nowPiece.getPieceShape();
        }
        removeFullLines();
        if (!isReachBottom)
            newPiece();
    }
    
    //    public static void gameover() {
    //        int select = JOptionPane.showConfirmDialog(null, "game over", "have fun", JOptionPane.YES_NO_OPTION);
    //        if(select==1) System.exit(0);
    //        else restart();
    //    }
    
    
    //    public static void restart(){
    //}
    
    
    private void removeFullLines() { // 消行方法
        int howManyLineFull = 0;
        for (int i = penalHeight - 1; i >= 0; --i) {
            boolean lineIsFull = true;
            for (int j = 0; j < penalWidth; ++j) {
                if (PieceAt(j, i) == PieceType.AllType.Empty) {
                    lineIsFull = false;
                    break;
                }
            }
            if (lineIsFull) {
                ++howManyLineFull;
                for (int k = i; k < penalHeight - 1; ++k) {
                    for (int j = 0; j < penalWidth; ++j)
                        board[(k * penalWidth) + j] = PieceAt(j, k + 1);
                }
            }
            
            //            for (int m = penalHeight - 1; m >= 0; m--) {
            //                for (int j = penalHeight; j >= 0; j--) {
            //                    for (int i = 0; i < 13; i++) {
            //                    }
            //                }
            //            }
            
        }
        if (howManyLineFull > 0) {
            score += howManyLineFull;
            gameBoard.setScoreText(String.valueOf(score + "               "));
            showHighestScore();
            isReachBottom = true;
            nowPiece.setPieceType(PieceType.AllType.Empty);
            gameBoard.repaint();
        }
    }
}
