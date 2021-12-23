package domain;

import domain.obstacle.Obstacle;
import domain.obstacle.WallMaria;
import util.PosVector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GameTest implements ActionListener {
    Game g;
    Ball ball;
    Paddle paddle;
    WallMaria wm;
    static final int FRAME_WIDTH = 1368;
    static final int FRAME_HEIGHT = 766;
    private static final int TIMER_SPEED = 5;
    private javax.swing.Timer game_Timer = new javax.swing.Timer(TIMER_SPEED,  this);


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        System.out.println("Running: Set Up");
        HashMap<String, Integer> runSettings = new HashMap<String, Integer>();
        HashMap<Obstacle, PosVector> obstacle_positions = new HashMap<Obstacle, PosVector>();
        wm = new WallMaria(400,400);
        obstacle_positions.put(wm,wm.getPosVector());
        runSettings.put("screenWidth",  FRAME_WIDTH);
        runSettings.put("screenHeight",  FRAME_HEIGHT);
         g = Game.getInstance();
         g.onClickEvent(runSettings,"Oya", "99");
         paddle =g.getPaddle();
         ball =g.getBall();


    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.out.println("Running: Tear Down");
        g.getDomainObjectArr().remove(wm);

    }

    @org.junit.jupiter.api.Test
    void IsGameOver(){
        g.gameState.getPlayer().setChance_points(0);
        g.gameOverCheck();
        assertEquals(g.gameState.getIsRunning(), false);
    }
    @org.junit.jupiter.api.Test
    void GameNotOver(){
        g.gameState.getPlayer().setChance_points(2);
        g.gameOverCheck();
        assertEquals(g.gameState.getIsRunning(), true);
    }

    @org.junit.jupiter.api.Test
    void GameNotOverIsNotWinCheck(){
        g.gameState.getPlayer().setChance_points(2);
        g.getDomainObjectArr().add(wm);
        g.gameOverCheck();
        assertEquals(g.getIsWin(), false);
    }
    @org.junit.jupiter.api.Test
    void IsTimerStoppedGameLose(){
        g.gameState.getPlayer().setChance_points(0);
        g.gameOverCheck();
        assertEquals(g.getT().isRunning(), false);
    }

    @org.junit.jupiter.api.Test
    void TimerRunningObstacleLeft(){
        g.gameState.getPlayer().setChance_points(2);
        g.getDomainObjectArr().add(wm);
        g.gameOverCheck();
        assertEquals(g.getT().isRunning(), true);
    }

    @org.junit.jupiter.api.Test
    void IsObjectArrayNull(){
        g.gameState.getPlayer().setChance_points(2);
        assertEquals(g.getDomainObjectArr().isEmpty(), true);
    }

    @org.junit.jupiter.api.Test
    void IsGetChanceWork(){
        assertEquals(g.gameState.getPlayer().getChance_points(), 3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}