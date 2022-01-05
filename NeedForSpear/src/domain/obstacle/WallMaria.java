package domain.obstacle;

import domain.path.IPathBehaviour;
import domain.path.StraightVerticalBFPath;
import domain.strategy.DestroyBehaviour;
import domain.strategy.RegularDestroyBehaviour;
import util.PosVector;

import java.util.ArrayList;

public class WallMaria extends Obstacle {

	private boolean isMove = false;

	public WallMaria(int xPos, int yPos) {
		super(xPos, yPos);
		this.destroyBehaviour = (DestroyBehaviour) new RegularDestroyBehaviour();
		this.type = "WallMaria";
	}


	public ArrayList<String> makeList() {
		return null;
	}

	@Override
	protected void updatePosition(int x, int y) {

	}

	public void setPathBehaviour(IPathBehaviour pb){
		this.pathBehaviour = pb;
		pb.initialPath(this);
	}

	@Override
	public String toString() {
		return super.toString() + ", typeVariable=" + getType();
	}

	public void move(){
		PosVector pos = pathBehaviour.nextPosition();
		setPosVector(pos);
	}
}
