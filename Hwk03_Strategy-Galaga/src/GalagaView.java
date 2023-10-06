import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * @File GalagaView.java 
 * 화면 처리를 담당하는 뷰 클래스
 */
public class GalagaView extends BorderPane {
	private GalagaController controller;
	private final Pane battleField = new Pane();
	private GalagaStatePane statePane = new GalagaStatePane();
	private UserShip userShip = new UserShip();
	private Alien alien = new Alien();
	private boolean userShipDestroyed = false;
	private boolean alienDestroyed = false;
	private Set<Missile> alienMissiles = new TreeSet<>((a, b)->Integer.compare(a.hashCode(), b.hashCode()));
	private Set<Missile> userShipMissiles = new TreeSet<>((a, b)->Integer.compare(a.hashCode(), b.hashCode()));
	
	public GalagaView() {
		battleField.setPrefSize(GalagaUtility.SPACEWIDTH, GalagaUtility.SPACEHEIGHT);
		battleField.setBackground(new Background(
			new BackgroundFill(new ImagePattern(new Image("space.jpg")), CornerRadii.EMPTY, Insets.EMPTY)));
		
		userShip.setOppositeMissiles(alienMissiles);
		userShip.setOnExplode(m->explodeUserShip(m));
		alien.setOppositeMissiles(userShipMissiles);
		alien.setOnExplode(m->explodeAlien(m));
		alien.setOnAttack(()->alienAttack());
		
		setTop(statePane);
		setCenter(battleField);
	}
	
	public void setController(GalagaController controller) {
		this.controller = Objects.requireNonNull(controller);
		statePane.setNewGameHandler(e->controller.newGame());
	}
	
	public void init() {
		newGame();
	}
	
	private void initUserShip() {
		int x = (GalagaUtility.SPACEWIDTH - (userShip.getWidth())) / 2;
		int y = GalagaUtility.SPACEHEIGHT - (userShip.getHeight() + 8);
		userShip.init();
		userShip.moveTo(x, y);
		userShip.build();
		battleField.getChildren().add(userShip);
	}
	
	private void initAlien() {
		int x = ThreadLocalRandom.current().nextInt(
			GalagaUtility.SPACEWIDTH - alien.getWidth() - 10) + 10;
		int y = 10;
		alien.init();
		alien.moveTo(x, y);
		alien.build();
		battleField.getChildren().add(alien);
		alienAttack();
	}
	
	public void newGame() {
		requestFocus();
		statePane.setDisableGameStartBtn(true);
		battleField.getChildren().clear();
		userShipDestroyed = false;
		alienDestroyed = false;
		initAlien();
		initUserShip();
		alien.move();
	}
	
	// ==============================
	private void explodeAlien(Missile missile) {
		alienDestroyed = true;
		userShipMissiles.remove(missile);
		alienExplodeAnimation();
		Sound.play("alienDestroyed");
		endGame();
	}
	
	private void alienExplodeAnimation() {
		PauseTransition explodeSequence1 = new PauseTransition(Duration.millis(500));
		explodeSequence1.setOnFinished(e -> alien.build());
		PauseTransition explodeSequence2 = new PauseTransition(Duration.millis(500));
		explodeSequence2.setOnFinished(e -> alien.toggleMap());
		PauseTransition explodeSequence3 = new PauseTransition(Duration.millis(500));
		explodeSequence3.setOnFinished(e -> battleField.getChildren().remove(alien));
		SequentialTransition explodeAnimation = new SequentialTransition(
			explodeSequence1, explodeSequence2, explodeSequence3);
		explodeAnimation.setCycleCount(1);
		explodeAnimation.play();
	}
	
	// ==============================
	public void moveUserShipLeft() {
		if(userShipDestroyed) return;
		userShip.moveLeft();
	}
	
	public void moveUserShipRight() {
		if(userShipDestroyed) return;
		userShip.moveRight();
	}
	
	public void stopUserShip() {
		userShip.stopMovement();
	}
	
	public void userAttack() {
		if(alienDestroyed || userShipDestroyed) return;
		userShip.stopMovement();
		final int x = userShip.getX() + userShip.getWidth() / 2 - 1;
		final int y = userShip.getY() - GalagaUtility.PIXELSIZE * 2;
		final Missile missile = new Missile(x, y, GalagaUtility.PIXELSIZE, GalagaUtility.PIXELSIZE * 2, Color.WHITE);
		battleField.getChildren().add(missile);
		userShipMissiles.add(missile);
		Sound.play("userShipMissile");
		
		TranslateTransition missileTransition = new TranslateTransition(Duration.millis(1200), missile);
		missile.setMissileTransition(missileTransition);
		missileTransition.setByY(-y);
		missileTransition.setAutoReverse(false);
		missileTransition.setCycleCount(1);
		missileTransition.setOnFinished(e->{
			battleField.getChildren().remove(missile);
			userShipMissiles.remove(missile);
		});
		missileTransition.play();
	}
	
	// ==============================
	public void alienAttack() {
		if(alienDestroyed || userShipDestroyed) return;
		final int x = alien.getX() + alien.getWidth()/2;
		final int y = alien.getY() + alien.getHeight();
		final Missile missile = new Missile(x, y, GalagaUtility.PIXELSIZE, GalagaUtility.PIXELSIZE * 2, Color.DEEPPINK);
		battleField.getChildren().add(missile);
		
		int endY = GalagaUtility.SPACEHEIGHT-8;
		double speed = 1200.0 * Math.abs(y - endY) / GalagaUtility.SPACEHEIGHT;
		alienMissiles.add(missile);
		
		Sound.play("alienMissile");
		TranslateTransition missileTransition = new TranslateTransition(Duration.millis(speed), missile);
		missile.setMissileTransition(missileTransition);
		missileTransition.setByY(endY - y);
		missileTransition.setAutoReverse(false);
		missileTransition.setCycleCount(1);
		missileTransition.setOnFinished(e->{
			battleField.getChildren().remove(missile);
			alienMissiles.remove(missile);
		});
		missileTransition.play();
	}
	
	private void explodeUserShip(Missile missile) {
		userShipDestroyed = true;
		alienMissiles.remove(missile);
		Sound.play("userShipDestroyed");
		userShipExplodeAnimation();
		endGame();
	}
	
	private void userShipExplodeAnimation() {
		PauseTransition explodeSequence1 = new PauseTransition(Duration.millis(500));
		explodeSequence1.setOnFinished(e -> userShip.build());
		PauseTransition explodeSequence2 = new PauseTransition(Duration.millis(500));
		explodeSequence2.setOnFinished(e -> userShip.toggleMap());
		PauseTransition explodeSequence3 = new PauseTransition(Duration.millis(500));
		explodeSequence3.setOnFinished(e -> battleField.getChildren().remove(userShip));
		SequentialTransition explodeAnimation = new SequentialTransition(
			explodeSequence1, explodeSequence2, explodeSequence3);
		explodeAnimation.setCycleCount(1);
		explodeAnimation.play();
	}
	
	// ==============================
	public void endGame() {
		alien.stopMovement();
		statePane.setDisableGameStartBtn(false);
		controller.endGame();
	}
	
}
