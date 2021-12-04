package domain.obstacle;

import domain.strategy.DestroyBehaviour;
import domain.strategy.RegularDestroyBehaviour;

import java.util.ArrayList;

public class WallMaria extends Obstacle {
	
	public WallMaria(int xPos, int yPos, float L, int health) {
		super(xPos, yPos, L, health);
		this.destroyBehaviour = (DestroyBehaviour) new RegularDestroyBehaviour();
		this.type = "WallMaria";
	}


	public ArrayList<String> makeList() {
		return null;
	}

	@Override
	protected void updatePosition(int x, int y) {

	}

	@Override
	public String toString() {
		return super.toString() + ", typeVariable=" + getType() + ", typeString = WallMaria}";
	}
}
