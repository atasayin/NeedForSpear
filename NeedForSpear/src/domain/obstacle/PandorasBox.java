package domain.obstacle;

import domain.strategy.ExplosiveDestroyBehaviour;

import java.util.ArrayList;

public class PandorasBox extends Obstacle {

	public PandorasBox(int xPos, int yPos, float L, int health) {
		super(xPos, yPos, L, health);
		this.destroyBehaviour = new ExplosiveDestroyBehaviour();
		this.is_rotating = true;
	}

	@Override
	public ArrayList<String> makeList() {
		return null;
	}
}
