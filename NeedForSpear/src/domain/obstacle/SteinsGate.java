package domain.obstacle;

import domain.strategy.RegularDestroyBehaviour;

import java.util.ArrayList;

public class SteinsGate extends Obstacle {
	public SteinsGate(int xPos, int yPos, float L, int health) {
		super(xPos, yPos, L, health);
		this.destroyBehaviour = new RegularDestroyBehaviour();
	}


	@Override
	public ArrayList<String> makeList() {
		return null;
	}
}
