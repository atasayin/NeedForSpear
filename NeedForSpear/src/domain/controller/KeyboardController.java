package domain.controller;

import domain.*;
import domain.util.PosVector;


public class KeyboardController {

    public boolean getInput(int input) {
        Paddle paddle = Game.getInstance().PC.getPaddle();

        // returns a boolean indicating whether to restart timer
        int speed = paddle.getNormalSpeed();
        int fastSpeed = paddle.getFastSpeed();

        switch (input) {
            case 87: // w
                Game.getInstance().ball.setisAlive(true);


            case 37: // left
                paddle.setSpeed(-speed, 0);

                if (Game.getInstance().gameState.isRunning) {
                    if ((paddle.getPosVector().x >= speed) && (paddle.getPosVector().x <= paddle.FRAME_WIDTH - paddle.getLength())) {
                        paddle.updatePosition(paddle.getDx(), paddle.getDy());
                    } else {
                        paddle.updatePosition(0, paddle.getDy());
                    }
                }
//                System.out.println("bu cok farkli bi yerden gelen");
//                System.out.println(Game.getInstance().PC.getPaddle().getPosVector().x);
                return false;
            case 39: // right

                paddle.setSpeed(speed, 0);
                if (Game.getInstance().gameState.isRunning) {
                    if ((paddle.getPosVector().x <= paddle.FRAME_WIDTH - paddle.getLength() - speed) && (paddle.getPosVector().x >= 0)) {
                        paddle.updatePosition(paddle.getDx(), paddle.getDy());
                    } else {
                        paddle.updatePosition(0, paddle.getDy());
                    }
                }
//                System.out.println("bu cok farkli bi yerden gelen");
//                System.out.println(paddle.getPosVector().x);
                //System.out.println(Game.getInstance().PC.getPaddle().getPosVector().x);
                return false;
            case 65: // rotate counter-clockwise
                paddle.move(3);
                return false;
            case 68: // rotate clockwise
                paddle.move(4);
                return false;
            case 32: // rotate clockwise

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
}