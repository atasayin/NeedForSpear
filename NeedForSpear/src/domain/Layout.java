package domain;

import domain.obstacle.*;
import domain.util.PosVector;
//import javafx.geometry.Pos;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Layout {
    // Represents the locations and types of the objects in the game.

    /////////////////////////////////////////////////////////////////////////////////////

    // Total number of Simple Obstacles(Wall Maria)
    public int wallMariaCount;

    // Total number of Firm Obstacles(Steins Gate)
    public int steinsGateCount;

    // Total number of Explosive Obstacles(Pandora’s Box)
    public int pandoraBoxCount;

    // Total number of Gift Obstacles(Gift of Uranus)
    public int uranusCount;

    // Holds positions of all obstacles
    public static HashMap<Obstacle, PosVector> obstacle_positions;

    // Holds the center of circles of the paths that some obstacles move in
    public static HashMap<Obstacle, PosVector> obstacle_centers;

    // Layout width
    public int layoutWidth;

    // Layout heigth
    public int layoutHeight;

    // Random Seed number
    private int SEED_NUMBER = 3;

    // Collision Checker for Obstacles
    private CollisionChecker CS;

    // Offset to place the obstacles
    int obsLen;
    int layoutHeightOffset = 350;


    /////////////////////////////////////////////////////////////////////////////////////

    public Layout(int wallMariaCount,int steinsGateCount,int pandoraBoxCount
            , int uranusCount, int layoutWidth, int layoutHeight){
        this.wallMariaCount = wallMariaCount;
        this.steinsGateCount = steinsGateCount;
        this.pandoraBoxCount = pandoraBoxCount;
        this.uranusCount = uranusCount;

        this.CS = CollisionChecker.getInstance();

        obstacle_positions = new HashMap<Obstacle, PosVector>();
        obstacle_centers = new HashMap<Obstacle, PosVector>();

        this.layoutWidth = layoutWidth;
        this.layoutHeight = layoutHeight;
        obsLen = layoutWidth/50;
        setLayout();
    }

    public Layout(){

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
    public HashMap<Obstacle, PosVector> getObstacle_positions() { return obstacle_positions; }

    // Returns center of circles of the paths that some obstacles move in.
    public HashMap<Obstacle, PosVector> getObstacle_centers() { return obstacle_centers; }


    // Creates obstacles
    public void setLayout(){
        Random rnd = new Random(SEED_NUMBER);

        // Create PandorasBox Obstacles
        for (int i = 0; i < pandoraBoxCount; i++) {
            while(true) {
                PosVector pos = new PosVector(rnd.nextInt(layoutWidth-obsLen), rnd.nextInt(layoutHeight-layoutHeightOffset));
                //int L = 10;
                PandorasBox obs = new PandorasBox(pos.getX(),pos.getY(),obsLen, 1);
                Game.getInstance().getDomainObjectArr().add(obs);

                if (isAvailable(obs)) {
                    obstacle_positions.put(obs,pos);
                    break;

                }


            }


        }

        // Create SteinsGate Obstacles
        for (int i = 0; i < steinsGateCount; i++) {
            while(true) {
                PosVector pos = new PosVector(rnd.nextInt(layoutWidth-obsLen), rnd.nextInt(layoutHeight-layoutHeightOffset));
                //int L = 10;
                SteinsGate obs = new SteinsGate(pos.getX(),pos.getY(),obsLen, 1);
                Game.getInstance().getDomainObjectArr().add(obs);

                if (isAvailable(obs)) {
                    obstacle_positions.put(obs,pos);
                    break;

                }


            }


        }

        // Create GiftOfUranus Obstacles
        for (int i = 0; i < uranusCount; i++) {
            while(true) {
                PosVector pos = new PosVector(rnd.nextInt(layoutWidth-obsLen), rnd.nextInt(layoutHeight-layoutHeightOffset));
                //int L = 10;
                GiftOfUranus obs = new GiftOfUranus(pos.getX(),pos.getY(),obsLen, 1);
                Game.getInstance().getDomainObjectArr().add(obs);

                if (isAvailable(obs)) {
                    obstacle_positions.put(obs,pos);
                    break;

                }


            }


        }

        // Create WallMaria Obstacles
        for (int i = 0; i < wallMariaCount; i++) {
            while(true) {
                PosVector pos = new PosVector(rnd.nextInt(layoutWidth-obsLen), rnd.nextInt(layoutHeight-layoutHeightOffset ));
                //int L = 10;
                System.out.println(obsLen);
                WallMaria obs = new WallMaria(pos.getX(),pos.getY(),obsLen, 1);
                Game.getInstance().getDomainObjectArr().add(obs);

                if (isAvailable(obs)) {
                    obstacle_positions.put(obs,pos);
                    break;

                }


            }


        }

    }


    // Collider check for creating Layout
    private boolean isAvailable(Obstacle newObs){
//        for (Obstacle obs: obstacle_positions.keySet()){
//
//            if ( !CS.checkCollision(newObs, obs)){
//                return false;
//
//            }
//
//        }

        return true;
    }

    /*
    // Changes type of the obstacle.

    void changeTypeObstacle(Obstacle obstacle, newType){

    }

    // Changes location of an existing obstacle.
    void changeLocationObstacle(obstacle, location){

    }

    // Adds new obstacle to the Layout with given type and location.
    void addNewObstacle(location, type){


    }


    // Removes a obstacle from Layout.
    void removeObstacle(Obstacle obstacle){


    }
    */

}
