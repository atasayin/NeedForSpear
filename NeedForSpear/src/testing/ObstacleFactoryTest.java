package testing;

import domain.obstacle.*;
import util.PosVector;

import static org.junit.jupiter.api.Assertions.*;

class ObstacleFactoryTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getInstance() {
        assertNotEquals(ObstacleFactory.getInstance(), null);
    }

    @org.junit.jupiter.api.Test
    void getWallMaria() {
        WallMaria wm = new WallMaria(400,400);
        ObstacleFactory of = ObstacleFactory.getInstance();
        assertSame(wm.getClass(),of.getObstacle(3, new PosVector(400,400)).getClass());
    }

    @org.junit.jupiter.api.Test
    void getSteinsGate() {
        SteinsGate sg = new SteinsGate(400,400);
        ObstacleFactory of = ObstacleFactory.getInstance();
        assertSame(sg.getClass(),of.getObstacle(1, new PosVector(400,400)).getClass());
    }

    @org.junit.jupiter.api.Test
    void getPandorasBox() {
        PandorasBox pb = new PandorasBox(400,400);
        ObstacleFactory of = ObstacleFactory.getInstance();
        assertSame(pb.getClass(),of.getObstacle(0, new PosVector(400,400)).getClass());
    }

    @org.junit.jupiter.api.Test
    void getGiftofUranus() {
        GiftOfUranus gu = new GiftOfUranus(400,400);
        ObstacleFactory of = ObstacleFactory.getInstance();
        assertSame(gu.getClass(),of.getObstacle(2, new PosVector(400,400)).getClass());
    }

}