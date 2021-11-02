package domain;

import java.awt.*;
import java.awt.Graphics;

public class Paddle implements Drawable {
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
	
	public void draw(Graphics g) {
		g.fillRect(x, y, length, thickness);
	}
	
	
}