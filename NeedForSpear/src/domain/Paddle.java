package domain;


import java.awt.Graphics;

public class Paddle  {
	int length;
	int thickness;
	int x;
	int y;
	
	public Paddle(int fWidth, int fHeight) {
		this.length = fWidth/10;
		this.thickness = 20; 
		this.x = (fWidth - length)/2;
		this.y = fHeight - (this.thickness * 4);
	}

	public int getLength() { return this.length; }

	public int getThickness() { return this.thickness; }

	public int getX() { return this.x; }

	public int getY() { return this.y; }

}