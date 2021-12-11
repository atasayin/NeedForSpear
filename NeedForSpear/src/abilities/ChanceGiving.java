package abilities;

import domain.Game;

public class ChanceGiving {

    public ChanceGiving() {}

    public void activate() {
        Game.getInstance().getPlayer().incrementChances();
    }
}
