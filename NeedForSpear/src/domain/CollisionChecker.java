package domain;

import domain.abilities.PaddleExpansion;
import domain.obstacle.Obstacle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollisionChecker {
    static CollisionChecker instance;
    private ArrayList<IBoxListener> BoxListeners = new ArrayList<>();
    private ArrayList<IRemainsListener> RemainListeners = new ArrayList<>();
    private  ArrayList<Box> boxes = new ArrayList<>();
    private  ArrayList<RemainingPieces> remains = new ArrayList<>();


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
        int paddle_length = paddle.getLength();
        int paddle_right = paddle_x + paddle_length;


        if (ball.posVector.getY() > (paddle_y -37)) {
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


    // Returns true if collision is vertical, false otherwise
    public Boolean findCollisionDirection(DomainObject object1, DomainObject object2) {
        int object1_x = object1.getPosVector().getX();
        int object1_y = object1.getPosVector().getY();
        int object1_width = object1.getWidth();
        int object1_height = object1.getHeight();
        int obj1_left = object1_x;
        int obj1_right = object1_x + object1_width;
        int obj1_top = object1_y;
        int obj1_bottom = object1_y + object1_height;

        int object2_x = object2.getPosVector().getX();
        int object2_y = object2.getPosVector().getY();
        int object2_width = object2.getWidth();
        int object2_height = object2.getHeight();
        int obj2_left = object2_x;
        int obj2_right = object2_x + object2_width;
        int obj2_top = object2_y;
        int obj2_bottom = object2_y + object2_height;

        int[] intersections = new int[4];
        intersections[0] = Math.abs(obj1_right - obj2_left);
        intersections[1] = Math.abs(obj1_left - obj2_right);
        intersections[2] = Math.abs(obj1_top - obj2_bottom);
        intersections[3] = Math.abs(obj1_bottom - obj2_top);
        int min_pos = Arrays.stream(intersections).min().getAsInt();

        return (min_pos <= 1);





    }
    public void ChecktoDelete() {
        Obstacle toBeDeleted = null;
        Box boxToBeDeleted = null;
        RemainingPieces remainsToBeDeleted = null;
        Game.getInstance().getPaddle().updatePosition(0, 0);
        Game.getInstance().getBall().move();
        if (boxes != null) {
            for (Box box : boxes) {
                box.updatePosition();
                if(checkCollision(box,Game.getInstance().getPaddle())){
                    box.updateAbility();
                    Game.getInstance().getDomainObjectArr().remove(box);
                    boxToBeDeleted = box;
                }

            }
        }
        if (remains != null) {
            for (RemainingPieces r : remains) {
                r.updatePosition();
                if(checkCollision(r,Game.getInstance().getPaddle())){
                    int chance = Game.getInstance().gameState.getPlayer().getChance_points();
                    Game.getInstance().gameState.setChance(chance-1);
                    Game.getInstance().getDomainObjectArr().remove(r);
                    remainsToBeDeleted = r;
                }

            }
        }

        if (instance.checkPaddleBallCollision(Game.getInstance().getBall(), Game.getInstance().getPaddle())) {
            Game.getInstance().getBall().reflectFromPaddle();
        }

        //if (Game.getInstance().ball.getPosVector().getY() < 0) Game.getInstance().ball.reflectFromHorizontal();

        for (Obstacle obs : Layout.obstacle_positions.keySet()) {
            if (instance.checkCollision(Game.getInstance().getBall(), obs)) {
                if (obs.getHit()){
                    String typeCheck = obs.getType();
                    if(typeCheck.equals("GiftOfUranus")){
                         Game.getInstance().getDomainObjectArr().add(obs.getBox());
                         boxes.add(obs.getBox());
                        for (IBoxListener listener : BoxListeners) {
                            listener.dropBox(obs.getPosVector().getX(), obs.getPosVector().getY());
                        }
                    }
                    if(typeCheck.equals("PandorasBox")){
                        Game.getInstance().getDomainObjectArr().add(obs.getRemains());
                        remains.add(obs.getRemains());
                        for (IRemainsListener listener : RemainListeners) {
                            listener.dropRemains(obs.getPosVector().getX(), obs.getPosVector().getY());
                        }
                    }
                    Game.getInstance().getDomainObjectArr().remove(obs);
                    toBeDeleted = obs;
                }

                if (instance.findCollisionDirection(Game.getInstance().getBall(), obs)) {
                    Game.getInstance().getBall().reflectFromVertical();
                } else {
                    Game.getInstance().getBall().reflectFromHorizontal();
                }
            }
        }
        if (toBeDeleted != null) Layout.obstacle_positions.remove(toBeDeleted);
        if (boxToBeDeleted != null) {
            boxes.remove(boxToBeDeleted);
            Game.getInstance().getDomainObjectArr().remove(boxToBeDeleted);
        }
        if (remainsToBeDeleted != null) {
            remains.remove(remainsToBeDeleted);
            Game.getInstance().getDomainObjectArr().remove(remainsToBeDeleted);
        }

    }

    public void addListener( IBoxListener listener) {
        BoxListeners.add(listener);
    }

    public ArrayList<Box> getBoxes() {
        return this.boxes;
    }

    public ArrayList<RemainingPieces> getRemainingPieces() {
        return this.remains;
    }
}
