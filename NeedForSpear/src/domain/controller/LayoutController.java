package domain.controller;

import domain.Layout;
import domain.MouseColliderBox;
import domain.obstacle.Obstacle;
import util.PosVector;

import java.util.BitSet;
import java.util.HashMap;

public class LayoutController {

    /////////////////////////////////////////////////////////////////////////////////////

    private Layout layout;
    private int FRAME_WIDTH;
    private int FRAME_HEIGHT;
    private int OBS_OFFSET = 35;

    private int PANEL_WIDTH;
    private int PANEL_HEIGHT;
    private double C_PANEL_WIDTH = 0.6;

    private double C_PADDLE_OFFSET_HEIGHT_LINE = 0.8;

    private HashMap<String, Integer> obstacleSettings;

    private PosVector mousePos;
    private BitSet keyBits;


    /////////////////////////////////////////////////////////////////////////////////////

    public LayoutController() {
        FRAME_WIDTH = (int) (1368 * C_PANEL_WIDTH);
        FRAME_HEIGHT = 766;
        PANEL_WIDTH = (int) (1368 * C_PANEL_WIDTH);
        PANEL_HEIGHT = 766;

        mousePos = new PosVector(0,0);
    }

    // Deletes an obstacle in (X,Y) if its present
    public void deleteObs(int x, int y) {
        setMouseInput(x, y);
        MouseColliderBox mouseColliderBox = new MouseColliderBox(mousePos);
        Obstacle obsCol = layout.getCollideObstacle(mouseColliderBox);

        if (obsCol != null) {
            layout.removeObstacle(obsCol);
        }
    }

    // Add an obstacle in (X,Y) if its not present
    // Change type of an obstacle in (X,Y) if its present
    public void addOrChangeObstacle(int x, int y){
        setMouseInput(x, y);
        MouseColliderBox mouseColliderBox = new MouseColliderBox(mousePos);
        Obstacle obsCol = layout.getCollideObstacle(mouseColliderBox);

        if ( isInRange(x, y) ){
            if (obsCol == null) {
                PosVector pos = new PosVector(x, y);
                layout.addNewObstacle(pos);
            }else{
                layout.changeTypeObstacle(obsCol);
            }
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



    private boolean isKeyPressed(int keyCode) {
        return keyBits.get(keyCode);
    }

    // Set mousePos for mouseCollider
    private void setMouseInput(int mouseX, int mouseY) {
        mousePos.setX(mouseX);
        mousePos.setY(mouseY);

    }

    // if location is in the panel view
    private boolean isInRange(int x, int y){
        return  x > 0
                && x < PANEL_WIDTH
                && y > 0
                && y < (PANEL_HEIGHT * C_PADDLE_OFFSET_HEIGHT_LINE - OBS_OFFSET);
    }

    public void setKeyInput(int keycode){
        this.keyBits.set(keycode);
    }




}


