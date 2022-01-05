package domain.path;

import domain.obstacle.Obstacle;
import util.PosVector;

public class CircularPath implements IPathBehaviour{

    private int startX, endLeft, endRight;
    private int velocity;
    private int currentX,currentY;
    private int MIN_PATH_LONG = 50;

    public CircularPath(int startX,
                                  int startY,
                                  int velocity) {
        this.startX = startX;
        this.currentX = startX;
        this.endLeft = startX;
        this.endRight = startX;
        this.velocity = velocity;
    }

    @Override
    public void initialPath(Obstacle obs) {

    }

    @Override
    public PosVector nextPosition() {
        return null;
    }
}
