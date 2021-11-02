package domain;

public class GiftOfUranus extends Obstacle {

	public GiftOfUranus(int xPos, int yPos, float L, int health) {
		super(xPos, yPos, L, health);
		this.destroyBehaviour = new GiftObstacleDestroyBehaviour();
	}
}
