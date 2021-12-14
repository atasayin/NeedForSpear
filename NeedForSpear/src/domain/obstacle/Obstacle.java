package domain.obstacle;

import domain.DomainObject;
import domain.Game;
import domain.strategy.DestroyBehaviour;
import util.PosVector;

public abstract class Obstacle extends DomainObject {

	static final int FRAME_WIDTH = 1368;

	public PosVector pos;
	public boolean is_rotating;
	public int health;
	public DestroyBehaviour destroyBehaviour;
	protected String type;
	private int height, width;

	
	public Obstacle(int xPos, int yPos) {
		this.pos = new PosVector(xPos, yPos);
		this.health = 1;
		this.is_rotating = false;
		this.width = FRAME_WIDTH/50;
		this.height = 20;
	}
	public String getType() {
		return type;
	}

	public PosVector getPosVector() {
		return this.pos;
	}
	
	public boolean getHit() {
		this.health -= 1;
		if (this.health == 0) {
			this.destroy();
			double o = Game.getInstance().getOldScore();
			double neww = Game.getInstance().getScore(o);
			Game.getInstance().setScore(neww);
			System.out.println(Game.getInstance().getOldScore());
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

	public PosVector getPos() {
		return pos;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}


	public int getHealth() {
		return health;
	}
}
