package abilities;

import domain.Game;

import java.util.concurrent.TimeUnit;

public class PaddleExpansion implements Runnable{

    public PaddleExpansion() {}

    public void run() {
        Game.getInstance().PC.getPaddle().expandPaddle();
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Game.getInstance().PC.getPaddle().normalizePaddle();
    }
}
