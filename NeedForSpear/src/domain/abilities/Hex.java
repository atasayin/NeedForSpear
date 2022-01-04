package domain.abilities;

import domain.Cannon;
import domain.Game;

import java.util.concurrent.TimeUnit;

public class Hex  implements Runnable{

    public Hex() {}


    public void run() {
        int x = Game.getInstance().getPaddle().getPosVector().getX();
        int y = Game.getInstance().getPaddle().getPosVector().getY();

        Cannon c1 = new Cannon(x+5,y);
        Cannon c2 = new Cannon(x-(Game.getInstance().getPaddle().getLength()+5),y);
        Game.getInstance().getPaddle().setHasCannon(true);
        Game.getInstance().getPaddle().setCannons(c1,c2);

        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Game.getInstance().getPaddle().setHasCannon(false);
        Game.getInstance().gameState.getPlayer().setIsMagicalAbilityActive(false);
    }

}
