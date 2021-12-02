package domain;

import domain.obstacle.*;
import domain.util.PosVector;
import javafx.geometry.Pos;

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

    private int SEED_NUMBER = 3;

    /////////////////////////////////////////////////////////////////////////////////////

    public Layout(int wallMariaCount,int steinsGateCount,int pandoraBoxCount
                  , int uranusCount, int layoutWidth, int layoutHeight){
        this.wallMariaCount = wallMariaCount;
        this.steinsGateCount = steinsGateCount;
        this.pandoraBoxCount = pandoraBoxCount;
        this.uranusCount = uranusCount;

        obstacle_positions = new HashMap<Obstacle, PosVector>();
        obstacle_centers = new HashMap<Obstacle, PosVector>();

        this.layoutWidth = layoutWidth;
        this.layoutHeight = layoutHeight;
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
    HashMap<Obstacle, PosVector> getObstacle_positions() { return obstacle_positions; }

    // Returns center of circles of the paths that some obstacles move in.
    HashMap<Obstacle, PosVector> getObstacle_centers() { return obstacle_centers; }


    //
    public void setLayout(){
        Random rnd = new Random(SEED_NUMBER);

        for (int i = 0; i < pandoraBoxCount; i++) {
            while(true) {
                int x = rnd.nextInt(layoutWidth);
                int y = rnd.nextInt(layoutHeight);

                if (isAvailable()) {
                    PosVector pos = new PosVector(x, y);

                    int L = 10;
                    PandorasBox obs = new PandorasBox(pos.getX(),pos.getY(),L, 1);
                    obstacle_positions.put(obs,pos);
                    //updateAvailable(pos.getX(), pos.getY(), obs);
                    break;

                }


            }


        }

        for (int i = 0; i < steinsGateCount; i++) {

            while(true) {
                int x = rnd.nextInt(layoutWidth);
                int y = rnd.nextInt(layoutHeight);

                if (isAvailable()) {
                    PosVector pos = new PosVector(x, y);
                    int L = 10;
                    SteinsGate obs = new SteinsGate(pos.getX(),pos.getY(),L, 1);
                    obstacle_positions.put(obs,pos);
                    //updateAvailable(pos.getX(), pos.getY(), obs);
                    break;

                }


            }


        }

        for (int i = 0; i < uranusCount; i++) {

            while(true) {
                int x = rnd.nextInt(layoutWidth);
                int y = rnd.nextInt(layoutHeight);

                if (isAvailable()) {
                    PosVector pos = new PosVector(x, y);
                    int L = 10;
                    GiftOfUranus obs = new GiftOfUranus(pos.getX(),pos.getY(),L, 1);
                    obstacle_positions.put(obs,pos);
                    //updateAvailable(pos.getX(), pos.getY(), obs);
                    break;

                }


            }


        }

        for (int i = 0; i < wallMariaCount; i++) {

            while(true) {
                int x = rnd.nextInt(layoutWidth);
                int y = rnd.nextInt(layoutHeight);

                if (isAvailable()) {
                    PosVector pos = new PosVector(x, y);
                    int L = 10;
                    WallMaria obs = new WallMaria(pos.getX(),pos.getY(),L, 1);
                    obstacle_positions.put(obs,pos);
                    //updateAvailable(pos.getX(), pos.getY(), obs);
                    break;

                }


            }


        }

        }



    // Collider check for creating Layout
    private boolean isAvailable(){
    /*
        for (Map.Entry<Obstacle,PosVector> entry: obstacle_positions.entrySet()){
            Obstacle obs = entry.getKey();
            PosVector pos = entry.getValue();

            if(!fun()){
                return false;

            }
        }
    */

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
