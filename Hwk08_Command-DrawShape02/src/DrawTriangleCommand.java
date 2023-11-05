import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 2019136056 박세현
 * Lab 06. 명령 패턴
 * 실습 2. 명령 패턴을 이용한 undo/redo 기능 구현
 * DrawTriangleCommand.java: 삼각형 그리기 명령 클래스
 */
public class DrawTriangleCommand implements Command, Cloneable {
	private static final int RADIUS = 40;
	private final Pane pane;
	private Shape shape;
	private double x;
	private double y;
	
	public DrawTriangleCommand(Pane pane, double x, double y) {
		this.pane = pane;
		this.x = x;
		this.y = y;
	}

	@Override
	public void excute() {
		Polygon triangle = new Polygon();
		final double radian = Math.PI / 180F;
		Double[] points = new Double[6]; 
		int i = 0;
		for(var angle: new double[]{30, 150, 270}) {
			points[i] = x + RADIUS*Math.cos(angle * radian);
			points[i + 1] = y + RADIUS*Math.sin(angle * radian);
			i += 2;
		}
		triangle.getPoints().addAll(points);
		shape = triangle;
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
	protected DrawTriangleCommand clone() {
		try {
			return (DrawTriangleCommand) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
