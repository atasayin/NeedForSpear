package domain;

import util.PosVector;

public class Box extends DomainObject {

    /////////////////////////////////////////////////////////////////////////////////////

    public static final int FRAME_WIDTH = 1368;
    public static final int FRAME_HEIGHT = 766;
    public static final int PADDLE_THICKNESS = 20;
    protected int length;
    private int thickness;
    private int speed;
    private int width;
    private static int xOffset = 150;

    private int xVelocity = 10;
    private int yVelocity = 10;

    private PosVector posVector;

    /////////////////////////////////////////////////////////////////////////////////////

    public Box(double x, double y) {
        this.posVector = new PosVector((int)x,(int)y);
        this.setSpeed(0, 0);
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

    public void setPosVector(PosVector pos) {
        this.posVector = pos;
    }


}