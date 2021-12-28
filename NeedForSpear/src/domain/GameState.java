package domain;

import domain.controller.BallController;
import domain.controller.PaddleController;
import util.PosVector;

import java.util.ArrayList;
import java.util.HashMap;

public class GameState {
    public PosVector ballPos, paddlePos;
    public Layout layout;
    public static BallController ballController;
    public boolean isRunning;
    private ArrayList<DomainObject> domainObjects;
    public HashMap<Integer, Integer> ObstacleCounts;
    protected Player player;
    public static PaddleController pc;
    private java.util.List<IChanceListener> ChanceListeners = new ArrayList<>();


    public GameState() {
        this.paddlePos = new PosVector(200,800);
        this.ballPos = new PosVector(200, 750);
        this.layout = new Layout();
        ballController = new BallController();
        domainObjects = new ArrayList<DomainObject>();
        ObstacleCounts = new HashMap<Integer, Integer>();
        pc = new PaddleController(100,20);
        player = new Player();
    }

    public GameState(PosVector ballPos, PosVector paddlePos, Layout layout) {
        this.ballPos = ballPos;
        this.paddlePos = paddlePos;
        this.layout = layout;
    }

    public boolean getIsRunning(){
        return this.isRunning;
    }

    public ArrayList<DomainObject> getDomainObjectArr() {
        return this.domainObjects;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player p) { this.player = p; }

    public PaddleController getPC(){
        return pc;
    }

    public void setDomainList(ArrayList<DomainObject> list) {
        this.domainObjects = list;
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
