package domain;

import domain.util.PosVector;

public class Paddle extends DomainObject{
	public static final int FRAME_WIDTH = 1368;
	public static final int FRAME_HEIGHT = 766;

	protected int length;
	private int thickness;
	private double angle;
	
	public Paddle(int fWidth, int fHeight) {
		this.length = fWidth/10;
		this.thickness = 20;
		this.posVector = new PosVector((fWidth - length)/2, fHeight - (this.thickness * 4));
		this.angle = 0;
		this.setSpeed(0,0);
	}

	public int getLength() { return this.length; }

	public int getThickness() { return this.thickness; }

	public double getAngle() { return this.angle; }

	public void setAngle(double angle) { this.angle = angle; }

	public PosVector getPosVector() { return this.posVector; }

	public void setPosVector(PosVector pos) {
		this.posVector = pos;
	}

	public void move(int direction) {
		if(direction==1) { // right
			//System.out.println(this.getDx());
			if(this.getPosVector().getX() <= FRAME_WIDTH - this.getLength()) {
				//this.updatePosition();
			}
		} else if (direction==2) { // left
			//System.out.println(this.getDx());
			if(this.getPosVector().getX() >= 0) {
				//this.updatePosition();
			}
		} else if (direction==3) { // speed right
			if(this.getPosVector().getX() <= FRAME_WIDTH - this.getLength()) {
				//this.updatePosition();
			}
		}  else if (direction==4) { // speed left
			if(this.getPosVector().getX() >= 0){
				//this.updatePosition();
			}

		}

	}


	public void updatePosition(int  x, int  y) {
		this.posVector.x += x;
//		System.out.println("Pcden gelen");
//		System.out.println(Game.getInstance().PC.getPaddle().getPosVector().x);
//		System.out.println("thisden gelen");
//		System.out.println(this.posVector.x);
		this.posVector.y += y;
	}

}