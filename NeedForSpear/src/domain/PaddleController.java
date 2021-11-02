package domain;
import java.awt.Graphics;

public class PaddleController {
	Paddle paddle; 
	int fw;
	int fh;
	
	public PaddleController(int w, int h, Graphics g) {
		paddle = new Paddle(w, h);
		this.fw = w;
		this.fh = h;
		paddle.draw(g);
	}
	
	public void move(int d) {
		if(d==1) {
			if(paddle.x <= fw - paddle.length) {
				paddle.x += paddle.length/2;
			}
		} else if (d==2) {
			if(paddle.x >= 0) {
				paddle.x -= paddle.length/2;
			}
		} else if (d==3) {
			if(paddle.x <= fw - paddle.length) {
				paddle.x += paddle.length;
			}
		}  else if (d==4) {
			if(paddle.x >= 0) {
				paddle.x -= paddle.length;
			}
		}
		
	}
	

}
