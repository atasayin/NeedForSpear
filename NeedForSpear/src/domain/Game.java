package domain;
import util.PosVector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;


public class Game implements IRunListener, ILoadListener, ActionListener {
    static final int FRAME_WIDTH = 1368;
    static final int FRAME_HEIGHT = 766;

    String gameStatus;
    public GameState gameState;
    public Saver saver;
    static Game instance;
    //private Timer game_Timer;
    private Paddle paddle;
    private Ball ball;
    public Layout layout;
    public boolean isLoad = false;
    private static final int TIMER_SPEED = 5;
    Player player = null;
    private static final long TOTAL_DEATH_TIME = 4500;
    private long deathInitTime = -100;
    public static int UNITLENGTH_L = 1;
    private boolean isWin = false;
    private long initialTime;
    private int score = 0;
    private static int yOffset = 70;
    private Box box;
    private javax.swing.Timer game_Timer;

    private Game() {
        gameState = new GameState();
        game_Timer = new javax.swing.Timer(TIMER_SPEED, this);



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

    public Timer getT() {
        return instance.game_Timer;

    }

    public void loadGame() {
        saver = new Saver();
        saver.loadGame(Game.getInstance().getPaddle(), Game.getInstance().ball);

    }

    public void saveGame() {
        saver = new Saver();
        saver.saveGame(Game.getInstance().getPaddle(), Game.getInstance().ball, Layout.getObstacle_positions());
    }

    public Integer getScore(int oldScore){
       long CurrentTime = System.currentTimeMillis();
       int NewScore =(int) (oldScore + 300/(double)((CurrentTime-initialTime)/1000));
       //System.out.println(CurrentTime-initialTime);
       return NewScore;
    }
    public void setScore(int newScore){  this.score = newScore;}

    public int getOldScore() {
        return this.score;
    }
    @Override
    public void onClickEvent(HashMap<String, Integer> startParameters, String username, String id) {
        // TODO Auto-generated method stub
        //initializeGame(startParameters, username);

        paddle = new Paddle(FRAME_WIDTH,FRAME_HEIGHT);
        this.ball = new Ball();
        this.ball.setisAlive(false);

        Game.getInstance().gameState.isRunning = true;
        initialTime = System.currentTimeMillis();

        Player player = new Player(username, id);
        player.initializeInventory();
        instance.gameState.setPlayer(player);
        game_Timer.start();

        if(isLoad){
            Game.getInstance().loadGame();
        }
    }

//    private void initializeGame(HashMap<String, Integer> startParameters, String username) {
//
//        //gameState.initializeGameState(gameState.layout);
//    }

    public ArrayList<DomainObject> getDomainObjectArr() {
        return this.gameState.getDomainObjectArr();
    }

    public void setDomainObjectArr(ArrayList<DomainObject> list) {
        this.gameState.setDomainList(list);
    }

     /*EFFECTS: If player has no more chance points it stops running, if player cleared whole layout
        game stops running and player has won.

        MODIFIES: this (Game), isWin, game_Timer, isRunning
    */
    public void gameOverCheck(){
        Integer chancePoint = instance.gameState.getPlayer().getChance_points();
        if( chancePoint <= 0 ){
            instance.gameState.isRunning = false;
            game_Timer.stop();
        }
        else if(instance.getDomainObjectArr().size() == 0){
            //System.out.println("bitti");
            this.isWin= true;
            game_Timer.stop();
        }
    }
    public Paddle getPaddle() {return paddle;}

    public Ball getBall() {return ball;}

    public boolean getIsWin(){ return this.isWin;}


    @Override
    public void onClickEventDo() {
        isLoad = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //boolean isDead = Game.getInstance().ball.move();
        /// List size review
        gameOverCheck();
        if(!Game.getInstance().ball.checkAlive()) {

            if (deathInitTime < 0) {
                // if dead but just now dead, initialize deathInitTime
                deathInitTime = System.currentTimeMillis();
                Integer points = Game.getInstance().gameState.getPlayer().getChance_points() - 1;
                Game.getInstance().gameState.setChance(points);
            } else {
                // he's been dead
                // check how long he's been dead
                long deathTime = System.currentTimeMillis() - deathInitTime;
                if (deathTime > TOTAL_DEATH_TIME) {
                    // if he's been dead long enough, call this code
                    Game.getInstance().ball.setOutOfScreen(true);
                    PosVector pos = new PosVector(FRAME_WIDTH/2, yOffset+1);
                    Game.getInstance().ball.setPosVector(pos);
                    Game.getInstance().ball.setyVelocity(0);

                    deathInitTime = -1L;  // and re-initialize deathInitTime
                }
                Game.getInstance().ball.setOutOfScreen(true);
            }
        }

    }
}

