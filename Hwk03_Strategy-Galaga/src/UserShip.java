import javafx.util.Duration;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * @File UserShip.java 
 * 사용자 우주선
 */
public class UserShip extends GalagaUnit{
	private static final int[][] UserShipMap = {
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	
		{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},	
		{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},	
		{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},	
		{0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0},	
		{0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0},	
		{0,0,0,0,0,2,0,0,1,1,1,0,0,2,0,0,0,0,0},	
		{0,0,0,0,0,2,0,0,1,1,1,0,0,2,0,0,0,0,0},	
		{0,0,0,0,0,1,0,1,1,1,1,1,0,1,0,0,0,0,0},	
		{0,0,2,0,0,1,3,1,1,2,1,1,3,1,0,0,2,0,0},
		{0,0,2,0,0,3,1,1,2,2,2,1,1,3,0,0,2,0,0},	
		{0,0,1,0,0,1,1,1,2,1,2,1,1,1,0,0,1,0,0},	
		{0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,0,0},	
		{0,0,1,1,1,1,1,2,1,1,1,2,1,1,1,1,1,0,0},
		{0,0,1,1,1,0,2,2,1,1,1,2,2,0,1,1,1,0,0},
		{0,0,1,1,0,0,2,2,0,1,0,2,2,0,0,1,1,0,0},	
		{0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	};
	
	private static final int[][] explode1 = {
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	
		{0,0,5,5,0,0,0,0,0,0,0,0,0,0,0,5,5,0,0},	
		{0,0,0,0,5,0,2,0,0,0,0,0,2,0,5,5,0,0,0},	
		{0,0,0,0,0,5,0,0,0,2,0,0,0,5,5,0,0,0,0},	
		{0,0,0,0,2,0,0,5,5,5,5,5,0,0,0,0,0,0,0},	
		{0,0,5,5,0,0,5,1,1,1,1,1,5,5,0,5,5,0,0},	
		{0,0,0,0,0,5,1,0,0,2,1,0,0,5,0,5,0,0,0},	
		{0,0,0,0,5,1,1,2,5,0,0,1,5,0,0,0,5,0,0},	
		{0,0,0,0,0,1,1,0,0,0,2,0,5,0,5,0,0,0,0},
		{0,0,5,0,5,5,1,0,1,1,0,0,1,5,0,0,0,0,0},	
		{0,0,0,5,0,5,1,1,2,1,1,1,1,5,0,2,0,0,0},	
		{0,0,0,0,0,5,5,1,1,5,5,5,5,0,0,0,0,0,0},	
		{0,0,0,0,2,0,0,5,5,0,0,0,0,5,1,0,0,0,0},
		{0,0,0,2,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0},
		{0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,5,5,0,0},	
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	};
	
	private static final int[][] explode2 = {
		{5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5},	
		{0,5,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,5,0},	
		{0,5,5,5,0,0,5,5,5,0,5,5,5,0,0,5,5,0,0},	
		{0,0,0,0,5,0,5,1,1,5,5,1,5,0,5,5,0,2,0},	
		{0,0,0,5,5,5,1,1,1,1,1,1,5,5,5,0,2,0,0},	
		{0,5,5,1,1,1,1,0,5,2,0,1,1,1,1,5,0,0,0},	
		{0,5,1,1,5,0,0,0,5,0,5,0,0,5,1,1,5,0,0},	
		{0,5,1,1,2,0,5,0,0,0,0,1,0,5,0,1,5,5,0},	
		{0,0,5,1,2,0,0,1,1,0,1,0,0,2,1,1,5,5,0},	
		{0,0,5,1,0,5,1,1,0,0,1,0,5,1,1,5,5,0,0},
		{0,0,0,5,1,0,0,1,1,0,0,1,0,1,5,0,0,0,2},	
		{0,0,5,1,5,0,0,2,1,1,1,0,5,1,1,5,0,0,0},	
		{0,0,5,1,0,0,5,0,0,0,5,2,0,1,1,5,0,5,5},	
		{0,0,5,1,1,1,0,0,5,0,0,1,1,1,5,5,0,0,0},
		{0,0,0,5,5,1,1,1,1,0,0,1,5,5,0,0,0,0,0},
		{0,0,2,2,0,5,5,5,1,1,1,5,5,0,2,0,2,0,0},	
		{0,5,5,0,0,0,0,0,5,5,5,5,0,0,0,0,0,5,0},
		{5,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5}
	};
	
	private boolean isMovingLeft = false;
	private boolean isMovingRight = false;
	private UnitTransition leftMoveTransition = null;
	private UnitTransition rightMoveTransition = null;
	
	public UserShip() {
		super(UserShipMap);
	}
	
	public void init() {
		setImageMap(UserShipMap);
		setAuxMap(null);
	}
	
	@Override public void explode(Missile missile) {
		setImageMap(explode1);
		setAuxMap(explode2);
		explodeAction.accept(missile);
	}
	
	public void moveLeft() {
		if(isMovingLeft) return;
		if(isMovingRight) stopRightMovement();
		int destX = 0;
		if(getX() != destX) {
			double speed = 3 * GalagaUtility.getDistance(getX(), getY(), destX, getY());	
			isMovingLeft = true;
			
			leftMoveTransition = new UnitTransition(Duration.millis(speed), destX, getY(), this, oppositeMissiles);
			leftMoveTransition.setAutoReverse(false);
			leftMoveTransition.setCycleCount(1);
			leftMoveTransition.play();
			leftMoveTransition.setOnFinished(e->{
				isMovingLeft = false;
			});
			// leftMoveTransition.play(); 	강의시간 수정
		}
	}
	
	private void stopLeftMovement() {
		leftMoveTransition.stop();
		isMovingLeft = false;
	}
	
	public void moveRight() {
		if(isMovingRight) return;
		if(isMovingLeft) stopLeftMovement();
		int destX = GalagaUtility.SPACEWIDTH - getWidth();
		if(getX() != destX) {
			double speed = 3 * GalagaUtility.getDistance(getX(), getY(), destX, getY());
			isMovingRight = true;
			
			rightMoveTransition = new UnitTransition(Duration.millis(speed), destX, getY(), this, oppositeMissiles);
			rightMoveTransition.setAutoReverse(false);
			rightMoveTransition.setCycleCount(1);
			rightMoveTransition.play();
			rightMoveTransition.setOnFinished(e->{
				isMovingRight = false;
			});
			rightMoveTransition.play();
		}
	}
	
	private void stopRightMovement() {
		rightMoveTransition.stop();
		isMovingRight = false;
	}
	
	@Override public void stopMovement() {
		if(leftMoveTransition != null) leftMoveTransition.stop();
		if(rightMoveTransition != null) rightMoveTransition.stop();
		isMovingLeft = isMovingRight = false;
	}
}
