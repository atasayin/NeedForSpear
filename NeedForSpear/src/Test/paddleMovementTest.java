package Test;

import domain.DomainObject;
import domain.Paddle;
import domain.Ball;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class paddleMovementTest {

    Ball ball;
    Paddle paddle;
    ArrayList<DomainObject> objList;


    @BeforeEach
    void setUp() {

        System.out.println("Running: Set Up");
        ball = new Ball();
        paddle = new Paddle(1368, 766);
        objList = new ArrayList<>();
        objList.add(ball);
        objList.add(paddle);

    }

    @AfterEach
    void tearDown() {

        System.out.println("Running: Teardown");
        for (DomainObject obj : objList) {
            obj = null;
        }

    }

    @Test
    void checkPaddleGoLeft() {

    }

    @Test
    void checkPaddleGoRight() {

    }

    @Test
    void checkPaddleRotateCClockwise() {

    }

    @Test
    void checkPaddleRotateClockwise() {

    }

    @Test
    void checkPaddleGoFastLeft() {

    }

    @Test
    void checkPaddleGoFastRight() {

    }



}
