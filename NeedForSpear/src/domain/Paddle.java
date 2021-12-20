package domain;

import util.PosVector;

public class Paddle extends DomainObject{

	/////////////////////////////////////////////////////////////////////////////////////

	public static final int FRAME_WIDTH = 1368;
	public static final int FRAME_HEIGHT = 766;
	public static final int PADDLE_THICKNESS = 20;

	private double MIN_ANGLE_LIMIT = -45;
	private double MAX_ANGLE_LIMIT = 45;

	protected int length;
	private int thickness;
	private double angle;
	private double angleSpeed;
	private int normalSpeed;
	private int fastSpeed;
	private int width;
	private int height = PADDLE_THICKNESS;
	private static int xOffset = 150;

	/////////////////////////////////////////////////////////////////////////////////////

	public Paddle(int fWidth, int fHeight) {
		this.length = fWidth/10;
		this.width = this.length;
		this.thickness = PADDLE_THICKNESS;
		this.posVector = new PosVector((fWidth - length)/2, fHeight - (this.thickness * 4));
		this.angle = 0;
		this.angleSpeed = 10;
		normalSpeed = length/2;
		fastSpeed = 2*length;
		this.setSpeed(0,0);
	}

	public int getLength() { return this.length; }

	public int getThickness() { return this.thickness; }

	public double getAngle() { return this.angle; }

	public void setAngle(double angle) { this.angle = angle; }

	public double getAngleSpeed() { return this.angleSpeed; }

	public void setAngleSpeed(double angleSpeed) {this.angleSpeed = angleSpeed; }

	public PosVector getPosVector() { return this.posVector; }

	public void setPosVector(PosVector pos) {
		this.posVector = pos;
	}

	public int getNormalSpeed(){ return this.normalSpeed; }
	public int getFastSpeed() { return this.fastSpeed; }

	// Used for Paddle Expansion ability
	public void expandPaddle() {
		this.length *=2;
	}

	// Normalize paddle after ability
	public void normalizePaddle() {
		this.length /=2;
	}

	public void move(int direction) {
		// Paddle goes left
		if (direction == 1) { goLeft(); }
		// Paddle goes right
		else if (direction == 2) { goRight(); }
		// Paddle rotates counter clockwise (A)
		else if (direction == 3) { rotateCClockwise(); }
		// Paddle rotates clockwise (D)
		else if (direction == 4) { rotateClockwise(); }
		// Paddle goes faster left
		else if (direction == 5) { goFastLeft(); }
		// Paddle goes faster right
		else if (direction == 6) { goFastRight(); }

	}


	public void updatePosition(int  x, int  y) {
		this.posVector.setX(posVector.getX() + x);
		this.posVector.setY(posVector.getY() + y);
	}

	private void goLeft(){
		setSpeed(-normalSpeed, 0);
		if ((getPosVector().x >= normalSpeed) && (getPosVector().x <= FRAME_WIDTH - getLength())) {
			updatePosition(getDx(), getDy());
		} else {
			updatePosition(0, getDy());
		}
	}

	private void goRight(){
		setSpeed(normalSpeed, 0);
		if ((getPosVector().x <= FRAME_WIDTH - getLength() - normalSpeed - xOffset) && (getPosVector().x >= 0)) {
			updatePosition(getDx(), getDy());
		} else {
			updatePosition(0, getDy());
		}
	}

	private void rotateCClockwise(){
		// | -45 --- 45 |

		if (angle > MIN_ANGLE_LIMIT + angleSpeed){
			setAngle(angle - angleSpeed);
		}else{
			setAngle(MIN_ANGLE_LIMIT);
		}


	}

	private void rotateClockwise(){
		// | -45 --- 45 |

		if (angle < MAX_ANGLE_LIMIT - angleSpeed){
			setAngle(angle + angleSpeed);
		}else{
			setAngle(MAX_ANGLE_LIMIT);
		}



	}

	private void goFastLeft(){
		setSpeed(-fastSpeed, 0);
		if ((getPosVector().x >= fastSpeed) && (getPosVector().x <= FRAME_WIDTH - getLength())) {
			updatePosition(getDx(), getDy());
		} else {
			updatePosition(0, getDy());
		}
	}

	private void goFastRight(){
		setSpeed(fastSpeed, 0);
		if ((getPosVector().x <= FRAME_WIDTH - getLength() - fastSpeed) && (getPosVector().x >= 0)) {
			updatePosition(getDx(), getDy());
		} else {
			updatePosition(0, getDy());
		}
	}



	// Needed for collision check calculations
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
}