package domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Layout {
    // Represents the locations and types of the objects in the game.

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
}
