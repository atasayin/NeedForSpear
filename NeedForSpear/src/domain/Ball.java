package domain;

import domain.util.PosVector;

import java.awt.*;

public class Ball extends DomainObject{
    private final int WIDTH = 1368;
    private final int HEIGHT = 766;

    private final int diameter = 16;
    public double gravity;
    public int yVelocity, xVelocity;

    public Ball(){
        this.posVector = new PosVector(200,5);
        this.gravity = 0;
        this.xVelocity = 2;
        this.yVelocity = 0;
    }

    public Ball(int xVel, int yVel) {
        this.posVector = new PosVector(500,500);
        this.gravity = 10;
        this.xVelocity = xVel;
        this.yVelocity = yVel;
    }

    public void updateVelocity() {
        this.yVelocity += this.gravity;
    }

    public void updatePosition(int x, int y) {
        //pass
    }

    @Override
    public void updatePosition() {
        this.posVector.setX( this.posVector.getX() + this.xVelocity );
        this.posVector.setY( this.posVector.getY() + this.yVelocity );
    }

    // When the ball reflects from a vertical surface, it reverses its velocity in x-direction.
    // When a collision of this type is detected, the handler will command the ball domain.controller to reverse the x-velocity.
    public void reflectFromVertical() {
        this.xVelocity *= -1;
    }

    // When the ball reflects from a horizontal surface, it reverses its velocity in y-direction.
    // When a collision of this type is detected, the handler will command the ball domain.controller to reverse the y-velocity.
    public void reflectFromHorizontal() {
        this.yVelocity *= -1;
    }


    public PosVector getBallVelocity() {
        return new PosVector(this.xVelocity, this.yVelocity);
    }

    public void checkWallCollision(){
        if (this.posVector.getX() < 8) {
            this.reflectFromVertical();
        }
        else if (this.posVector.getX() > (WIDTH-40)) {
            this.reflectFromVertical();
        }
    }

    public Boolean checkAlive() {
        return (this.posVector.getY() <= HEIGHT);
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

/*
    if (Game.getInstance().gameState.isRunning) {
        if ((Game.getInstance().PC.getPaddle().getPosVector().x != 0) && (Game.getInstance().PC.getPaddle().getPosVector().x <= Game.getInstance().PC.getPaddle().FRAME_WIDTH - Game.getInstance().PC.getPaddle().getLength())) {
            Game.getInstance().PC.getPaddle().updatePosition(Game.getInstance().PC.getPaddle().getDx(), Game.getInstance().PC.getPaddle().getDy());
        } else {
            Game.getInstance().PC.getPaddle().updatePosition(0, Game.getInstance().PC.getPaddle().getDy());
        }
        */


}

