package domain;

import domain.controller.BallController;
import domain.controller.PaddleController;
import domain.util.PosVector;

import java.util.ArrayList;
import java.util.HashMap;

public class GameState {
    public PosVector ballPos, paddlePos, ballVelocity;
    public Layout layout;
    public static BallController ballController;
    public boolean isRunning;
    private ArrayList<DomainObject> domainObjects;
    public HashMap<Integer, Integer> ObstacleCounts;
    private double time;
    protected ArrayList<Player> players;
    public static PaddleController pc;


    public GameState() {
        this.paddlePos = new PosVector(200,800);
        this.ballPos = new PosVector(200, 750);
        this.layout = new Layout();
        ballController = new BallController();
        domainObjects = new ArrayList<DomainObject>();
        ObstacleCounts = new HashMap<Integer, Integer>();
        players = new ArrayList<Player>();
        pc = new PaddleController(100,20);
    }

    public GameState(PosVector ballPos, PosVector paddlePos, Layout layout) {
        this.ballPos = ballPos;
        this.paddlePos = paddlePos;
        this.layout = layout;
    }

    public double getTime() {
        return this.time;
    }
    public PosVector getBallPos() {
        return ballPos;
    }

    public void setBallPos(PosVector ballPos) {
        this.ballPos = ballPos;
    }

    public PosVector getPaddlePos() {
        return pc.getPaddle().getPosVector();
    }

    //public void updatePaddlePosition() {
      //  this.pc.getPaddle().updatePosition() ;}


    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public boolean getIsRunning(){
        return this.isRunning;
    }

    public ArrayList<DomainObject> getDomainObjectArr() {
        return this.domainObjects;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
    public PaddleController getPC(){
        return pc;
    }

    public void setDomainList(ArrayList<DomainObject> list) {
        this.domainObjects = list;
    }

    public void initializeGameState(Layout layout) {

    }
}