package NeedForSpear.src.domain;
import java.awt.Graphics;

public class PaddleController {
	Paddle paddle; 
	int fw;
	int fh;
	
	public PaddleController(int w, int h){
		paddle = new Paddle(w, h);
		this.fw = w;
		this.fh = h;
	}

	public Paddle getPaddle(){
		return this.paddle;
	}

	public void move(int d, Graphics g) {
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
