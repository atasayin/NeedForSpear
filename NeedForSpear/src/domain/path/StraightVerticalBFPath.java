package domain.path;

import domain.CollisionChecker;
import domain.Layout;
import domain.obstacle.Obstacle;
import util.PosVector;

import java.util.HashMap;

public class StraightVerticalBFPath implements IPathBehaviour {
    private int startX, Y, endLeft, endRight;
    private final int END_MOST_DIS_X = 100;
    private int velocity;
    private int currentX;
    private int MIN_PATH_LONG = 50;

    public StraightVerticalBFPath(int startX,
                                  int Y,
                                  int velocity) {
        this.startX = startX;
        this.currentX = startX;
        this.endLeft = startX - END_MOST_DIS_X;
        this.endRight = startX + END_MOST_DIS_X;
        this.Y = Y;
        this.velocity = velocity;
    }

    @Override
    public void initialPath(Obstacle thisObs) {
        // Get game variables for checking collision
        HashMap<Obstacle, PosVector> obstacle_positions = Layout.getObstaclePositions();
        CollisionChecker CC = CollisionChecker.getInstance();
        int tempX = currentX;
        System.out.println("Obstacle " + thisObs.toString());

        // Find the right limit
        while(true){
            for (Obstacle obs: obstacle_positions.keySet()){
                if (!obs.equals(thisObs) && CC.checkCollision(thisObs, obs)){
                    endRight = tempX- thisObs.getWidth() / 2;
                    break;
                }
            }
            tempX += velocity;
            thisObs.setPosVector(new PosVector(tempX,Y));

            if (tempX >= endRight){
                break;
            }

        }

        // Find the left limit
        velocity *= - 1;
        tempX = startX;
        thisObs.setPosVector(new PosVector(tempX,Y));

        while(true){
            for (Obstacle obs: obstacle_positions.keySet()){
                if (!obs.equals(thisObs) && CC.checkCollision(thisObs, obs)){
                    endLeft = tempX + thisObs.getWidth() / 5;
                    break;
                }
            }
            tempX += velocity;
            thisObs.setPosVector(new PosVector(tempX,Y));

            if (tempX <= endLeft){
                break;
            }

        }
        System.out.println("ENDLEFT " + endLeft);
        System.out.println("ENDRIGHT " + endRight);

        // Reset obstacle
        thisObs.setPosVector(new PosVector(startX,Y));

        // Delete if the path is too small
        if (endRight - endLeft <= MIN_PATH_LONG){
            Layout.getObstacleMoving().remove(thisObs);
        }

    }

    @Override
    public PosVector nextPosition() {
        currentX += velocity;

        if (currentX >= endRight || currentX <= endLeft){
            velocity *= -1;
        }

        return new PosVector(currentX,Y);
    }


}
