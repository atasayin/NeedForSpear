package domain;

public class WallMaria extends Obstacle {
	
	public WallMaria(int xPos, int yPos, float L, int health) {
		super(xPos, yPos, L, health);
		this.destroyBehaviour = new RegularDestroyBehaviour();
	}
	
}
