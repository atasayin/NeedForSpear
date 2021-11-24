package NeedForSpear.src.UI;
import java.awt.Graphics;

public interface Drawable {
	
	static void drawRec(Graphics g, int x , int y, int l, int t){
		g.fillRect(x,y,l,t);
	}

}
