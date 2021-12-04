package domain.obstacle;

import domain.strategy.RegularDestroyBehaviour;

import java.util.ArrayList;

public class SteinsGate extends Obstacle {
	public SteinsGate(int xPos, int yPos, float L, int health) {
		super(xPos, yPos, L, health);
		this.destroyBehaviour = new RegularDestroyBehaviour();
		this.type = "SteinsGate";
	}

	public ArrayList<String> makeList() {
		return null;
	}

	@Override
	protected void updatePosition(int x, int y) {

	}

	@Override
	public String toString() {
		return super.toString() + ", typeVariable=" + getType();
	}
}
