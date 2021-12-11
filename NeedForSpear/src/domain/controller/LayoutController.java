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

    HashMap<String, Integer> obstacleSettings;

    static LayoutController instance;

    /////////////////////////////////////////////////////////////////////////////////////

    private LayoutController() {
    }

    public static LayoutController getInstance() {
        if (instance == null) {
            instance = new LayoutController();
        }
        return instance;
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
        layout = new Layout(obstacleSettings.get("simpleObstacleCount"),
                obstacleSettings.get("firmObstacleCount"),
                obstacleSettings.get("explosiveObstacleCount"),
                obstacleSettings.get("giftObstacleCount"),
                1368,
                766
        );
        //printLayout();
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

}


