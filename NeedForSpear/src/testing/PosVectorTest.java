package testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.PosVector;
import java.lang.Math;

import static org.junit.jupiter.api.Assertions.*;

class PosVectorTest {

    PosVector pv, pv2;


    @BeforeEach
    void setUp() {
        PosVector center;
        pv = new PosVector(300,300);
        pv2 = new PosVector(100,100);

    }

    @AfterEach
    void tearDown() {
        pv = null;
        pv2 = null;
    }

    @Test
    void addVector() {
        // true, false, null
        //deniz
    }

    @Test
    void halfVelocity() {
        // true, false
        // beyza
    }

    @Test
    void getTheta() {
        // true, false , arithmetic exception
        // ata, yigithan

    }
    @Test
    void getThetaTrue() {
        pv.getTheta(pv2);
        assertEquals(pv.getTheta(pv2), 0.7853981633974483);
        assertTrue(pv.repOK());
    }

    @Test
    void getThetaFalse() {
         int dx = pv.getX();
         int dy = pv.getY();
         pv.getTheta(pv2);
         assertTrue(pv.repOK());
         assertNotEquals(pv.getTheta(pv2), dy/dx );

    }


    // getters //oya
}