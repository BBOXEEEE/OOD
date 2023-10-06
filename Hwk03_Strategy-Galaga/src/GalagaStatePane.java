import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * @File GalagaStatePane.java 
 * 화면 위 정보를 보여주는 Pane 클래스 
 */
public class GalagaStatePane extends HBox {
	private Label scoreLabel = new Label("점수");
	private TextField scoreText = new TextField();
	private Label livesLabel = new Label("생명");
	private TextField livesText = new TextField();
	private Button startGameBtn = new Button("새 게임");
	public GalagaStatePane() {
		setPadding(new Insets(10));
		setSpacing(10);
		setAlignment(Pos.CENTER);
		scoreText.setEditable(false);
		livesText.setEditable(false);
		startGameBtn.setDisable(true);
		getChildren().addAll(scoreLabel,scoreText,livesLabel,livesText,startGameBtn);
	}
	
	public void updateScore(int score) {
		scoreText.setText(""+score);
	}
	
	public void updateLives(int lives) {
		livesText.setText(""+lives);
	}
	
	public void setDisableGameStartBtn(boolean flag) {
		startGameBtn.setDisable(flag);
	}
	
	public void setNewGameHandler(EventHandler<ActionEvent> startGamehandler) {
		startGameBtn.setOnAction(startGamehandler);
	}
}
