import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 2019136056 박세현
 * Lab 06. 명령 패턴
 * 실습 3. Command Manager를 이용한 undo/redo 기능 구현
 * DrawCircleCommand.java: 원 그리기 명령 클래스
 */
public class DrawCircleCommand implements Command, Cloneable {
	private static final int RADIUS = 40;
	private final Pane pane;
	private Shape shape;
	private double x;
	private double y;

	public DrawCircleCommand(Pane pane, double x, double y) {
		this.pane = pane;
		this.x = x;
		this.y = y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public void excute() {
		shape = new Circle(x, y, RADIUS);
		shape.setStroke(Color.BLACK);
		shape.setFill(null);
		shape.setStrokeWidth(5d);
		pane.getChildren().add(shape);
	}

	@Override
	public void undo() {
		pane.getChildren().remove(shape);
	}
	
	@Override
	public void redo() {
		pane.getChildren().add(shape);
	}
	
	@Override
	protected DrawCircleCommand clone() {
		try {
			return (DrawCircleCommand) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}

}
