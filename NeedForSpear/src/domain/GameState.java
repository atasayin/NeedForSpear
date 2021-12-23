package domain;

import domain.controller.BallController;
import util.PosVector;

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
    protected Player player;
    public static Paddle paddle;
    private java.util.List<IChanceListener> ChanceListeners = new ArrayList<>();


    public GameState() {
        this.paddlePos = new PosVector(200,800);
        this.ballPos = new PosVector(200, 750);
        this.layout = new Layout();
        ballController = new BallController();
        domainObjects = new ArrayList<DomainObject>();
        ObstacleCounts = new HashMap<Integer, Integer>();
        paddle = new Paddle(100,20);
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
        return paddle.getPosVector();
    }

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

    public Paddle getPaddle(){ return paddle; }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player p) { this.player = p; }

    public void setDomainList(ArrayList<DomainObject> list) {
        this.domainObjects = list;
    }

    public void initializeGameState(Layout layout) {

    }
    public void setChance(int chance){
        this.getPlayer().setChance_points(chance);
        for (IChanceListener listener : ChanceListeners) {
            listener.onLoseChance(chance);
        }
    }

    public void addListener( IChanceListener listener) {
        ChanceListeners.add(listener);
    }
}
