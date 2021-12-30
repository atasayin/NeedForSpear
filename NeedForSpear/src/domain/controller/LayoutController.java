package domain.controller;

import domain.Game;
import domain.Layout;
import domain.obstacle.Obstacle;
import domain.obstacle.WallMaria;

import java.util.HashMap;
import java.util.Iterator;

public class LayoutController {

    /////////////////////////////////////////////////////////////////////////////////////

    Layout layout;
    private int FRAME_WIDTH;
    private int FRAME_HEIGHT;

    HashMap<String, Integer> obstacleSettings;

    /////////////////////////////////////////////////////////////////////////////////////

    public LayoutController() {
        FRAME_WIDTH = 1368;
        FRAME_HEIGHT = 766;
    }

    // For mouse events
    public boolean getInput(int input) {

        switch (input) {

            default:
                return false;
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
        layout.setLayout();

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
        for (Obstacle obs : layout.getObstaclePositions().keySet()){
            System.out.println(obs.toString());
        }

    }

}


