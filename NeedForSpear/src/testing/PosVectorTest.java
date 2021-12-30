package testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.PosVector;

import static org.junit.jupiter.api.Assertions.*;

class PosVectorTest {

    PosVector pv, pv2;


    @BeforeEach
    void setUp() {
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

    // getters //oya
}