package domain.abilities;

import domain.Game;

import java.util.concurrent.TimeUnit;

public class DoubleAccel implements Runnable{

    public DoubleAccel() { }

    public void activate() {
        this.run();
    }

    public void run() {
        Game.getInstance().getBall().xVelocity /= 2;
        Game.getInstance().getBall().yVelocity /= 2;
        System.out.println("agam hizi dusurdum");
        try {
            TimeUnit.SECONDS.sleep(15);
            Game.getInstance().getBall().xVelocity *= 2;
            Game.getInstance().getBall().yVelocity *= 2;
            System.out.println("hizi geri artirdim");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
