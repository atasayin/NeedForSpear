package domain;
import domain.controller.PaddleController;
import domain.util.PosVector;

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
    public PaddleController PC;
    public Ball ball;
    public Layout layout;
    public boolean isLoad = false;
    private static final int TIMER_SPEED = 5;
    Player player = null;
    private static final long TOTAL_DEATH_TIME = 5000;
    private long deathInitTime = -100;
    public static int UNITLENGTH_L = 1;
    private boolean isWin = false;
    private long initialTime;
    private double score =0;

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

    public double getScore(double oldScore){
       long CurrentTime = System.currentTimeMillis();
       double NewScore = oldScore + 300/(double)((CurrentTime-initialTime)/1000);
       System.out.println(CurrentTime-initialTime);
       return NewScore;
    }
    public void setScore(double newScore){  this.score = newScore;}

    public double getOldScore() {
        return this.score;
    }
    @Override
    public void onClickEvent(HashMap<String, Integer> startParameters, String username, String id) {
        // TODO Auto-generated method stub
        initializeGame(startParameters, username);

        PC = new PaddleController(FRAME_WIDTH,FRAME_HEIGHT);
        this.ball = new Ball();
        this.ball.setisAlive(false);

        Game.getInstance().gameState.isRunning = true;
        initialTime = System.currentTimeMillis();
        System.out.println(ball.posVector.getX() + " " + ball.posVector.getY());
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
            game_Timer.stop();
        }
        else if(instance.getDomainObjectArr().size() == 0){
            System.out.println("bitti");
            this.isWin= true;
            game_Timer.stop();
        }
    }

    public boolean getIsWin(){ return this.isWin;}


    @Override
    public void onClickEvent() {
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
                Game.getInstance().gameState.getPlayer().setChance_points(points);
            } else {
                // he's been dead
                // check how long he's been dead
                long deathTime = System.currentTimeMillis() - deathInitTime;
                if (deathTime > TOTAL_DEATH_TIME) {
                    // if he's been dead long enough, call this code
                    Game.getInstance().ball.setOutOfScreen(true);
                    System.out.println(Game.getInstance().gameState.getPlayer().getChance_points());
                    System.out.println("ball is reseted");
                    PosVector pos = new PosVector(FRAME_WIDTH/2, 1);
                    Game.getInstance().ball.setPosVector(pos);
                    Game.getInstance().ball.setyVelocity(0);

                    System.out.println(Game.getInstance().ball.posVector.getY());
                    deathInitTime = -1L;  // and re-initialize deathInitTime
                }
                Game.getInstance().ball.setOutOfScreen(true);
            }
        }
        /*if(!Game.getInstance().ball.getIsBall()){
            Integer points = Game.getInstance().gameState.getPlayer().getChance_points() - 1;
            Game.getInstance().gameState.getPlayer().setChance_points(points);

            if(Game.getInstance().ball.getPosVector().getY() > FRAME_HEIGHT){
                Game.getInstance().ball.setOutOfScreen(true);
                System.out.println(Game.getInstance().gameState.getPlayer().getChance_points());
                System.out.println("ball is reseted");
                PosVector pos = new PosVector(FRAME_WIDTH/2, 1);
                Game.getInstance().ball.setPosVector(pos);
                Game.getInstance().ball.setBall(true);

                System.out.println(Game.getInstance().ball.posVector.getY());
            }
            Game.getInstance().ball.setOutOfScreen(true);

        }*/

    }
}

