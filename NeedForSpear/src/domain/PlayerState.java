package domain;

import java.util.HashMap;

public class PlayerState {
    private int chance_points;
    private int score;
    private HashMap<String, Integer> abilities;

    public PlayerState() {
        this.chance_points = 0;
        this.score = 0;
        this.abilities = new HashMap<>();
    }

    public PlayerState(int chance_points, int score, HashMap<String, Integer> abilities) {
        this.chance_points = chance_points;
        this.score = score;
        this.abilities = abilities;
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

    public HashMap<String, Integer> getAbilities() {
        return abilities;
    }

    public void setAbilities(HashMap<String, Integer> abilities) {
        this.abilities = abilities;
    }
}
