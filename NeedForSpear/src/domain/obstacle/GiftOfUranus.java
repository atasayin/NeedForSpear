package domain.obstacle;

import domain.strategy.GiftObstacleDestroyBehaviour;

import java.util.ArrayList;

public class GiftOfUranus extends Obstacle {

	private int width;

	public GiftOfUranus(int xPos, int yPos, int L, int health) {
		super(xPos, yPos, L, health);
		this.destroyBehaviour = new GiftObstacleDestroyBehaviour();
		this.type = "GiftOfUranus";
	}


	public ArrayList<String> makeList() {
		return null;
	}

	@Override
	protected void updatePosition(int x, int y) {

	}

	@Override
	public String toString() {
		return super.toString() + ", typeVariable=" + getType();
	}
}
