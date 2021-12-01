package domain;

import domain.obstacle.*;
import domain.util.PosVector;
import javafx.geometry.Pos;

import java.util.HashMap;
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

    // 2D bool array
    public boolean[][] isAvailable;

    // Layout width
    public int layoutWidth;

    // Layout heigth
    public int layoutHeight;

    /////////////////////////////////////////////////////////////////////////////////////

    public Layout(int layoutWidth, int layoutHeight){
        this.layoutWidth = layoutWidth;
        this.layoutHeight = layoutHeight;
        isAvailable = new boolean[layoutWidth][layoutHeight];
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
        Random rnd = new Random(3);
        HashMap<Obstacle, PosVector> obstacle_positions = new HashMap<>();
        HashMap<Obstacle, PosVector> obstacle_centers = new HashMap<>();

        for (int i = 0; i < pandoraBoxCount; i++) {

            while(true) {
                int x = rnd.nextInt(layoutWidth);
                int y = rnd.nextInt(layoutHeight);

                if (isAvailable[x][y]) {
                    PosVector pos = new PosVector(x, y);
                    int L = 10;
                    PandorasBox obs = new PandorasBox(pos.getX(),pos.getY(),L, 1);
                    obstacle_positions.put(obs,pos);
                    updateAvailable(pos.getX(), pos.getY(), obs);
                    break;

                }


            }


        }

        for (int i = 0; i < steinsGateCount; i++) {

            while(true) {
                int x = rnd.nextInt(layoutWidth);
                int y = rnd.nextInt(layoutHeight);

                if (isAvailable[x][y]) {
                    PosVector pos = new PosVector(x, y);
                    int L = 10;
                    SteinsGate obs = new SteinsGate(pos.getX(),pos.getY(),L, 1);
                    obstacle_positions.put(obs,pos);
                    updateAvailable(pos.getX(), pos.getY(), obs);
                    break;

                }


            }


        }

        for (int i = 0; i < uranusCount; i++) {

            while(true) {
                int x = rnd.nextInt(layoutWidth);
                int y = rnd.nextInt(layoutHeight);

                if (isAvailable[x][y]) {
                    PosVector pos = new PosVector(x, y);
                    int L = 10;
                    GiftOfUranus obs = new GiftOfUranus(pos.getX(),pos.getY(),L, 1);
                    obstacle_positions.put(obs,pos);
                    updateAvailable(pos.getX(), pos.getY(), obs);
                    break;

                }


            }


        }

        for (int i = 0; i < wallMariaCount; i++) {

            while(true) {
                int x = rnd.nextInt(layoutWidth);
                int y = rnd.nextInt(layoutHeight);

                if (isAvailable[x][y]) {
                    PosVector pos = new PosVector(x, y);
                    int L = 10;
                    WallMaria obs = new WallMaria(pos.getX(),pos.getY(),L, 1);
                    obstacle_positions.put(obs,pos);
                    updateAvailable(pos.getX(), pos.getY(), obs);
                    break;

                }


            }


        }

        }


    // Updates 2D bool array acc. Obstacle
    private void updateAvailable(int x, int y, Obstacle obs){

        String type = obs.getType();

        switch (type){

            case "PandorasBox":
                for(int dx = 0; dx < obs.getWidth(); dx++){
                    for(int dy = 0; dy < obs.getHeight(); dy++){
                        isAvailable[x + dx][y + dy] = true;

                    }

                }
                break;
            case "GiftOfUranus":
                for(int dx = 0; dx < obs.getWidth(); dx++){
                    for(int dy = 0; dy < obs.getHeight(); dy++){
                        isAvailable[x + dx][y + dy] = true;

                    }

                }

                break;
            case "SteinsGate":
                for(int dx = 0; dx < obs.getWidth(); dx++){
                    for(int dy = 0; dy < obs.getHeight(); dy++){
                        isAvailable[x + dx][y + dy] = true;

                    }

                }
                break;
            case "WallMaria":
                for(int dx = 0; dx < obs.getWidth(); dx++){
                    for(int dy = 0; dy < obs.getHeight(); dy++){
                        isAvailable[x + dx][y + dy] = true;
                    }

                }
                break;
            default:
                System.out.println("Could not receive valid obstacle type!");
                break;
        }




    }

    private boolean isAvailable(){

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
