package domain.controller;

import util.PosVector;

public class BallController {
    public domain.Ball ball;
    public int gravity;
    public int yVelocity, xVelocity;

    // Two constructors for the domain.controller, we will be using whichever is more useful.
    public BallController() {
        this.ball = new domain.Ball();
        this.gravity = 0;
        this.xVelocity = 0;
        this.yVelocity = 0;
    }

    public BallController(int xVelocity, int yVelocity, int gravity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.gravity = gravity;
        this.ball = new domain.Ball();
    }

    //Game handler will delegate the domain.controller to update the velocity
    public void updateVelocity() {
        this.yVelocity += this.gravity;
    }


    // Game handler commands the ball domain.controller to update the position
    // This method takes the ball position and screen dimensions from the GameState, updates it according to the velocity,
    // and returns it.
    public PosVector updatePosition(PosVector ballPos) {
        ballPos.setX( ballPos.getX() + this.xVelocity );
        ballPos.setY( ballPos.getY() + this.yVelocity );
        return ballPos;
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
