package domain.obstacle;

import domain.strategy.GiftObstacleDestroyBehaviour;

import java.util.ArrayList;

public class GiftOfUranus extends Obstacle {

	public GiftOfUranus(int xPos, int yPos, float L, int health) {
		super(xPos, yPos, L, health);
		this.destroyBehaviour = new GiftObstacleDestroyBehaviour();
		this.type = "GiftOfUranus";
	}


	public ArrayList<String> makeList() {
		return null;
	}
}
