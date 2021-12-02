package domain;

import domain.obstacle.Obstacle;
import domain.util.PosVector;

import java.util.HashMap;

public class Layout {
    // Represents the locations and types of the objects in the game.

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
