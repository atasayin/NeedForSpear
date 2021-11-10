package domain;

public class Player {
    private int id;
    private String userName;
    private PlayerState playerState;

    public Player(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Player(int id, String userName, PlayerState pState){
        this.id = id;
        this.userName = userName;
        this.playerState = pState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }
}
