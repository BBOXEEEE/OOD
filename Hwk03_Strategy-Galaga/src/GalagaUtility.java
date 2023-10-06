import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

@FunctionalInterface
interface Action{
	void doAction();
}

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * @File GalagaUtility.java 
 * 공통적으로 사용하는 상수와 일반 함수 
 */
public interface GalagaUtility {
	public static final int PIXELSIZE = 3;
	public static final int SPACEHEIGHT = 600;
	public static final int SPACEWIDTH = 672;
	public static final Map<Integer, Color> colorMap = new HashMap<>();
	public static double getDistance(double sourceX, double sourceY, double destX, double destY) {
		double diffY = Math.abs(sourceY - destY);
		double diffX = Math.abs(sourceX - destX);
		return Math.sqrt(diffX*diffX + diffY*diffY);
	}
	
	public static void galagaInfoDialog(String title, String content){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		ImageView icon = new ImageView(new Image("galaga.png"));
		icon.setFitHeight(80);
		icon.setPreserveRatio(true);
		alert.setGraphic(icon);
		alert.showAndWait();
	}
	
	public static boolean galagaConfirmDialog(String title, String content,
			String okButton, String cancelButton){
		Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(null);
    	alert.setContentText(content);
    	ButtonType buttonTypeOK = new ButtonType(okButton, ButtonData.OK_DONE);
    	ButtonType buttonTypeCancel = new ButtonType(cancelButton, ButtonData.CANCEL_CLOSE);
    	alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
    	ImageView icon = new ImageView(new Image("galaga.png"));
		icon.setFitHeight(80);
		icon.setPreserveRatio(true);
		alert.setGraphic(icon);
    	Optional<ButtonType> result = alert.showAndWait();
    	return (result.isPresent() && result.get() == buttonTypeOK);
	}
}
