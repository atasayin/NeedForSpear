package domain.obstacle;

import domain.DomainObject;
import domain.strategy.DestroyBehaviour;
import domain.util.PosVector;

public abstract class Obstacle extends DomainObject {
	
	public PosVector pos;
	public boolean is_rotating;
	public int health;
	public DestroyBehaviour destroyBehaviour;
	protected static String type;

	
	
	public Obstacle(int xPos, int yPos, float L, int health) {
		this.pos = new PosVector(xPos, yPos);
		this.health = health;
		this.is_rotating = false;
	}
	public static String getType() {
		return type;
	}

	public PosVector getPosVector() {
		return this.pos;
	}
	
	public boolean getHit() {
		this.health -= 1;
		if (this.health == 0) {
			this.destroy();
			return true;
		} else {
			return false;
		}
	}
	
	public void destroy() {
		this.destroyBehaviour.destroy();
	}

	@Override
	public String toString() {
		return "Obstacle{" +
				"pos=" + pos.toString();
	}
}
