package domain;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Ymir implements Runnable{
    private int period;
    private double[] weights;
    private Random random;



    public Ymir() {
        this.period = 30;
        this.weights = new double[]{0.33, 0.33, 0.34};
        long currentTime = System.currentTimeMillis();
        random = new Random(currentTime);
    }

    // Dice roll every <period> seconds to decide whether an ability will be used or not
    public Boolean rollDice(){
        return random.nextBoolean();
    }

    // A double between 0 and 1 is generated and then compared with the normalized weights of abilities to choose among them.
    public YmirAbilities chooseAbility() {
        double threshold = random.nextDouble();
        if (threshold < weights[0]) {return YmirAbilities.InfiniteVoid;}
        else if (threshold < weights[1]) return YmirAbilities.DoubleAccel;
        else return YmirAbilities.HollowPurple;

    }



    public void setPeriod(int period) {
        this.period = period;
    }

    // Weights are doubles which will be normalized
    public void setProbabilities(double w1, double w2, double w3) {
        double sum = w1 + w2 + w3;
       weights[0] = w1 / sum;
        weights[1] = weights[0] + w2 / sum;
        weights[2] = weights[3] / sum;
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (rollDice()) {
                YmirAbilities ability = chooseAbility();
                System.out.println(ability);


            } else {
                System.out.println("No Ymir ability this time!");
            }

        }


    }
}
