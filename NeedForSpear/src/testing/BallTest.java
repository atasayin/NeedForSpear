package testing;

import domain.Ball;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.PosVector;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {
    Ball ball;

    @BeforeEach
    void setUp() {
        ball = new Ball();
    }

    @AfterEach
    void tearDown() {
        ball = null;
    }

    @Test
    void moveWhenDead() {
        // Test of movement of dead ball, it should not move if it is dead.
        ball.setPosVector(new PosVector(-200, -200));
        ball.move();
        assertEquals(-200, ball.getPosVector().getX());

    }

    @Test
    void nullPositionTest() {
        // Test to throw exception when position is null
        ball.setPosVector(null);
        assertThrows(Exception.class, ()-> {
            ball.move();});
    }

    @Test
    void correctMovement1() {
        int xPos = ball.getPosVector().getX();
        int xVel = ball.xVelocity;
        ball.setisAlive(true);
        ball.move();
        int lastPos = ball.getPosVector().getX();
        assertEquals(xPos + xVel, lastPos);
    }

    @Test
    void correctMovement2() {
        int yPos = ball.getPosVector().getY();
        int yVel = ball.yVelocity;
        ball.setisAlive(true);
        ball.move();
        int lastPos = ball.getPosVector().getY();
        assertEquals(yPos + yVel + 2*ball.gravity, lastPos);
    }

    @Test
    void movementCheck() {
        int xPos = ball.getPosVector().getX();
        int yPos = ball.getPosVector().getY();
        ball.setisAlive(true);
        ball.move();
        assertNotEquals(xPos, ball.getPosVector().getX());
        assertNotEquals(yPos, ball.getPosVector().getY());

    }

    @Test
    void movementFeedbackTrue() {
        ball.setisAlive(true);
        assertTrue(ball.move());
    }

    @Test
    void movementFeedbackFalse() {
        assertFalse(ball.move());
    }
}