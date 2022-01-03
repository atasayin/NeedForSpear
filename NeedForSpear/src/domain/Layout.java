package domain;

import domain.obstacle.ObstacleFactory;
import domain.obstacle.*;
import util.PosVector;
//import javafx.geometry.Pos;

import java.util.HashMap;
import java.util.Random;

public class Layout {
    // Represents the locations and types of the objects in the game.

    /////////////////////////////////////////////////////////////////////////////////////
    private static int yOffset = 70;
    private static int xOffset = 175;
    // Total number of Simple Obstacles(Wall Maria)
    private int wallMariaCount;

    // Total number of Firm Obstacles(Steins Gate)
    private int steinsGateCount;

    // Total number of Explosive Obstacles(Pandora’s Box)
    private int pandoraBoxCount;

    // Total number of Gift Obstacles(Gift of Uranus)
    private int uranusCount;

    // Holds positions of all obstacles
    private static HashMap<Obstacle, PosVector> obstacle_positions;

    // Holds the center of circles of the paths that some obstacles move in
    private static HashMap<Obstacle, PosVector> obstacle_centers;

    // Layout width
    private int layoutWidth;

    // Layout heigth
    private int layoutHeight;

    // Random Seed number
    private int SEED_NUMBER = 14;

    // Collision Checker for Obstacles
    private CollisionChecker CC;

    // Offset to place the obstacles
    private int obsLen;
    private int layoutHeightOffset = 350;

    /////////////////////////////////////////////////////////////////////////////////////

    // Constructor
    public Layout(int wallMariaCount, int steinsGateCount, int pandoraBoxCount, int uranusCount,
            int layoutWidth, int layoutHeight){
        this.wallMariaCount = wallMariaCount;
        this.steinsGateCount = steinsGateCount;
        this.pandoraBoxCount = pandoraBoxCount;
        this.uranusCount = uranusCount;

        this.CC = CollisionChecker.getInstance();
        obstacle_positions = new HashMap<>();
        obstacle_centers = new HashMap<>();

        this.layoutWidth = layoutWidth;
        this.layoutHeight = layoutHeight;
        obsLen = layoutWidth/50;
        //setLayout();
    }

    // Constructor
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

    // Creates obstacles
    public void setLayout(){
        /*EFFECTS: It creates obstacles at some locations by looking at the numbers
        of the obstacle types. It generates the game layout.
        MODIFIES: HashMap<Obstacle, PosVector> obstacle_positions
        */
        Random rnd = new Random(SEED_NUMBER);
        Integer[] obsCounts = {wallMariaCount, steinsGateCount, pandoraBoxCount, uranusCount};
        Obstacle obs;

        for(int type = 0; type < obsCounts.length; type++) {
            for (int i = 0; i < obsCounts[type]; i++) {
                while (true) {
                    PosVector pos = new PosVector(
                            rnd.nextInt(layoutWidth - obsLen - xOffset - 2),
                            rnd.nextInt(layoutHeight - layoutHeightOffset - yOffset) + yOffset);
                    obs = ObstacleFactory.getInstance().getObstacle(type, pos);

                    if (isAvailable(obs)) {
                        obstacle_positions.put(obs, pos);
                        // domainObjArr = [obs, box if exists, remain if exist]
                        Game.getInstance().getDomainObjectArr().add(obs);
                        Game.getInstance().getDomainObjectArr().add(obs.getBox());
                        Game.getInstance().getDomainObjectArr().add(obs.getRemains());
                        break;
                    }
                }
            }
        }
    }


    // Collider check for creating Layout
    private boolean isAvailable(Obstacle newObs){
        /*
        EFFECTS: It compares the newcoming obstacle positions with all the existing obstacles' positions
                If there is an overlapping, it returns false. Otherwise, it returns true.
        */
        for (Obstacle obs: obstacle_positions.keySet()){
            if (CC.checkCollision(newObs, obs)){
                return false;
            }
        }

        return true;
    }

    // Getter method for obstacle_positions
    public static HashMap<Obstacle, PosVector> getObstaclePositions() {
        /*
        EFFECTS: returns a hashmap that keeps the obstacle positions
        */
        return obstacle_positions;
    }

    // Setter method for obstacle_positions
    public static void setObstaclePositions(HashMap<Obstacle, PosVector> newMap) {
         /*
        EFFECTS: assign a new obstacle position array
        MODIFIES: this.obstacle_positions
        */
        obstacle_positions = newMap;
    }

    // Returns center of circles of the paths that some obstacles move in.
    public HashMap<Obstacle, PosVector> getObstacleCenters() {
        /*
        EFFECTS: returns a hashmap that keeps the obstacle centers
        */
        return obstacle_centers;
    }

    /*
    // Changes type of the obstacle.


    // Changes type of the obstacle.
    public void changeTypeObstacle(Obstacle obs){
        Obstacle changeObs;
        String type = obs.getType();
        PosVector pos = obs.getPos();
        removeObstacle(obs);

        if (type.equals("WallMaria")){
            this.wallMariaCount--;
            changeObs = ObstacleFactory.getInstance().getObstacle(0,pos);
        }else if (type.equals("SteinsGate")){
            this.steinsGateCount--;
            changeObs = ObstacleFactory.getInstance().getObstacle(1,pos);
        }else if (type.equals("PandorasBox")){
            this.pandoraBoxCount--;
            changeObs = ObstacleFactory.getInstance().getObstacle(2,pos);
        }else {
            this.uranusCount--;
            changeObs = ObstacleFactory.getInstance().getObstacle(3,pos);
        }
        obstacle_positions.put(changeObs,changeObs.getPosVector());
        Game.getInstance().getDomainObjectArr().add(changeObs);
        Game.getInstance().getDomainObjectArr().add(changeObs.getBox());
        System.out.println("CHANGE");
    }

    // Changes location of an existing obstacle.
    public void changeLocationObstacle(Obstacle obs, PosVector pos){

    }

    // Adds new obstacle to the Layout with given type and location.
    public void addNewObstacle(PosVector pos){
        Obstacle obs = ObstacleFactory.getInstance().getObstacle(3,pos);
        obstacle_positions.put(obs,obs.getPosVector());
        Game.getInstance().getDomainObjectArr().add(obs);
        Game.getInstance().getDomainObjectArr().add(obs.getBox());
        System.out.println("ADD");
    }


    // Removes an obstacle from Layout.
    public void removeObstacle(Obstacle obs){
        Game.getInstance().getDomainObjectArr().remove(obs);
        obstacle_positions.remove(obs);


        String type = obs.getType();
        if (type.equals("WallMaria")){
            this.wallMariaCount--;
        }else if (type.equals("SteinsGate")){
            this.steinsGateCount--;
        }else if (type.equals("PandorasBox")){
            this.pandoraBoxCount--;
        }else {
            this.uranusCount--;
        }

        System.out.println("REMOVE");
    }




}
