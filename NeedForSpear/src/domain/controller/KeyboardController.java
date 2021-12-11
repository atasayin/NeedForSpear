package domain.controller;

import domain.*;
import domain.util.PosVector;


public class KeyboardController {

    public boolean getInput(int input) {
        Paddle paddle = Game.getInstance().getPaddle();

        // returns a boolean indicating whether to restart timer
        switch (input) {
            case 37: // left
                if (isGameRunning()) {
                    paddle.move(1);
                }
                return false;
            case 39: // right
                if (isGameRunning()) {
                    paddle.move(2);
                }
                return false;
            case 65: // rotate counter-clockwise (A)
                if (isGameRunning()) {
                    paddle.move(3);
                }
                return false;
            case 68: // rotate clockwise (D)
                if (isGameRunning()) {
                    paddle.move(4);
                }
                return false;
            case 83: // s
                Game currentGame = Game.getInstance();
                currentGame.saveGame();
                //SAVE SETTINGS
                return false;
            case 76: // l
                Game currentGame1 = Game.getInstance();
                currentGame1.loadGame();
                //LOAD SETTINGS
                return false;
            default:
                return false;
        }
    }


    public void released(Paddle paddle) {
    }

    private boolean isGameRunning(){
        return Game.getInstance().gameState.isRunning;
    }
}