package abilities;

import domain.Game;

import java.util.concurrent.TimeUnit;

public class DoubleAccel implements Runnable{

    public DoubleAccel() { }

    public void activate() {
        this.run();
    }

    public void run() {
        Game.getInstance().ball.xVelocity /= 2;
        Game.getInstance().ball.yVelocity /= 2;
        System.out.println("agam hizi dusurdum");
        try {
            TimeUnit.SECONDS.sleep(15);
            Game.getInstance().ball.xVelocity *= 2;
            Game.getInstance().ball.yVelocity *= 2;
            System.out.println("hizi geri artirdim");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
