package domain;

public abstract class Obstacle extends DomainObject {
	
	public domain.PosVector pos;
	public boolean is_rotating;
	public int health;
	public domain.DestroyBehaviour destroyBehaviour;
	protected String type;

	
	
	public Obstacle(int xPos, int yPos, float L, int health) {
		this.pos = new domain.PosVector(xPos, yPos);
		this.health = health;
		this.is_rotating = false;
	}
	public static String getType() {
		return this.type;
	}

	public domain.PosVector getLocation() {
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

}
