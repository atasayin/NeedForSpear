package domain.controller;

import domain.*;


public class KeyboardController {

    public boolean getInput(int input, Object obj) {
        // returns a boolean indicating whether to restart timer

        switch (input) {
            case 37: // left
                Game.getInstance().PC.getPaddle().setSpeed(-8, 0);

                //Game.getInstance().PC.getPaddle().move(2);
                if( (Game.getInstance().PC.getPaddle().getPosVector().x !=0) && (Game.getInstance().PC.getPaddle().getPosVector().x<= Game.getInstance().PC.getPaddle().FRAME_WIDTH - Game.getInstance().PC.getPaddle().getLength())) {
                    Game.getInstance().PC.getPaddle().updatePosition(Game.getInstance().PC.getPaddle().getDx(),Game.getInstance().PC.getPaddle().getDy());
                }
                else{
                    Game.getInstance().PC.getPaddle().updatePosition(0,Game.getInstance().PC.getPaddle().getDy());
                }
                System.out.println("bu cok farkli bi yerden gelen");
                System.out.println(Game.getInstance().PC.getPaddle().getPosVector().x);
                return false;
            case 39: // right
                Game.getInstance().PC.getPaddle().setSpeed(8, 0);
                if( (Game.getInstance().PC.getPaddle().getPosVector().x != Game.getInstance().PC.getPaddle().FRAME_WIDTH-Game.getInstance().PC.getPaddle().getLength()) && (Game.getInstance().PC.getPaddle().getPosVector().x >=0)) {
                    Game.getInstance().PC.getPaddle().updatePosition(Game.getInstance().PC.getPaddle().getDx(),Game.getInstance().PC.getPaddle().getDy());
                }
                else{
                    Game.getInstance().PC.getPaddle().updatePosition(0,Game.getInstance().PC.getPaddle().getDy());
                }
                System.out.println("bu cok farkli bi yerden gelen");
                System.out.println(Game.getInstance().PC.getPaddle().getPosVector().x);
                //System.out.println(Game.getInstance().PC.getPaddle().getPosVector().x);
                return false;
            case 65: // rotate counter-clockwise
                Game.getInstance().PC.getPaddle().move(3);
                return false;
            case 68: // rotate clockwise
                Game.getInstance().PC.getPaddle().move(4);
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