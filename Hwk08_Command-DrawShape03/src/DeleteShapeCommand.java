import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 2019136056 박세현
 * Lab 06. 명령 패턴
 * 실습 3. Command Manager를 이용한 undo/redo 기능 구현
 * DeleteShapeCommand.java: 도형 삭제하기 명령 클래스
 */
public class DeleteShapeCommand implements Command, Cloneable {
	private final Pane pane;
	private Shape shape;
	
	public DeleteShapeCommand(Pane pane, Shape shape) {
		this.pane = pane;
		this.shape = shape;
	}
	
	@Override
	public void excute() {
		pane.getChildren().remove(shape);
	}

	@Override
	public void undo() {
		pane.getChildren().add(shape);
	}

	@Override
	public void redo() {
		pane.getChildren().remove(shape);
	}

	@Override
	protected DeleteShapeCommand clone() {
		try {
			return (DeleteShapeCommand) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
