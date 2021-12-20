package domain;

import util.PosVector;

import java.util.ArrayList;
import java.util.List;

public class Ball extends DomainObject {
    private final int FRAME_WIDTH = 1368;
    private final int FRAME_HEIGHT = 766;
    public double gravity;
    public int yVelocity, xVelocity;
    private int width = 20;
    private int height = 20;
    private boolean is_unstoppable;
    private List<IGameListener> gameListeners = new ArrayList<>();
    private boolean isAlive;
    private static int yOffset = 70;
    private static int xOffset = 175;

    private boolean outOfScreen;

    public Ball() {
        this.posVector = new PosVector(800, 400);
        this.gravity = 1;
        this.xVelocity = 3;
        this.yVelocity = -40;
        this.is_unstoppable = false;
        this.isAlive = false;

    }

    public Ball(int xVel, int yVel) {
        this.posVector = new PosVector(100, 100);
        this.gravity = 10;
        this.xVelocity = xVel;
        this.yVelocity = yVel;
        this.is_unstoppable = false;
        this.outOfScreen = false;
        //this.gameListeners.add(RunGameObjects);
    }

    public void updateVelocity() {
        this.yVelocity += this.gravity;
        this.yVelocity = Math.min(((int) (this.yVelocity + this.gravity)), 40);
    }

    public void updatePosition(int x, int y) {
        this.posVector.setX(x);
        this.posVector.setY(y);

    }

    @Override
    public void updatePosition() {
        this.posVector.setX(this.posVector.getX() + this.xVelocity);
        this.posVector.setY(this.posVector.getY() + this.yVelocity);
    }

    // When the ball reflects from a vertical surface, it reverses its velocity in x-direction.
    // When a collision of this type is detected, the handler will command the ball domain.controller to reverse the x-velocity.
    public void reflectFromVertical() {
        if (!this.is_unstoppable) {
            this.xVelocity *= -1;
        }
    }

    // When the ball reflects from a horizontal surface, it reverses its velocity in y-direction.
    // When a collision of this type is detected, the handler will command the ball domain.controller to reverse the y-velocity.
    public void reflectFromHorizontal() {
        if (!this.is_unstoppable) {
            this.yVelocity *= -1;
        }
    }


    public void reflectFromPaddle() {
        this.yVelocity *= -1.25;
    }

    public void reflectFromTopWall() {
        this.yVelocity *= -1.10;
    }

    public void reflectFromSideWall() {
        this.xVelocity *= -1;
    }


    public PosVector getBallVelocity() {
        return new PosVector(this.xVelocity, this.yVelocity);
    }

    public void checkWallCollision() {
        if (this.posVector.getX() < 8) {
            this.reflectFromSideWall();
        } else if (this.posVector.getX() > (FRAME_WIDTH - xOffset - 20)) {
            this.reflectFromSideWall();
        }
        else if (this.posVector.getY()<yOffset) {
            this.reflectFromTopWall();
        }
    }

    public void checkPaddleCollision() {

    }

    public Boolean checkAlive() {
        return (this.posVector.getY() <= (FRAME_HEIGHT));
    }

    public Boolean move() {
        if (this.checkAlive() != true) {

           /* for (IGameListener listener : autoModeListeners) {
                listener.onEvent();
            }*/
            return false;
        }
        if(!this.isAlive){
            return false;
        }
        this.checkWallCollision();
        // check paddle collision here
        this.updateVelocity();
        this.updatePosition();

        return true;

    }

    public void setisAlive(Boolean b){
        this.isAlive = b;
    }

    public void setOutOfScreen(Boolean b){
        this.outOfScreen = b;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setPosVector(PosVector pos) {
        this.posVector = pos;
    }

    public void setyVelocity(Integer a) {
        this.yVelocity = a;
    }

    public void setXVelocity(Integer a) {
        this.xVelocity = a;
    }

    public void setGravity(Integer a) {
        this.gravity = a;
    }

    public void set_is_unstoppable(boolean is_unstoppable) {
        this.is_unstoppable = is_unstoppable;
    }
}
