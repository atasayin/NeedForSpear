package domain;

public abstract class Obstacle {
	
	public PosVector pos;
	public float speed;
	public int health;
	public DestroyBehaviour destroyBehaviour;
	
	
	
	public Obstacle(int xPos, int yPos, float L, int health) {
		this.pos = new PosVector(xPos, yPos);
		this.speed = L/4;
		this.health = health;
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
