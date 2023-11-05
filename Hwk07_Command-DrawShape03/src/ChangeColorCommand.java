import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 2019136056 박세현
 * Lab 06. 명령 패턴
 * 실습 3. Command Manager를 이용한 undo/redo 기능 구현
 * ChangeColorCommand.java: 도형 색 변경하기 명령 클래스
 */
public class ChangeColorCommand implements Command, Cloneable {
	private final Pane pane;
	private Shape shape;
	private Paint prevColor;
	
	public ChangeColorCommand(Pane pane, Shape shape) {
		this.pane = pane;
		this.shape = shape;
	}
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}

	@Override
	public void excute() {
		prevColor = shape.getFill();
		ColorPicker picker = new ColorPicker();
		picker.setLayoutX(shape.getBoundsInLocal().getCenterX());
		picker.setLayoutY(shape.getBoundsInLocal().getCenterY());
		pane.getChildren().add(picker);
		picker.setOnAction(e->{
			shape.setFill(picker.getValue());
			pane.getChildren().remove(picker);
		});
	}

	@Override
	public void undo() {
		Paint currentColor = shape.getFill();
		shape.setFill(prevColor);
		prevColor = currentColor;
	}

	@Override
	public void redo() {
		undo();
	}

	@Override
	protected ChangeColorCommand clone() {
		try {
			return (ChangeColorCommand) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
