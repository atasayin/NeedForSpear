package domain;



import domain.util.PosVector;

import java.awt.*;

public class Ball extends DomainObject{
    private final int diameter = 16;
    public int gravity;
    public int yVelocity, xVelocity;

    public Ball(){
        this.posVector = new PosVector(500,500);
        this.gravity = 10;
        this.xVelocity = 20;
        this.yVelocity = 0;
    }

    public void updateVelocity() {
        this.yVelocity += this.gravity;
    }

    public void updatePosition(int x, int y) {
        //pass
    }

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

}

