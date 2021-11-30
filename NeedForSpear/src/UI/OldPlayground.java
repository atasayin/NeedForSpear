package UI;

import domain.*;
import domain.controller.PaddleController;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class OldPlayground extends JFrame {
	
	public static int h;
	public static int w;
	public static int TIMER_SPEED = 100;
	
	static JFrame frame;
	static JButton startButton;

	private static PaddleController pc;


	public OldPlayground(PaddleController paddleCont, int h, int w) {
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
			int x = pc.getPaddle().getPosVector().x;
			int y = pc.getPaddle().getPosVector().y;
			Graphics2D g2d = (Graphics2D) g;

			g2d.drawRect(x,y,leng,thick);
			g.drawRect(250,250,200,200);
			g2d.fillRect(x,y,leng,thick);
			System.out.println(x);
			System.out.println(y);
			System.out.println(leng);
			System.out.println(thick);

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

//	@Override
//	public void draw(Graphics2D g2d, DomainObject domainObject) {
//
//	}

	private static class KeyHandler extends KeyAdapter {
		@Override 
		public void keyPressed(KeyEvent event) {
			Graphics g = frame.getGraphics();
			int key = event.getKeyCode();
			System.out.println(key);
			if (key == KeyEvent.VK_RIGHT) {
				System.out.println("yayy");
				pc.move(1);
			} else if (key == KeyEvent.VK_LEFT) {
				pc.move(2);
			} else if (key == KeyEvent.VK_DOWN && key == KeyEvent.VK_RIGHT) {
				pc.move(3);
			} else if (key == KeyEvent.VK_DOWN && key == KeyEvent.VK_LEFT) {
				pc.move(4);
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
