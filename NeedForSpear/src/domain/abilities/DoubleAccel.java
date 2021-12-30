package domain.abilities;

import domain.Game;

import java.util.concurrent.TimeUnit;

public class DoubleAccel implements Runnable{

    public DoubleAccel() { }

    public void run() {
        Game.getInstance().getBall().xVelocity /= 2;
        Game.getInstance().getBall().yVelocity /= 2;
        System.out.println("agam hizi dusurdum");
        try {
            System.out.println("After double accel");
            System.out.println(Game.getInstance().getBall().xVelocity);
            System.out.println(Game.getInstance().getBall().yVelocity);
            TimeUnit.SECONDS.sleep(15);
            Game.getInstance().getBall().xVelocity *= 2;
            Game.getInstance().getBall().yVelocity = (Game.getInstance().getBall().yVelocity*2)-15;
            System.out.println("hizi geri artirdim");
            System.out.println(Game.getInstance().getBall().xVelocity);
            System.out.println(Game.getInstance().getBall().yVelocity);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
