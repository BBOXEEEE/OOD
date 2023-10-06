import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file MultipleActionTest.java 
 * JavaFX. addEventHandler를 이용한 한 노드에 여러 개 처리자 등록 예제
 * GUI에서 사건 소스에 등록된 처리자는 사전이 발생하면 자동 통보해 줌
 * 이 부분은 관찰자 패턴과 유사함. 
 * addEventHandler -> registerObserver
 * 사건 발생 -> notifyObservers 
 * handle -> Observer의 update 
 * GUI에서 사건이 발생하여 사건 처리자가 호출되면 보통 사건 객체를 전달함
 * 이 측면에서는 push 형태?, 사건 객체는 사건 소스 객체를 포함하고 있으므로 pull 형태?
 */
public class MultipleActionTest extends Application {
	private TextArea textOutArea = new TextArea();
	private ScrollPane scrollpane = new ScrollPane(textOutArea);
	private String textData = "";
	
	class A implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			textData += "Apple\n";
			textOutArea.setText(textData);		
		}
	}
	
	class B implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			textData += "Banana\n";
			textOutArea.setText(textData);			
		}
	}
	
	class C implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			textData += "Cherry\n";
			textOutArea.setText(textData);			
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane mainPane = new BorderPane();
		mainPane.setPadding(new Insets(10d));
		
		HBox buttonPane = new HBox();
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setPadding(new Insets(10d));
		buttonPane.setSpacing(10d);
		Button normalButton = new Button("단일 사건처리자");
		Button multiActionButton = new Button("다중 사건처리자");
		Button clearButton = new Button("전체지우기");
		buttonPane.getChildren().addAll(normalButton, multiActionButton, clearButton);
		
		normalButton.setOnAction(new A());
		
		multiActionButton.addEventHandler(ActionEvent.ACTION, new B());
		multiActionButton.addEventHandler(ActionEvent.ACTION, new C());
		multiActionButton.addEventHandler(ActionEvent.ACTION, e->{
			textData += "Kiwi\n";
			textOutArea.setText(textData);	
		});
		
		clearButton.setOnAction(e->{
			textData = "";
			textOutArea.setText("");
		});
		
		mainPane.setCenter(scrollpane);
		mainPane.setBottom(buttonPane);
		
		primaryStage.setTitle("관찰자 패턴 테스트");
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
