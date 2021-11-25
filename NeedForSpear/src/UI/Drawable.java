package UI;
import domain.DomainObject;

import java.awt.*;

public interface Drawable {

	void draw(Graphics2D g2d, DomainObject domainObject);
//	static void drawRec(Graphics g, int x , int y, int l, int t){
//		g.fillRect(x,y,l,t);
//	}

}
