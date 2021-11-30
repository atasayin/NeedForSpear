package domain;

import domain.util.PosVector;

public class Paddle extends DomainObject{
	public static final int FRAME_WIDTH = 900;
	public static final int FRAME_HEIGHT = 600;

	private int length;
	private int thickness;
	private double angle;
	
	public Paddle(int fWidth, int fHeight) {
		this.length = fWidth/10;
		this.thickness = 20;
		this.posVector = new PosVector((fWidth - length)/2, fHeight - (this.thickness * 4));
		this.angle = 0;
	}

	public int getLength() { return this.length; }

	public int getThickness() { return this.thickness; }

	public double getAngle() { return this.angle; }

	public void setAngle(double angle) { this.angle = angle; }

	public PosVector getPosVector() { return this.posVector; }

	@Override
	public void updatePosition() {
		if (posVector.x <= 0) {
			posVector.x += 10;
			return;
		} else if (posVector.x + this.length >= FRAME_WIDTH) {
			posVector.x -= 10;
			return;
		}
		posVector.x += dx;
	}

}