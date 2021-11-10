package UI;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

import domain.*;

public class Playground extends JFrame {
	
	public static final int HEIGHT = 600;
	public static final int WIDTH = 900;
	public static int TIMER_SPEED = 100;
	
	static JFrame frame;
	static JButton startButton;
	
	public static PaddleController pc;
	
	
	public static void main(String[] args) {
		frame = init();
		frame.setLocationRelativeTo(null);
		//GridLayout layout = new GridLayout(5, 3);
		JPanel panel = new JPanel();
		startButton = new JButton("Start the Game");
		panel.add(startButton);
		
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		Graphics g = frame.getGraphics();
		
		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				pc = new PaddleController(WIDTH, HEIGHT, g);
				startButton.setVisible(false);
			}
			
		} );
		
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int key = e.getKeyCode();
				System.out.println(key);
			    if (key == KeyEvent.VK_RIGHT) {
			        pc.move(1);
			    } else if (key == KeyEvent.VK_LEFT) {
			        pc.move(2);
			    } else if (key == KeyEvent.VK_DOWN && key == KeyEvent.VK_RIGHT) {
			    	pc.move(3);
			    } else if (key == KeyEvent.VK_DOWN && key == KeyEvent.VK_LEFT) {
			    	pc.move(4);
			    }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}

	public static JFrame init() {
		JFrame frame = new JFrame("NeedForSpear");
		//frame.setLayout(new GridLayout(5, 3));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		return frame;
	}


	
	
	
}
