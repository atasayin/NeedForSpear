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
        this.length = 15;
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

    public void move(int direction) {
        // MODIFIES: The position and the velocity of the paddle.
		/* EFFECTS: by checking at the boundary conditions of the setup, it updates the position and velocity
		of the paddle.
		*/

        // Paddle goes left
        if (direction == GO_LEFT_DIC) { goLeft(); }
        // Paddle goes right
        else if (direction == GO_RIGHT_DIC) { goRight(); }
        // Paddle rotates counter clockwise (A)
        else if (direction == ROTATE_CCLOKC_DIC) { rotateCClockwise(); }
        // Paddle rotates clockwise (D)
        else if (direction == ROTATE_CLOCK_DIC) { rotateClockwise(); }
        // Paddle goes faster left
        else if (direction == GO_FAST_LEFT_DIC) { goFastLeft(); }
        // Paddle goes faster right
        else if (direction == GO_FAST_RIGHTH_DIC) { goFastRight(); }

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

    private void goLeft(){
        setSpeed(-normalSpeed, 0);
        if ((getPosVector().getX() >= normalSpeed) && (getPosVector().getX()  <= FRAME_WIDTH - getLength())) {
            updatePosition(getDx(), getDy());
        } else {
            updatePosition(0, getDy());
        }
    }

    private void goRight(){
        setSpeed(normalSpeed, 0);
        if ((getPosVector().getX() <= FRAME_WIDTH - getLength() - normalSpeed) && (getPosVector().getX() >= 0)) {
            updatePosition(getDx(), getDy());
        } else {
            updatePosition(0, getDy());
        }
    }

    private void rotateCClockwise(){
        // | -50 --- 50 |

        if (angle > MIN_ANGLE_LIMIT + angleSpeed){
            setAngle(angle - angleSpeed);
        }else{
            setAngle(MIN_ANGLE_LIMIT);
        }


    }

    private void rotateClockwise(){
        // | -50 --- 50 |

        if (angle < MAX_ANGLE_LIMIT - angleSpeed){
            setAngle(angle + angleSpeed);
        }else{
            setAngle(MAX_ANGLE_LIMIT);
        }



    }

    private void goFastLeft(){
        setSpeed(-fastSpeed, 0);
        if ((getPosVector().getX()  >= fastSpeed) && (getPosVector().getX() <= FRAME_WIDTH - getLength())) {
            updatePosition(getDx(), getDy());
        } else {
            updatePosition(0, getDy());
        }
    }

    private void goFastRight(){
        setSpeed(fastSpeed, 0);
        if ((getPosVector().getX()  <= FRAME_WIDTH - getLength() - fastSpeed) && (getPosVector().getX()  >= 0)) {
            updatePosition(getDx(), getDy());
        } else {
            updatePosition(0, getDy());
        }
    }
    public int getLength() { return this.length; }

}
