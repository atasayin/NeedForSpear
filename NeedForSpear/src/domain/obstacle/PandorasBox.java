package domain.obstacle;

import domain.strategy.ExplosiveDestroyBehaviour;

import java.util.ArrayList;

public class PandorasBox extends Obstacle {

	public PandorasBox(int xPos, int yPos, float L, int health) {
		super(xPos, yPos, L, health);
		this.destroyBehaviour = new ExplosiveDestroyBehaviour();
		this.is_rotating = true;
		this.type = "PandorasBox";
	}


	public ArrayList<String> makeList() {

		return null;
	}

	@Override
	protected void updatePosition(int x, int y) {

	}
}