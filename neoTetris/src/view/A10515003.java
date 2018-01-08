/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Dengpengyu
 *
 */
public class A10515003 {
public static void main(String[] args) {  
        
        JFrame frame = new JFrame("Tetris");  
        Container contentPane = frame.getContentPane();  
        contentPane.setBackground(Color.CYAN); 
        JPanel panel = new JPanel(); 
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.blue);
        panel.setBackground(Color.yellow); 
        JButton button = new JButton("1");  
        JButton button2 = new JButton("2");  
        panel.add(button); 
        panel2.add(button2);
        contentPane.add(panel, BorderLayout.WEST); 
        contentPane.add(panel2, BorderLayout.EAST);
        frame.setSize(800, 600);  
        frame.setVisible(true);  
    }  

}
