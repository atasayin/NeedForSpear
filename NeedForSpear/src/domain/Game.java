package domain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class Game implements IRunListener {

    String gameStatus;
    public GameState gameState;
    public PlayerState playerState;
    public Client client;
    static Game instance;
    private Timer game_Timer;

    public static int UNITLENGTH_L;

    private Game() {
        gameState = new GameState();
        playerState = new PlayerState();
        game_Timer = new Timer();
        game_Timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //gameState.decreaseTime();
            }
        }, 0, 1000);

    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void resumeTime(double remaining) {
        game_Timer = new Timer();
        game_Timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //gameState.decreaseTime();
            }
        }, 0, 200);
    }

    public void cancelTime() {
        game_Timer.cancel();
    }

    public void loadGame(String type) {
        client = new Client();
        client.loadGame(gameState.players.get(0).getPlayerState().getAbilities(), gameState.ObstacleCounts);

    }

    public void saveGame(Player player, String type) {
        client = new Client();
        client.saveGame(player.getUserName(),gameState.players.get(0).getPlayerState().getScore(),
                gameState.players.get(0).getPlayerState().getChance_points() ,gameState.players.get(0).getPlayerState().getAbilities(),gameState.ObstacleCounts);
    }

    @Override
    public void onClickEvent(HashMap<String, Double> startParameters, String username) {
        // TODO Auto-generated method stub
        initializeGame(startParameters, username);
        Game.UNITLENGTH_L = startParameters.get("unitLengthL").intValue();
    }

    private void initializeGame(HashMap<String, Double> startParameters, String username) {

        gameState.initializeGameState(gameState.layout);
    }

    public ArrayList<DomainObject> getDomainObjectArr() {
        return this.gameState.getDomainObjectArr();
    }

    public void setDomainObjectArr(ArrayList<DomainObject> list) {
        this.gameState.setDomainList(list);
    }

    public ArrayList<Player> getPlayers() {
        return this.gameState.getPlayers();
    }

    public void addPlayer(Player p) {
        this.gameState.addPlayer(p);
    }
}

