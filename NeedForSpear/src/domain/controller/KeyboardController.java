package domain.controller;

import domain.*;


public class KeyboardController {

    public boolean getInput(int input, Object obj) {
        // returns a boolean indicating whether to restart timer

        switch (input) {
            case 37: // left
                PaddleController.move(1);
                return false;
            case 39: // right
                PaddleController.move(2);
                return false;
            case 65: // rotate counter-clockwise
                PaddleController.move(3);
                return false;
            case 68: // rotate clockwise
                PaddleController.move(4);
                return false;
            case 32: // rotate clockwise

                return false;
            case 83: // s
                Game currentGame = Game.getInstance();
                //SAVE SETTINGS
                return false;
            case 76: // l
                Game currentGame1 = Game.getInstance();
                //LOAD SETTINGS
                return false;
            default:
                return false;
        }
    }


    public void released(Paddle paddle) {
    }
}