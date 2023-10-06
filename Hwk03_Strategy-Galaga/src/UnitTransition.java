import java.util.Set;

import javafx.animation.Transition;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * @File UnitTransition.java 
 * Transition을 상속받아 이동 과정에서 물체 충돌 탐지
 * 적 외계인과 사용자 우주선을 이동할 때 모두 사용함
 */
public class UnitTransition extends Transition {
	private GalagaUnit unit;
	private int startX; 
	private int startY;
	private int byX;
	private int byY;
	private Set<Missile> missiles;
	
	public UnitTransition(Duration millis, int endX, int endY, GalagaUnit unit, Set<Missile> missiles) {
		super();
		this.unit = unit;
		startX = (int)unit.getX();
		startY = (int)unit.getY();
		byX = endX - startX;
		byY = endY - startY;
		this.missiles = missiles;
		setCycleDuration(millis);
	}
	
	public boolean hitTest(Rectangle missile) {
		Bounds unitRect = unit.getBoundsInLocal();
		Bounds missileRect = missile.getBoundsInLocal();
		BoundingBox missileBox = new BoundingBox(missileRect.getMinX(),
				missileRect.getMinY() + missile.getTranslateY(), missile.getWidth(), missile.getHeight());
		return unitRect.intersects(missileBox);
	}
	
	@Override
	protected void interpolate(double frac) {
		int newX = startX + (int)(byX * frac);
		int newY = startY + (int)(byY * frac);
		unit.moveTo(newX, newY);
		for(var missile: missiles) {
			if(hitTest(missile)) {
				stop();
				unit.explode(missile);
				missile.stopTransition();
				break;
			}
		}
	}
}
