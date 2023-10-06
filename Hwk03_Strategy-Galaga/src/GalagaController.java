import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * @File GalagaController.java 
 * 키보드 처리 담당
 */
public class GalagaController {
	private GalagaView view;
	private boolean gameFinished = false;
	private boolean shootEnabled = true;
	
	public GalagaController(GalagaView view) {
		this.view = Objects.requireNonNull(view);
		view.setOnKeyPressed(this::handleKeyboard);
	}
	
	public void init() {
		GalagaUtility.galagaInfoDialog("Galaga 게임시작", "게임을 시작하시겠습니까???");
		view.init();
		gameFinished = false;
	}
	
	public void newGame() {
		view.newGame();
		gameFinished = false;
	}
	
	public void endGame() {
		gameFinished = true;
	}
	
	private void handleKeyboard(KeyEvent event) {
		if(gameFinished) return;
		switch(event.getCode()){
		case LEFT: view.moveUserShipLeft(); break;
		case RIGHT: view.moveUserShipRight(); break;
		case UP, DOWN: view.stopUserShip(); break;
		case SPACE: 
			if(shootEnabled) {
				shootEnabled = false;
				view.userAttack(); 
				// 발사 속도 조절 
				PauseTransition pause = new PauseTransition(Duration.millis(500));
				pause.setOnFinished(e -> shootEnabled = true);
				pause.play();
			}
			break;
		default:
		}
		event.consume(); 
	}
	
}
