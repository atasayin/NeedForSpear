package UI;
import NeedForSpear.src.domain.DomainObject;

import java.awt.*;

public interface Drawable {

	public void draw(Graphics2D g2d, DomainObject domainObject);
	static void drawRec(Graphics g, int x , int y, int l, int t){
		g.fillRect(x,y,l,t);
	}

}
