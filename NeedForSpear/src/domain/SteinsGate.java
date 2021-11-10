package domain;

public class SteinsGate extends Obstacle {
	public SteinsGate(int xPos, int yPos, float L, int health) {
		super(xPos, yPos, L, health);
		this.destroyBehaviour = new RegularDestroyBehaviour();
	}
	

}
