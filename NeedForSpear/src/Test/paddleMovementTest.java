package Test;

import domain.DomainObject;
import domain.Paddle;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.PosVector;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class paddleMovementTest {


    Paddle paddle1;
    Paddle paddle2;
    ArrayList<DomainObject> objList;


    @BeforeEach
    void setUp() {

        System.out.println("Running: Set Up");

        paddle1 = new Paddle(1368, 766);
        //paddle2 = new Paddle(1368, 766);
        objList = new ArrayList<>();

        objList.add(paddle1);
        //objList.add(paddle2);

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
        paddle1.setPosVector(new PosVector(360, 1100));
        paddle1.move(1);
        paddle1.getPosVector();
        assertTrue(paddle1.getDx() != 360);



    }

    @Test
    void checkPaddleGoRight() {
        paddle1.setPosVector(new PosVector(360, 1100));
        paddle1.move(2);
        paddle1.getPosVector();
        assertTrue(paddle1.getDx() != 360);

    }

    @Test
    void checkPaddleRotateCClockwise() {
        // | -45 --- 45 |

       // paddle1.setPosVector(new PosVector(360, 1100));
        paddle1.setAngle(0);
        paddle1.move(3);
        paddle1.getAngle();
        assertTrue(paddle1.getAngle() != 0);
        //assertTrue();

    }

    @Test
    void checkPaddleRotateClockwise() {
        // | -45 --- 45 |
        paddle1.setAngle(0);
        paddle1.move(4);
        paddle1.getAngle();
        assertTrue(paddle1.getAngle() != 0);
        //paddle1.setPosVector(new PosVector(360, 1100));

        //assertTrue(paddle1.getAngle());

    }

    @Test
    void checkPaddleGoFastLeft() {
        paddle1.setPosVector(new PosVector(360, 1100));
        paddle1.move(5);
        paddle1.getPosVector();
        //assertTrue();
    }

    @Test
    void checkPaddleGoFastRight() {
        paddle1.setPosVector(new PosVector(360, 1100));
        paddle1.move(6);
        paddle1.getPosVector();

    }



}
