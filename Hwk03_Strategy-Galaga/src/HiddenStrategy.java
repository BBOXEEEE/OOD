import java.util.Objects;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 2019136056 박세현
 * @File HiddenStrategy.java 
 * 적 외계인을 랜덤하게 움직이며 일정시간 간격으로 숨기는 전략
 */
public class HiddenStrategy implements AlienMovementStrategy {
	private Alien alien = null;
	private UnitTransition movementTransition;
	private boolean isMoving = false;
	private Timeline moveTimeline = new Timeline();
	private Random random = new Random();
	private Location firstLoc;
	private Location secondLoc;
	private boolean isFirstMovement = true;
	private boolean toSecondLoc = true;
	private double distance = 0.0;
	private boolean isHidden = false;
	private Timeline hideTimeline = new Timeline();

	public HiddenStrategy(Alien alien) {
		this.alien = Objects.requireNonNull(alien);

		moveTimeline.getKeyFrames().add(
				new KeyFrame(Duration.millis(500), e -> move()));
		moveTimeline.setCycleCount(Animation.INDEFINITE);

		hideTimeline.getKeyFrames().addAll(
				new KeyFrame(Duration.seconds(5), e -> hideAlien()),  // 5초 후 Alien 숨김
				new KeyFrame(Duration.seconds(7), e -> showAlien())  // Alien 숨긴 후 2초 뒤 나타냄
				);
		hideTimeline.setCycleCount(Animation.INDEFINITE);
	}

	private void doFirstMovement() {
		firstLoc = new Location(10, alien.getY());
		secondLoc = new Location(GalagaUtility.SPACEWIDTH - alien.getWidth() - 10, alien.getY());
		distance = GalagaUtility.getDistance(firstLoc.x(), firstLoc.y(), alien.getX(), alien.getY());

		Sound.play("alienFlying");
		movementTransition 
			= new UnitTransition(Duration.millis(distance * 5), firstLoc.x(), firstLoc.y(), 
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
		int destX = random.nextInt(GalagaUtility.SPACEWIDTH - alien.getWidth());
		int destY = random.nextInt(GalagaUtility.SPACEHEIGHT - alien.getHeight() - 100);

		distance = GalagaUtility.getDistance(alien.getX(), alien.getY(), destX, destY);

		movementTransition
		= new UnitTransition(Duration.millis(distance * 5), destX, destY,
				alien, alien.getOppositeMissiles());

		movementTransition.setAutoReverse(false);
		movementTransition.setCycleCount(1);
		movementTransition.setOnFinished(e -> {
			alien.toggleMap();
			Sound.stop("alienFlying");
			isMoving = false;
			toSecondLoc = !toSecondLoc;
		});
		movementTransition.play();
		

	}

	private void hideAlien() {
		isHidden = true;
		alien.setVisible(false);
	}

	private void showAlien() {
		isHidden = false;
		alien.setVisible(true);
	}

	@Override
	public void start() {
		moveTimeline.play();
		hideTimeline.play();
		isFirstMovement = true;
		toSecondLoc = true;
	}

	@Override
	public void stop() {
		moveTimeline.stop();
		hideTimeline.stop();
		movementTransition.stop();
		isMoving = false;
		if(isHidden) {
			isHidden = false;
			showAlien();
		}
	}


}
