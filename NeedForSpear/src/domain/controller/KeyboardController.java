package domain.controller;

import domain.Game;

public class KeyboardController {

    public boolean getInput(int input, Object obj) {
        // returns a boolean indicating whether to restart timer

        switch (input) {
            case 37: // left
                ((Shooter) obj).setSpeed(-Game.UNITLENGTH_L / 10, 0); // to preserve aesthetics
                return false;
            case 39: // right
                ((Shooter) obj).setSpeed(Game.UNITLENGTH_L / 10, 0); // to preserve aesthetics
                return false;
            case 65: // rotate counter-clockwise
                ((Shooter) obj).updateAngle(input);
                return false;
            case 68: // rotate clockwise
                ((Shooter) obj).updateAngle(input);
                return false;
            case 32: // space character
                ((Shooter) obj).shoot();
                return false;
            case 67: // c
                ((Shooter) obj).change();
                return false;
            case 66: // b
                isBlender = true;
                return false;
            case 83: // s
                Game currentGame = Game.getInstance();
                currentGame.saveGame(currentGame.getPlayers().get(0), Game.getInstance().gameState.saveType);
                return false;
            case 76: // l
                Game currentGame1 = Game.getInstance();
                currentGame1.loadGame(Game.getInstance().gameState.saveType);
                return false;
            default:
                return false;
        }


    return true;
    }





}