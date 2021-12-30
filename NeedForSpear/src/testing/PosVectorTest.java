package testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.PosVector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PosVectorTest {

    PosVector pv, pv2;
    private final int FRAME_WIDTH = 1368;
    private final int FRAME_HEIGHT = 766;

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

    @Test
    void  getX() {
        assertEquals(pv.getX(),300);
        assertEquals(pv2.getX(),100);

    }

    @Test
    void setXTest(){
        pv2.setX(-45);
        assertEquals(pv2.repOK(),false);

        pv2.setX(FRAME_WIDTH+1);
        assertEquals(pv2.repOK(),false);

        pv.setX(97);
        assertEquals(pv.repOK(),true);

    }

    @Test
    void getY() {
        assertEquals(pv.getY(),300);
        assertEquals(pv2.getY(),100);
    }

    @Test
    void setYTest(){
        pv2.setY(-67);
        assertEquals(pv2.repOK(),false);
        pv.setY(97);
        assertEquals(pv.repOK(),true);

    }



}