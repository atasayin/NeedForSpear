package abilities;

import domain.Game;

import java.util.concurrent.TimeUnit;

public class UnstopppableBall implements Runnable{


    public UnstopppableBall() {}


    public void activate() {
        this.run();
    }

    public void run() {
        Game.getInstance().ball.set_is_unstoppable(true);
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Game.getInstance().ball.set_is_unstoppable(false);

    }

}


