package domain;

public class PandorasBox extends Obstacle {

	public PandorasBox(int xPos, int yPos, float L, int health) {
		super(xPos, yPos, L, health);
		this.destroyBehaviour = new ExplosiveDestroyBehaviour();
	}
}
