import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진
 * @file KoreaTechSNS.java
 * SNS 프로그램에서 뷰 + 컨트롤러 역할
 */
public class KoreaTechSNS extends Application {
	private List<SNSWindow> chatWindows = new ArrayList<>();
	private Button startButton = new Button("코리아텍 SNS 시작");
	
	// SNS 서비스 시뮬레이션
	public void prepareSimulation() {
		SNSServer server = SNSServer.getServer();
		int userID1 = server.addUser("홍길동", "kildong@koreatech.ac.kr");
		int userID2 = server.addUser("임꺽정", "lim@koreatech.ac.kr");
		int userID4 = server.addUser("장길산", "gilsan@koreatech.ac.kr");
		Optional<User> hong = server.getUser(userID1);
		Optional<User> lim = server.getUser(userID2);
		Optional<User> jang = server.getUser(userID4);
		hong.get().addFriend(userID2);
		lim.get().addFriend(userID1);
		jang.get().addFriend(userID1);
		hong.get().addFriend(userID4);
		jang.get().addFriend(userID2);
		lim.get().addFriend(userID4);
		for(User user: server.getUsers()) {
			SNSWindow snsWindow = new SNSWindow(user);
			user.setView(snsWindow);
			chatWindows.add(snsWindow);
		}
		int userID3 = server.addUser("성춘향", "seong@koreatech.ac.kr");
		Optional<User> seong = server.getUser(userID3);
		hong.get().addFriend(userID3);
		lim.get().addFriend(userID3);
		seong.get().addFriend(userID1);
		seong.get().addFriend(userID2);
		server.newFeed(new SNSFeed(userID1, hong.get().getName(),
			"리버풀 3: 아스톤빌라 0\n#YNWA", "liverpool.png", LocalDateTime.of(2023, 9, 3, 23, 45)));
		server.newFeed(new SNSFeed(userID1, hong.get().getName(),
			"피카 피카 피카츄", "pikachu.jpg", LocalDateTime.of(2023, 9, 27, 19, 15)));
	}
	
	public void startTalkSimulation() {
		startButton.setDisable(true);
		
		double x = 400;
		double y = 100;
		for(var chatWindow: chatWindows) {
			chatWindow.setX(x);
			chatWindow.setY(y);
			chatWindow.show();
			x += 350;
			if(x == 1450) {
				y += 350;
				x = 50;
			}
		}
	}
	
	public void stopTalkSimulation() {
		for(SNSWindow chatWindow: chatWindows)
			chatWindow.close();
		startButton.setDisable(false);
	}
	
	@Override public void start(Stage primaryStage) throws Exception {	
		primaryStage.setTitle("KoreaTech SNS");
		primaryStage.setScene(new Scene(constructButtonPane(),300,300));
		primaryStage.setX(50);
		primaryStage.setY(100);
		primaryStage.show();
		primaryStage.getScene().getWindow()
			.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e->stopTalkSimulation());
		prepareSimulation();
	}

	private VBox constructButtonPane() {
		Button closeAllButton = new Button("창 모두 닫기");
		startButton.setMinWidth(160);
		closeAllButton.setMinWidth(160);
		
		VBox buttonPane = new VBox();
		buttonPane.setPadding(new Insets(10));
		buttonPane.setSpacing(10);
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.getChildren().addAll(startButton, closeAllButton);
		
		startButton.setOnAction(e->startTalkSimulation());
		closeAllButton.setOnAction(e->stopTalkSimulation());
		return buttonPane;
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
