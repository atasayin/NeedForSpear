package domain.abilities;

import domain.Game;

public class ChanceGiving {

    public ChanceGiving() {}

    public void activate() {
        Game.getInstance().gameState.getPlayer().incrementChances();
    }
}
