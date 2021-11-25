package domain;

import java.util.ArrayList;

public class WallMaria extends Obstacle {
	
	public WallMaria(int xPos, int yPos, float L, int health) {
		super(xPos, yPos, L, health);
		this.destroyBehaviour = (domain.DestroyBehaviour) new domain.RegularDestroyBehaviour();
		this.type = "WallMaria";
	}

	@Override
	public ArrayList<String> makeList() {
		return null;
	}
}
