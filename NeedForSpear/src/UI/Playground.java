package NeedForSpear.src.UI;

import NeedForSpear.src.domain.*;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Playground extends JFrame implements Drawable {
	
	public static int h;
	public static int w;
	public static int TIMER_SPEED = 100;
	
	static JFrame frame;
	static JButton startButton;

	private static PaddleController pc;


	public Playground(PaddleController paddleCont, int h, int w) {
		this.h =h;
		this.w = w;
		this.pc = paddleCont;

		frame = init();
		frame.setLocationRelativeTo(null);
		//GridLayout layout = new GridLayout(5, 3);
		JPanel panel = new JPanel();
		startButton = new JButton("Start the Game");
		panel.add(startButton);

		frame.add(panel);
		frame.setVisible(true);
		Graphics g = frame.getGraphics();

		startButton.setFocusable(false);
		startButton.addActionListener(e -> {
			startButton.setVisible(false);

			int leng = pc.getPaddle().getLength();
			int thick = pc.getPaddle().getThickness();
			int x = pc.getPaddle().getX();
			int y = pc.getPaddle().getY();

			Drawable.drawRec(g,x,y,leng,thick);

			KeyHandler handler = new KeyHandler();
			frame.addKeyListener(handler);
			panel.addKeyListener(handler);

		});
		
	}

	public static JFrame init() {
		JFrame frame = new JFrame("NeedForSpear");
		//frame.setLayout(new GridLayout(5, 3));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(w, h);
		//frame.setVisible(true);
		return frame;
	}

	private static class KeyHandler extends KeyAdapter {
		@Override 
		public void keyPressed(KeyEvent event) {
			Graphics g = frame.getGraphics();
			int key = event.getKeyCode();
			System.out.println(key);
			if (key == KeyEvent.VK_RIGHT) {
				System.out.println("yayy");
				pc.move(1, g);
			} else if (key == KeyEvent.VK_LEFT) {
				pc.move(2, g);
			} else if (key == KeyEvent.VK_DOWN && key == KeyEvent.VK_RIGHT) {
				pc.move(3, g);
			} else if (key == KeyEvent.VK_DOWN && key == KeyEvent.VK_LEFT) {
				pc.move(4, g);
			}
		}
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);

		graphics.setColor(Color.YELLOW);
		graphics.fillRect(100, 100, 120, 10);
		graphics.drawRect(0, 0, 1009, 620);

	}


}
