package domain;

import domain.util.PosVector;

import java.awt.*;

public class Ball extends DomainObject {
    private final int FRAME_WIDTH = 1368;
    private final int FRAME_HEIGHT = 766;
    private final int diameter = 16;
    public double gravity;
    public int yVelocity, xVelocity;
    private int width = 16;
    private int height = 16;
    private boolean is_unstoppable;

    public Ball() {
        this.posVector = new PosVector(540, 100);
        this.gravity = 1;
        this.xVelocity = 3;
        this.yVelocity = 0;
        this.is_unstoppable = false;
    }

    public Ball(int xVel, int yVel) {
        this.posVector = new PosVector(500, 500);
        this.gravity = 10;
        this.xVelocity = xVel;
        this.yVelocity = yVel;
        this.is_unstoppable = false;
    }

    public void updateVelocity() {
        this.yVelocity += this.gravity;
        this.yVelocity = Math.min(((int) (this.yVelocity + this.gravity)), 50);
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
        this.yVelocity *= -1.15;
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
        } else if (this.posVector.getX() > (FRAME_WIDTH - 40)) {
            this.reflectFromSideWall();
        } else if (this.posVector.getY() < 0) {
            this.reflectFromTopWall();
        }
    }

    public void checkPaddleCollision() {

    }

    public Boolean checkAlive() {
        return (this.posVector.getY() <= (FRAME_HEIGHT - 40));
    }

    public Boolean move() {
        if (this.checkAlive() != true) {
            return false;
        }
        this.checkWallCollision();
        // check paddle collision here
        this.updateVelocity();
        this.updatePosition();

        return true;

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

    public void set_is_unstoppable(boolean is_unstoppable) {
        this.is_unstoppable = is_unstoppable;
    }
}
