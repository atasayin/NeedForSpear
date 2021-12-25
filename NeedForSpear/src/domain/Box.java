package domain;

import util.PosVector;

import java.util.HashMap;
import java.util.Random;

public class Box extends DomainObject {

    /////////////////////////////////////////////////////////////////////////////////////

    private int yVelocity = 10;
    private int abilityType;
    private int SEED_NUMBER = 10;
    private PosVector posVector;
    protected int height = 30;
    protected int width = 30;
    private boolean isBoxCatched =true;


    /////////////////////////////////////////////////////////////////////////////////////

    public Box(double x, double y) {
        Random rnd = new Random();
        this.posVector = new PosVector((int)x,(int)y);
        this.setSpeed(0, 0);
        this.abilityType = rnd.nextInt(4) + 1;

    }

    @Override
    public void updatePosition() {
        this.posVector.setX(this.posVector.getX());
        this.posVector.setY(this.posVector.getY() + this.yVelocity);
    }
    public void updatePosition(int x, int y) {
        this.posVector.setX(x);
        this.posVector.setY(y);

    }
    public PosVector getPosVector() { return this.posVector; }

    public int getHeight() { return this.height; }

    public int getWidth() { return this.width; }

    public void setPosVector(PosVector pos) {
        this.posVector = pos;
    }

    public void updateAbility(){
        if(isBoxCatched){
            int currentAbilityNum = Game.getInstance().gameState.getPlayer().getAbilities().get(abilityType);
            currentAbilityNum = currentAbilityNum+1;
            Game.getInstance().gameState.getPlayer().getAbilities().put(abilityType,currentAbilityNum);
            isBoxCatched =false;
        }


    }

    public void setIsBoxCatched(boolean isCatched){
        isBoxCatched = isCatched;
    }
}