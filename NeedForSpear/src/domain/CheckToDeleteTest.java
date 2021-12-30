
package domain;

import domain.obstacle.GiftOfUranus;
import domain.obstacle.SteinsGate;
import domain.obstacle.WallMaria;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.PosVector;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CheckToDeleteTest{
    Ball ball;
    Paddle paddle;
    DomainObject wallmaria, steinsgate, giftofuranus;
    ArrayList<DomainObject> objList;
    ArrayList<Box> boxes = new ArrayList<>();
    ArrayList<RemainingPieces> remains = new ArrayList<>();
    CollisionChecker CS = CollisionChecker.getInstance();

    @BeforeEach
    void setUp() {
        System.out.println("Running: Set Up");
        ball = new Ball();
        paddle = new Paddle(1368, 766);
        wallmaria = new WallMaria(400,400);
        steinsgate = new SteinsGate(400,400);
        giftofuranus = new GiftOfUranus(400,400);
        objList = new ArrayList<>();
        objList.add(ball);
        objList.add(paddle);
        objList.add(wallmaria);
        objList.add(steinsgate);
        objList.add(giftofuranus);


    }

    @AfterEach
    void tearDown() {
        System.out.println("Running: Teardown");
        for (DomainObject obj : objList) {
            obj = null;
        }
    }


    @Test
    void checktoDeleteRemoveSimpleObstacle() {
        paddle.setPosVector(new PosVector(500, 500));
        ball.setPosVector(new PosVector(500, 505));



    }

    @Test
    void addListener() {
    }

    @Test
    void getBoxes() {
    }

    @Test
    void getRemainingPieces() {
    }
}

