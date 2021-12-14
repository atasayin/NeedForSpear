package domain.controller;
import domain.Paddle;

public class PaddleController {
	protected Paddle paddle;
	private int fw;
	private int fh;

	
	public PaddleController(int w, int h){
		paddle = new Paddle(w, h);
		this.fw = w;
		this.fh = h;
	}

	public Paddle getPaddle(){
		return this.paddle;
	}

//	public static void move(int d) {
//		if(d==1) {
//			if(paddle.getPosVector().getX() <= fw - paddle.getLength()) {
//				paddle.getPosVector().setX(paddle.getPosVector().getX() + (paddle.getLength() / 2));
//			}
//		} else if (d==2) {
//			if(paddle.getPosVector().getX() >= 0) {
//				paddle.getPosVector().setX(paddle.getPosVector().getX() - (paddle.getLength() / 2));
//			}
//		} else if (d==3) {
//			if(paddle.getPosVector().getX() <= fw - paddle.getLength()) {
//				paddle.getPosVector().setX(paddle.getLength());
//			}
//		}  else if (d==4) {
//			if(paddle.getPosVector().getX() >= 0)
//				paddle.getPosVector().setX(paddle.getPosVector().getX() - paddle.getLength());
//		}
//
//	}
	

}
