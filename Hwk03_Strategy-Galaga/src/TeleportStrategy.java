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
 * @File TeleportStrategy.java 
 * SidewaysStrategy 기반, 일정 시간 간격으로 순간이동하는 움직임 전략
 */
public class TeleportStrategy implements AlienMovementStrategy {
    private Alien alien = null;
    private UnitTransition movementTransition;
    private boolean isMoving = false;
    private boolean isTeleporting = false;
    private Timeline moveTimeline = new Timeline();
    private Timeline teleportTimeline = new Timeline();
    private Location firstLoc;
    private Location secondLoc;
    private boolean isFirstMovement = true;
    private boolean toSecondLoc = true;
    private double distance = 0.0;
    private Random random = new Random();

    public TeleportStrategy(Alien alien) {
        this.alien = Objects.requireNonNull(alien);

        moveTimeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(500), e -> move()));
        moveTimeline.setCycleCount(Animation.INDEFINITE);
        
        teleportTimeline.getKeyFrames().add(
        		new KeyFrame(Duration.seconds(5), e -> teleport()));
        teleportTimeline.setCycleCount(Animation.INDEFINITE);
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
        if (isMoving) return;
        isMoving = true;
        
        if (isFirstMovement) {
            isFirstMovement = false;
            doFirstMovement();
            return;
        }
        
        if(!isTeleporting) {
        	Sound.play("alienFlying");
        	
        	firstLoc = new Location(10, alien.getY());
    		secondLoc = new Location(GalagaUtility.SPACEWIDTH - alien.getWidth(), alien.getY());
    		
    		int destX = toSecondLoc? secondLoc.x(): firstLoc.x();
    		int destY = toSecondLoc? secondLoc.y(): firstLoc.y();
    		
    		distance = GalagaUtility.getDistance(alien.getX(), alien.getY(), destX, destY);
    		
    		movementTransition 
    			= new UnitTransition(Duration.millis(distance * 4), destX, destY, 
    				alien, alien.getOppositeMissiles());
        } else {
        	System.out.println("Teleporting!");
        	
        	int destX = random.nextInt(GalagaUtility.SPACEWIDTH - alien.getWidth());
        	int destY = random.nextInt(GalagaUtility.SPACEHEIGHT - alien.getHeight() - 100);
        	
        	distance = GalagaUtility.getDistance(alien.getX(), alien.getY(), destX, destY);
        	
        	movementTransition
        		= new UnitTransition(Duration.millis(distance), destX, destY,
        				alien, alien.getOppositeMissiles());
        	
        	isTeleporting = false;
        }
        
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
    
    private void teleport() {
    	isTeleporting = true;
    }
    
    @Override
    public void start() {
        moveTimeline.play();
        teleportTimeline.play();
        isFirstMovement = true;
        toSecondLoc = true;
    }

    @Override
    public void stop() {
    	Sound.stop("alienFlying");
		moveTimeline.stop();
		teleportTimeline.stop();
		movementTransition.stop();
		isMoving = false;
		isTeleporting = false;
    }
}
