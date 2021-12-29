package domain.controller;

import domain.Game;
import domain.Layout;
import domain.MouseColliderBox;
import domain.obstacle.Obstacle;
import domain.obstacle.WallMaria;
import util.PosVector;

import java.util.HashMap;

public class LayoutController {

    /////////////////////////////////////////////////////////////////////////////////////

    private Layout layout;
    private int FRAME_WIDTH;
    private int FRAME_HEIGHT;

    private HashMap<String, Integer> obstacleSettings;

    /////////////////////////////////////////////////////////////////////////////////////

    public LayoutController() {
        FRAME_WIDTH = (int) (1368 * 0.6);
        FRAME_HEIGHT = 766;
    }

    // For mouse events
    public void getInput(int mouseX, int mouseY) {
        PosVector pos = new PosVector(mouseX,mouseY);

        if (isEmpty(pos)){
            layout.addNewObstacle(pos);
        }else{




        }


    }

    // Gets random Layout after the obstacle settings
    public Layout getRandomLayout(){

        layout = new Layout(
                obstacleSettings.get("simpleObstacleCount"),
                obstacleSettings.get("firmObstacleCount"),
                obstacleSettings.get("explosiveObstacleCount"),
                obstacleSettings.get("giftObstacleCount"),
                FRAME_WIDTH,
                FRAME_HEIGHT
        );

        return layout;
    }

    public Layout getLayout(){
        return layout;
    }

    public void setObstacleSettings(HashMap<String, Integer> obstacleSettings){
        this.obstacleSettings = obstacleSettings;

    }

//    public void addObstaclesToGame(HashMap<String, Integer> obstacleSettings){
//        Game game = Game.getInstance();
//
//        for (String key : obstacleSettings.keySet()){
//            if (key.equals("simpleObstacleCount")){
//                game.getDomainObjectArr().add(new WallMaria())
//            }
//        }
//    }

    // Prints layout
    private void printLayout(){
        System.out.println(obstacleSettings);
        for (Obstacle obs : layout.obstacle_positions.keySet()){
            System.out.println(obs.toString());
        }

    }

    private boolean isEmpty(PosVector pos){
        MouseColliderBox mouseColliderBox = new MouseColliderBox(pos);
        return layout.isAvailable(mouseColliderBox);

    }


}


