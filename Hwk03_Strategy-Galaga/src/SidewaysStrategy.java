import java.util.Objects;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * @File SidewaysStrategy.java 
 * 적 외계인을 움직이는 전략
 */
public class SidewaysStrategy implements AlienMovementStrategy {
	private Alien alien = null;
	private UnitTransition movementTransition;
	private boolean isMoving = false;
	private Timeline moveTimeline = new Timeline();
	private Location firstLoc;
	private Location secondLoc;
	private boolean isFirstMovement = true;
	private boolean toSecondLoc = true;
	private double distance = 0.0;
	
	public SidewaysStrategy(Alien alien) {
		this.alien = Objects.requireNonNull(alien);
		
		moveTimeline.getKeyFrames().add(
				new KeyFrame(Duration.millis(500), e -> move()));
		moveTimeline.setCycleCount(Animation.INDEFINITE);
	}
	
	private void doFirstMovement() {
		firstLoc = new Location(10, alien.getY());
		secondLoc = new Location(GalagaUtility.SPACEWIDTH - alien.getWidth() - 10, alien.getY());
		distance = GalagaUtility.getDistance(firstLoc.x(), firstLoc.y(), alien.getX(), alien.getY());
		
		Sound.play("alienFlying");
		movementTransition 
			= new UnitTransition(Duration.millis(distance * 4), firstLoc.x(), firstLoc.y(), 
				alien, alien.getOppositeMissiles());
	
		movementTransition.setAutoReverse(false);
		movementTransition.setCycleCount(1);
		movementTransition.setOnFinished(e->{
			alien.toggleMap();
			Sound.stop("alienFlying");
			isMoving = false;
			distance = GalagaUtility.getDistance(firstLoc.x(), firstLoc.y(), secondLoc.x(), secondLoc.y());
		});
		movementTransition.play();
	}
	
	public void move() {
		if(isMoving) return;
		isMoving = true;
		
		if(isFirstMovement) {
			isFirstMovement = false;
			doFirstMovement();
			return;
		}
		
		Sound.play("alienFlying");
		int destX = toSecondLoc? secondLoc.x(): firstLoc.x();
		int destY = toSecondLoc? secondLoc.y(): firstLoc.y();
		
		movementTransition 
			= new UnitTransition(Duration.millis(distance * 4), destX, destY, 
				alien, alien.getOppositeMissiles());
		
		movementTransition.setAutoReverse(false);
		movementTransition.setCycleCount(1);
		movementTransition.setOnFinished(e->{
			alien.toggleMap();
			Sound.stop("alienFlying");
			isMoving = false;
			toSecondLoc = !toSecondLoc;
		});
		movementTransition.play();
	}
	
	@Override
	public void start() {
		moveTimeline.play();
		isFirstMovement = true;
		toSecondLoc = true;
	}

	@Override
	public void stop() {
		Sound.stop("alienFlying");
		moveTimeline.stop();
		movementTransition.stop();
		isMoving = false;
	}

}
