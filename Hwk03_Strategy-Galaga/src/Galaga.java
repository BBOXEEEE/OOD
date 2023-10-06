import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * @File Galaga.java 
 * 응용 클래스
 */
public class Galaga extends Application {
	private GalagaView view = new GalagaView();
	private GalagaController controller = new GalagaController(view);
	{   // 픽셀 아트에 사용하는 색
		GalagaUtility.colorMap.put(0, null);
		GalagaUtility.colorMap.put(1, Color.WHITE);
		GalagaUtility.colorMap.put(2, Color.RED);
		GalagaUtility.colorMap.put(3, Color.BLUE);
		GalagaUtility.colorMap.put(4, Color.YELLOW);
		GalagaUtility.colorMap.put(5, Color.CYAN);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		view.setController(controller);
		controller.init();
		primaryStage.setTitle("KoreaTech CSE Galaga");
		primaryStage.setScene(new Scene(view));
		primaryStage.setResizable(false);
		primaryStage.show();
		view.requestFocus();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
