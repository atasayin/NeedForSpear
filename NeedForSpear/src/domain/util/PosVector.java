package domain.util;
import java.lang.Math;

public class PosVector {
	public int x;
	public int y;

	public PosVector(int x, int y) {
		this.x = x;
		this.y = y;
	}


	// Vector Addition
	public void addVector(PosVector vec) {
		this.setX( this.getX() + vec.getX() );
		this.setY( this.getY() + vec.getY() );
	}

	public void halfVelociy() {
		this.setX( this.getX() / 2 );
		this.setY( this.getY() / 2 );
	}

	// To rotate the obstacles, we will need to get the angle between current pos and the center of the circle
	public double getTheta(PosVector center){
		double dy = center.getY() - this.getY();
		double dx = center.getX() - this.getX();
		double theta = Math.atan(dy/dx);
		return theta;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "PosVector{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}
