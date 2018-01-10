package views;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class Frame extends JFrame {

    JLabel statusbar;


    public Frame() {
        setSize(400, 800);
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

    public static void main(String[] args) {

    } 
}