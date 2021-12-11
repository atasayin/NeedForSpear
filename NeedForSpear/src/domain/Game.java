package domain;
import domain.controller.PaddleController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class Game implements IRunListener, ILoadListener, ActionListener {
    static final int FRAME_WIDTH = 1368;
    static final int FRAME_HEIGHT = 766;

    String gameStatus;
    public GameState gameState;
    public Saver saver;
    static Game instance;
    public PaddleController PC;
    public Ball ball;
    public Layout layout;
    public boolean isLoad = false;
    private static final int TIMER_SPEED = 50;
    Player player = null;

    public static int UNITLENGTH_L = 1;

    private javax.swing.Timer game_Timer = new javax.swing.Timer(TIMER_SPEED, this);
    private Game() {
        gameState = new GameState();


/*
        game_Timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //gameState.decreaseTime();
            }
        }, 0, 1000);*/

    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void resumeTime(double remaining) {

    }

    public void loadGame() {
        saver = new Saver();
        saver.loadGame(Game.getInstance().PC, Game.getInstance().ball);

    }

    public void saveGame() {
        saver = new Saver();
        saver.saveGame(Game.getInstance().PC, Game.getInstance().ball, Layout.getObstacle_positions());
    }

    @Override
    public void onClickEvent(HashMap<String, Integer> startParameters, String username, String id) {
        // TODO Auto-generated method stub
        initializeGame(startParameters, username);

        PC = new PaddleController(FRAME_WIDTH,FRAME_HEIGHT);
        this.ball = new Ball();
        Game.getInstance().gameState.isRunning = true;
        System.out.println("Paddle created " + PC.toString());

        Player player = new Player(username, id);
        player.initializeInventory();
        System.out.println(player);
        instance.gameState.setPlayer(player);
        game_Timer.start();

        if(isLoad){
            Game.getInstance().loadGame();
        }
    }

    private void initializeGame(HashMap<String, Integer> startParameters, String username) {

        //gameState.initializeGameState(gameState.layout);
    }

    public ArrayList<DomainObject> getDomainObjectArr() {
        return this.gameState.getDomainObjectArr();
    }

    public void setDomainObjectArr(ArrayList<DomainObject> list) {
        this.gameState.setDomainList(list);
    }

    public void gameOverCheck(){
        Integer chancePoint = instance.gameState.getPlayer().getChance_points();
        if( chancePoint <= 0 ){
            instance.gameState.isRunning = false;
        }
    }

    @Override
    public void onClickEvent() {
        isLoad = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //boolean isDead = Game.getInstance().ball.move();
        if(!Game.getInstance().ball.getIsBall()){
            Integer points = Game.getInstance().gameState.getPlayer().getChance_points() - 1;
            Game.getInstance().gameState.getPlayer().setChance_points(points);
            Game.getInstance().ball.setBall(true);
            System.out.println(Game.getInstance().gameState.getPlayer().getChance_points());
            System.out.println("ball is reseted");

        }

    }
}

