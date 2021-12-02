package domain;

public class CollisionChecker {
    private final int HEIGHT = 766;
    private final int WIDTH = 1368;
    static CollisionChecker instance;

    private CollisionChecker(){

    }

    public static CollisionChecker getInstance() {
        if (instance == null) {
            instance = new CollisionChecker();
        }
        return instance;
    }

    public Boolean checkPaddleBallCollision(Ball ball, Paddle paddle) {
        int ball_x = ball.posVector.getX();
        int paddle_x = paddle.posVector.getX();
        int paddle_y = paddle.posVector.getY();
        int paddle_radius = WIDTH/20;
        int paddle_left = paddle_x - paddle_radius;
        int paddle_right = paddle_x + paddle_radius;


        if (ball.posVector.getY() > (paddle_y - 30)) {
            if ((ball_x > paddle_left) && (ball_x < paddle_right)) {
                return true;
            }
        }
        return false;
    }

}
