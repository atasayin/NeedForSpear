package domain;

import util.PosVector;

public class GameState {
    public PosVector ballPos, paddlePos, ballVelocity;
    public Layout layout;



    public GameState() {
        this.paddlePos = new PosVector(200,800);
        this.ballPos = new PosVector(200, 750);
        this.layout = new Layout();
    }

    public GameState(PosVector ballPos, PosVector paddlePos, Layout layout) {
        this.ballPos = ballPos;
        this.paddlePos = paddlePos;
        this.layout = layout;
    }


    public PosVector getBallPos() {
        return ballPos;
    }

    public void setBallPos(PosVector ballPos) {
        this.ballPos = ballPos;
    }

    public PosVector getPaddlePos() {
        return paddlePos;
    }

    public void setPaddlePos(PosVector paddlePos) {
        this.paddlePos = paddlePos;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }
}
