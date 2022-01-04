package domain;

import util.PosVector;

public class Bullet extends DomainObject {
    private final int FRAME_WIDTH = 1368;
    private final int FRAME_HEIGHT = 766;
    public int yVelocity =10;
    private int width = 20;
    private int height = 20;
    protected boolean outOfScreen;

    public Bullet(int x, int y){
        this.posVector = new PosVector(x, y);
        this.outOfScreen = false;
    }

    @Override
    protected void updatePosition(int x, int y) {

    }
    public PosVector getPosVector() { return this.posVector; }

    public void setPosVector(PosVector pos) {
        this.posVector = pos;
    }
    @Override
    public void updatePosition() {
        this.posVector.setX(this.posVector.getX());
        this.posVector.setY(this.posVector.getY() - this.yVelocity);
    }
    public void setOutOfScreen(Boolean b){
        this.outOfScreen = b;
    }
}
