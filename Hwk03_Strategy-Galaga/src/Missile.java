import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * @File GalagaView.java 
 * 적 외계인과 사용자 우주선의 미사일
 */
public class Missile extends Rectangle {
	private TranslateTransition missileTransition = null;
	
	public Missile(double x, double y, double width, double height, Color color) {
		super(x, y, width, height);
		setFill(color);
	}
	
	public void setMissileTransition(TranslateTransition missileTransition) {
		this.missileTransition = missileTransition;
	}
	
	public void stopTransition() {
		if(missileTransition != null) missileTransition.stop();
	}
}
