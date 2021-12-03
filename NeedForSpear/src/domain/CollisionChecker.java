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

    public Boolean isBetween(int loc, int left, int right) {
        if (loc > right & loc < left) return true;

        return false;
    }

    public Boolean checkPaddleBallCollision(Ball ball, Paddle paddle) {
        int ball_x = ball.posVector.getX();
        int paddle_x = paddle.posVector.getX();
        int paddle_y = paddle.posVector.getY();
        int paddle_length = paddle.getLength();
        int paddle_right = paddle_x + paddle_length;


        if (ball.posVector.getY() > (paddle_y - 30)) {
            if ((ball_x > paddle_x) && (ball_x < paddle_right)) {
                return true;
            }
        }
        return false;
    }

    public Boolean checkCollision(DomainObject object1, DomainObject object2) {
        int object1_x = object1.getPosVector().getX();
        int object1_y = object1.getPosVector().getY();
        int object1_width = object1.getWidth();
        int object1_height = object1.getHeight();

        int object2_x = object2.getPosVector().getX();
        int object2_y = object2.getPosVector().getY();
        int object2_width = object2.getWidth();
        int object2_height = object2.getHeight();

        return (object1_y < object2_y + object2_height) &&
                (object2_y < object1_y + object1_height) &&
                (object1_x < object2_x + object2_width) &&
                (object2_x < object1_x + object1_width);
    }

}
