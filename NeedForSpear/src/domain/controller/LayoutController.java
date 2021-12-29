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

    private HashMap<String, Integer> obstacleSettings;

    private PosVector mousePos;
    private BitSet keyBits;

    /////////////////////////////////////////////////////////////////////////////////////

    public LayoutController() {
        FRAME_WIDTH = (int) (1368 * 0.6);
        FRAME_HEIGHT = 766;
        mousePos = new PosVector(0,0);
    }

    public void setKeyInput(int keycode){
        this.keyBits.set(keycode);
    }

    public void setMouseInput(int mouseX, int mouseY) {
        mousePos.setX(mouseX);
        mousePos.setY(mouseY);

    }

    public void control(){
        MouseColliderBox mouseColliderBox = new MouseColliderBox(mousePos);
        Obstacle obsCol = layout.getCollideObstacle(mouseColliderBox);;

        System.out.println(keyBits);
        if (obsCol == null){
            layout.addNewObstacle(mousePos);
        }else{

            if (isKeyPressed(68)){
                layout.removeObstacle(obsCol);
                System.out.println("PRESSED D");
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


}


