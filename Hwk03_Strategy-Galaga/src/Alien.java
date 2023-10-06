import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * @File Alien.java 
 * 적 외계인 클래스
 */
public class Alien extends GalagaUnit {
	private static int[][] alienWingClosed = {
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},		
		{0,0,0,3,0,0,0,4,0,0,0,3,0,0,0},	
		{0,0,0,3,0,4,2,4,2,4,0,3,0,0,0},			
		{0,0,0,0,3,2,2,4,2,2,3,0,0,0,0},		
		{0,0,0,0,0,4,4,4,4,4,0,0,0,0,0},				
		{0,0,0,0,3,3,4,4,4,3,3,0,0,0,0},		
		{0,0,0,3,3,3,2,2,2,3,3,3,0,0,0},	
		{0,0,0,3,3,0,2,2,2,0,3,3,0,0,0},	
		{0,0,0,3,3,0,4,4,4,0,3,3,0,0,0},	
		{0,0,0,3,3,0,2,2,2,0,3,3,0,0,0},
		{0,0,0,3,3,0,0,2,0,0,3,3,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}	
	};
	
	private static int[][] alienWingOpen = {
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	
		{0,0,3,0,0,0,0,4,0,0,0,0,3,0,0},	
		{0,0,0,3,0,4,2,4,2,4,0,3,0,0,0},			
		{0,0,0,0,3,2,2,4,2,2,3,0,0,0,0},		
		{0,0,0,0,0,4,4,4,4,4,0,0,0,0,0},				
		{0,0,0,0,3,3,4,4,4,3,3,0,0,0,0},		
		{0,0,0,3,3,3,2,2,2,3,3,3,0,0,0},	
		{0,0,3,3,3,0,2,2,2,0,3,3,3,0,0},	
		{0,3,3,3,3,0,4,4,4,0,3,3,3,3,0},	
		{0,3,3,3,0,0,2,2,2,0,0,3,3,3,0},
		{0,3,3,3,0,0,0,2,0,0,0,3,3,3,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	};
	
	private static int[][] explode1 = {
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	
		{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},			
		{0,0,0,4,4,0,1,0,1,0,0,4,0,0,0},	
		{0,0,0,0,4,0,0,1,0,1,0,4,0,0,0},			
		{0,0,0,1,0,1,0,2,2,0,0,0,0,0,0},	
		{0,0,1,0,0,4,4,4,0,1,4,1,0,0,0},			
		{0,0,0,0,2,2,2,4,2,2,2,0,0,0,0},	
		{0,0,0,0,0,4,2,1,4,2,0,0,1,0,0},			
		{0,0,2,0,1,2,2,2,2,0,0,1,0,0,0},			
		{0,0,0,0,0,4,4,1,0,0,1,0,4,0,0},	
		{0,0,0,0,0,2,0,0,0,1,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	};
	
	private static int[][] explode2 = {
		{1,0,0,0,0,0,1,0,0,0,2,0,0,0,0},	
		{0,0,0,1,0,0,4,0,1,0,0,1,0,0,2},			
		{0,0,0,0,2,0,2,0,0,4,4,0,0,1,0},		
		{0,1,0,0,2,2,4,0,0,0,4,0,2,0,0},						
		{0,0,0,1,0,0,4,0,0,1,0,0,0,1,0},	
		{2,2,0,0,0,0,4,0,0,0,0,2,0,4,0},		
		{1,0,0,4,0,0,0,0,0,1,0,0,1,0,0},
		{0,0,0,4,2,2,0,1,0,0,0,0,0,0,0},
		{0,0,1,0,4,0,0,0,0,0,1,0,1,0,1},
		{0,1,0,0,0,1,0,0,0,4,0,2,2,0,4},
		{0,0,1,0,4,4,0,0,0,4,0,2,2,0,0},
		{2,0,2,0,2,0,4,0,0,0,1,0,0,0,1}
	};
	
	private AlienMovementStrategy moveStrategy
		//= new SidewaysStrategy(this);
		//= new RandomMovementStrategy(this);
		= new HiddenStrategy(this);
		//= new TeleportStrategy(this);
	// 주기적으로 미사일 발사하기 위한 Timer
	private Timeline attackTimeline = new Timeline();
	private Action attackAction;
	
	public Alien() {
		super(alienWingClosed);
		setAuxMap(alienWingOpen);
		attackTimeline.getKeyFrames().add(
			new KeyFrame(Duration.millis(1000), e -> attack()));
		attackTimeline.setCycleCount(Animation.INDEFINITE);
	}
	
	public void init() {
		setImageMap(alienWingClosed);
		setAuxMap(alienWingOpen);
	}
	
	public void setOnAttack(Action attackAction) {
		this.attackAction = attackAction;
	}
	
	@Override public void explode(Missile missile) {
		stopMovement();
		setImageMap(explode1);
		setAuxMap(explode2);
		explodeAction.accept(missile);
	}
	
	public void move() {
		attackTimeline.play();
		moveStrategy.start();
	}
	
	@Override public void stopMovement() {
		moveStrategy.stop();
		attackTimeline.stop();
	}
	
	public void attack() { 
		Sound.play("alienMissile");
		attackAction.doAction();
	}
}
