package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerState  implements IRunListener{
    private int chance_points;
    private int score;
    private HashMap<Integer, Integer> abilities;
    private List<InventoryListener> inventoryListeners = new ArrayList<>();
    private List<TimeListener> timeListeners = new ArrayList<>();

    public PlayerState() {
        this.chance_points = 0;
        this.score = 0;
        this.abilities = new HashMap<>();
    }

    public PlayerState(int chance_points, int score, HashMap<Integer, Integer> abilities) {
        this.chance_points = chance_points;
        this.score = score;
        this.abilities = abilities;
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

    public void initializeInventory(HashMap<Integer, Integer> inv, int count) {
        inv.put(1, count);
        inv.put(2, count);
        inv.put(3, count);
        inv.put(4, count);
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
        initializeInventory(abilities, 0);
        notifyAllInventoryListeners("all");

    }

    private void notifyTimeListeners() {
        for (TimeListener listener : timeListeners) {
            listener.onTimeEvent();
        }
    }
}
