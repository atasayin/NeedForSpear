package domain;

import domain.util.PosVector;

public abstract class DomainObject {

    protected PosVector posVector;
    protected int dx;
    protected int dy;
    protected boolean isWidthHeightTaken = false;
    private int width;
    private int height;
    private double angle;

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void updateAngle() {
        this.angle += Math.toRadians(1);
    }

    public void updatePosition() {
        posVector.setX(posVector.getX() + dx);
        posVector.setY(posVector.getY() + dy);
    }

    public PosVector getPosVector() {
        return posVector;
    }

    public void setLoc(PosVector pos) {
        this.posVector = pos;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public void setSpeed(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public String toString() {
        return String.format("Domain Object with Loc: %s%n", this.getPosVector().toString());
    }

    public boolean isWidthHeightTaken() {
        return isWidthHeightTaken;
    }

    public void setWidthHeight(int width, int height) {
        if (!this.isWidthHeightTaken()) {
            this.setWidth(width);
            this.setHeight(height);
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    /*
     * * EFFECTS: Turns the given domain object in the array of string representing
     * the properties of the given domain object. Output is in the order type,for
     * non shooter objects name, location x, location y , speed x, speed y, and
     * angle in radians for shooter
     *
     */

    //public abstract ArrayList<String> makeList();
}
