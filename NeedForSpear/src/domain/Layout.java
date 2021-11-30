package domain;

import domain.obstacle.Obstacle;
import domain.obstacle.WallMaria;
import domain.util.PosVector;
import javafx.geometry.Pos;

import java.util.HashMap;

public class Layout {
    // Represents the locations and types of the objects in the game.

    /////////////////////////////////////////////////////////////////////////////////////

    // Total number of Simple Obstacles(Wall Maria)
    public int simpleObstacleCount;

    // Total number of Firm Obstacles(Steins Gate)
    public int firmObstacleCount;

    // Total number of Explosive Obstacles(Pandora’s Box)
    public int explosiveObstacleCount;

    // Total number of Gift Obstacles(Gift of Uranus)
    public int giftObstacleCount;

    // Holds positions of all obstacles
    public static HashMap<Obstacle, PosVector> obstacle_positions;

    // Holds the center of circles of the paths that some obstacles move in
    public static HashMap<Obstacle, PosVector> obstacle_centers;

    // 2D bool array
    public boolean[][] isAvailable;

    // Layout width
    public int layoutWidth;

    // Layout heigth
    public int layoutHeight

    /////////////////////////////////////////////////////////////////////////////////////

    public Layout(int layoutWidth, int layoutHeight){
        isAvailable = new boolean[layoutWidth][layoutHeight];
        setLayout();
    }

    // This method is called once the map is build to save the centers of the obstacles.
    public void buildCenterMap() {
        for (Obstacle obs : obstacle_positions.keySet()){
            if (obs.is_rotating) {
                obstacle_positions.put(obs, obstacle_positions.get(obs));
            }
        }
    }

    // Returns positions of all current obstacles.
    HashMap<Obstacle, PosVector> getObstacle_positions() { return obstacle_positions; }

    // Returns center of circles of the paths that some obstacles move in.
    HashMap<Obstacle, PosVector> getObstacle_centers() { return obstacle_centers; }

    // Sets Simple Obstacles(Wall Maria)
    public void setSimpleObstacleCount(int simpleObstacleCount) {
        this.simpleObstacleCount = simpleObstacleCount;
    }

    // Sets Firm Obstacles(Steins Gate)
    public void setFirmObstacleCount(int firmObstacleCount) {
        this.firmObstacleCount = firmObstacleCount;
    }

    // Sets Explosive Obstacles(Pandora’s Box)
    public void setExplosiveObstacleCount(int explosiveObstacleCount) {
        this.explosiveObstacleCount = explosiveObstacleCount;
    }

    // Sets Gift Obstacles(Gift of Uranus)
    public void setGiftObstacleCount(int giftObstacleCount) {
        this.giftObstacleCount = giftObstacleCount;
    }

    //
    public void setLayout(){

        // bir o bir o bir o bir o
        for (int i = 0; i < simpleObstacleCount; i ++) {
            PosVector pos;

            WallMaria wm = new WallMaria();
        }

        for (int i = 0; i < firmObstacleCount; i ++) {

        }

        for (int i = 0; i < explosiveObstacleCount; i ++) {

        }

        for (int i = 0; i < giftObstacleCount; i ++) {

        }


    }

    //
    public


    // Changes type of the obstacle.
 /*
    void changeTypeObstacle(Obstacle obstacle, newType){

    }

    // Changes location of an existing obstacle.
    void changeLocationObstacle(obstacle, location){

    }

    // Adds new obstacle to the Layout with given type and location.
    void addNewObstacle(location, type){


    }

  */
    // Removes a obstacle from Layout.
    void removeObstacle(Obstacle obstacle){


    }
}
