package domain;

import util.PosVector;

public class Cannon extends DomainObject{

    public static final int FRAME_WIDTH = 1368;
    public static final int FRAME_HEIGHT = 766;
    private double MIN_ANGLE_LIMIT = -50;
    private double MAX_ANGLE_LIMIT = 50;
    public static final int PADDLE_THICKNESS = 20;

    // move constants
    private int GO_LEFT_DIC = 1;
    private int GO_RIGHT_DIC = 2;
    private int ROTATE_CCLOKC_DIC = 3;
    private int ROTATE_CLOCK_DIC = 4;
    private int GO_FAST_LEFT_DIC = 5;
    private int GO_FAST_RIGHTH_DIC = 6;

    protected int length;
    private int thickness;
    private double angle;
    private double angleSpeed;
    private int normalSpeed;
    private int fastSpeed;
    private int width;
    private int height = 10;

    protected Bullet bullet;

    public Cannon(int X, int Y){
        this.length = 5;
        this.width = this.length;
        this.thickness = PADDLE_THICKNESS;
        this.posVector = new PosVector(X, Y);
        this.angle = 0;
        this.angleSpeed = 10;
        normalSpeed = length/2;
        fastSpeed = 2*length;
        this.setSpeed(0,0);
        this.bullet = new Bullet(X,Y);
    }

    public Bullet getBullet(){
        return this.bullet;
    }
    public PosVector getPosVector() { return this.posVector; }

    public void setPosVector(PosVector pos) {
        this.posVector = pos;
    }

    public int getNormalSpeed(){ return this.normalSpeed; }
    public int getFastSpeed() { return this.fastSpeed; }


    public void updatePosition(int  x, int  y) {
        this.posVector.setX(posVector.getX() + x);
        this.posVector.setY(posVector.getY() + y);
    }

    public int getLength() { return this.length; }

}
