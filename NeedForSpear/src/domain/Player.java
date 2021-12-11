package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player implements IRunListener{
    // Player attributes
    private int id;
    private String userName;

    // Attributes that this player has
    private int chance_points;
    private int score;
    private HashMap<Integer, Integer> abilities;
    private List<InventoryListener> inventoryListeners = new ArrayList<>();
    private List<TimeListener> timeListeners = new ArrayList<>();

    public Player(int id, String userName) {
        this.id = id;
        this.userName = userName;
        this.chance_points = 0;
        this.score = 0;
        abilities = new HashMap<>();
    }

    public Player(int id, String userName, int chance, int score, HashMap<Integer, Integer> abilities){
        this.id = id;
        this.userName = userName;
        this.chance_points = chance;
        this.score = score;
        this.abilities = abilities;

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

    public void incrementChances() {
        this.chance_points++;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addListener(InventoryListener listener) {
        inventoryListeners.add(listener);
    }

    public void removeListener(InventoryListener listener) {
        inventoryListeners.remove(listener);
    }

    public void addTimelistener(TimeListener l) {
        this.timeListeners.add(l);
    }

    public int getChance_points() {
        return chance_points;
    }

    public void setChance_points(int chance_points) {
        this.chance_points = chance_points;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public HashMap<Integer, Integer> getAbilities() {
        return abilities;
    }

    public void initializeInventory() {
        this.abilities = new HashMap<>();
        abilities.put(1,0);
        abilities.put(2,0);
        abilities.put(3,0);
        abilities.put(4,0);
    }

    public void notifyAllInventoryListeners(String toUpdate) {
        for (InventoryListener listener : inventoryListeners) {
            listener.onInventoryChange(toUpdate);
        }
    }
    public void updateAbilitiesInventory(int powerupType, int changeInPowerupCount) {
        int currentCount = abilities.get(powerupType);
        abilities.put(powerupType, currentCount + changeInPowerupCount);
        notifyAllInventoryListeners("powerup");

    }

    @Override
    public void onClickEvent(HashMap<String, Integer> runSettings, String username) {
        initializeInventory();
        notifyAllInventoryListeners("all");

    }

    private void notifyTimeListeners() {
        for (TimeListener listener : timeListeners) {
            listener.onTimeEvent();
        }
    }
}

