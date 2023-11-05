import java.util.Stack;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 2019136056 박세현
 * Lab 06. 명령 패턴
 * 실습 1. 명령 패턴 없이 undo/redo 기능 구현
 * DrawShapeApp.java: 도형 그리기, 색 채우기, 도형 삭제 기능
 */
public class DrawShapeApp extends Application {
	private static final int HEIGHT = 500;
	private static final int WIDTH = 500;
	private static final int RADIUS = 40;
	
	private RadioButton drawButton = new RadioButton("추가");
	private RadioButton selectButton = new RadioButton("선택");
	private Button undoButton = new Button("취소");
	private Button redoButton = new Button("재실행");
	
	private ComboBox<String> shapeChoice = new ComboBox<>();
	private ShapeType currentShape = ShapeType.SQUARE;
	private Shape selectedShape = null;
	
	private ContextMenu popupMenu = new ContextMenu();
	
	private Pane centerPane = new Pane();
	
	// undo : 이전에 실행한 도형, 이전에 실행한 작업, 이전에 채운 색상
	private Stack<Shape> undoShape = new Stack<>();
	private Stack<DrawAction> undoDrawAction = new Stack<>();
	private Stack<Paint> undoColor = new Stack<>();
	
	// redo : 취소한 도형, 취소한 작업, 취소한 색상
	private Stack<Shape> redoShape = new Stack<>();
	private Stack<DrawAction> redoDrawAction = new Stack<>();
	private Stack<Paint> redoColor = new Stack<>();
	
	private void drawShape(double x, double y) {
		switch(currentShape) {
		case SQUARE: drawSquare(x, y); break;
		case CIRCLE: drawCircle(x, y); break;
		case TRIANGLE: drawTriangle(x, y);
		}
	}
	
	private void addItem(Shape shape) {
		shape.setStroke(Color.BLACK);
		shape.setFill(null);
		shape.setStrokeWidth(5d);
		centerPane.getChildren().add(shape);
		
		// undo
		undoShape.add(shape);
		undoDrawAction.add(DrawAction.DRAW);
		// redo clear
		redoShape.clear();
		redoDrawAction.clear();
		// undoButton Activate
		undoButton.setDisable(false);
	}
	
	private void drawSquare(double x, double y) {
		addItem(new Rectangle(x - RADIUS, y - RADIUS, RADIUS * 2, RADIUS * 2));
	}
	
	private void drawCircle(double x, double y) {
		addItem(new Circle(x, y, RADIUS));
	}
	
	private void drawTriangle(double x, double y) {
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
		addItem(triangle);
	}
	
	private void changeColor() {
		ColorPicker picker = new ColorPicker();
		picker.setLayoutX(selectedShape.getBoundsInLocal().getCenterX());
		picker.setLayoutY(selectedShape.getBoundsInLocal().getCenterY());
		centerPane.getChildren().add(picker);
		picker.setOnAction(e->{
			selectedShape.setFill(picker.getValue());
			centerPane.getChildren().remove(picker);
		});
		
		// undo
		undoShape.add(selectedShape);
		undoDrawAction.add(DrawAction.CHANGE_COLOR);
		undoColor.add(selectedShape.getFill());
		// redo clear
		redoShape.clear();
		redoDrawAction.clear();
		redoColor.clear();
	}
	
	private void deleteShape() {
		centerPane.getChildren().remove(selectedShape);
		
		// undo
		undoShape.add(selectedShape);
		undoDrawAction.add(DrawAction.DELETE_SHAPE);
		undoColor.add(selectedShape.getFill());
		// redo clear
		redoShape.clear();
		redoDrawAction.clear();
		redoColor.clear();
	}
	
	private void selectShape(double x, double y, double screenX, double screenY) {
		for(int i = centerPane.getChildren().size() - 1; i >= 0; i--) {
			Shape shape = (Shape) centerPane.getChildren().get(i);
			if(shape.getBoundsInLocal().contains(x, y)) {
				selectedShape = shape;
				popupMenu.show(centerPane, screenX, screenY);
				break;
			}
		}
	}
	
	private void undo() {
		Shape shape = undoShape.pop();
		DrawAction action = undoDrawAction.pop();
		redoShape.add(shape);
		redoDrawAction.add(action);
		
		switch(action) {
		case DRAW: centerPane.getChildren().remove(shape); break;
		case CHANGE_COLOR:
			redoColor.add(shape.getFill());
			Paint prevColor = undoColor.pop();
			shape.setFill(prevColor);
			break;
		case DELETE_SHAPE: centerPane.getChildren().add(shape); break;
		}
		
		if(undoShape.isEmpty()) {
			undoButton.setDisable(true);
			return;
		}
		else
			redoButton.setDisable(false);
	}
	
	private void redo() {
		Shape shape = redoShape.pop();
		DrawAction action = redoDrawAction.pop();
		undoShape.add(shape);
		undoDrawAction.add(action);
		
		switch(action) {
		case DRAW: centerPane.getChildren().add(shape); break;
		case CHANGE_COLOR:
			undoColor.add(shape.getFill());
			Paint prevColor = redoColor.pop();
			shape.setFill(prevColor);
			break;
		case DELETE_SHAPE: centerPane.getChildren().remove(shape); break;
		}
		
		if(redoShape.isEmpty()) {
			redoButton.setDisable(true);
			return;
		}
		else
			undoButton.setDisable(false);
	}
	
	private void mouseHandle(MouseEvent mouseEvent) {
		double x = mouseEvent.getX();
		double y = mouseEvent.getY();
		if(x < RADIUS) x = RADIUS;
		else if(x + RADIUS > WIDTH) x = WIDTH - RADIUS;
		if(y < RADIUS) y = RADIUS;
		else if(y + RADIUS > HEIGHT) y = HEIGHT - RADIUS;
		if(drawButton.isSelected()) drawShape(x, y);
		else selectShape(x, y, mouseEvent.getScreenX(), mouseEvent.getScreenY());
	}
	
	private HBox constructControlPane() {
		String[] shapeList = {"Square", "Circle", "Triangle"};
		shapeChoice.getItems().addAll(shapeList);
		shapeChoice.getSelectionModel().selectFirst();
		shapeChoice.setOnAction(e->
			currentShape = ShapeType.valueOf(shapeChoice.getSelectionModel().getSelectedItem().toUpperCase())
		);
	
		ToggleGroup actionGroup = new ToggleGroup();
		actionGroup.getToggles().addAll(drawButton, selectButton);
		drawButton.setSelected(true);
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
		undoButton.setDisable(true);
		redoButton.setDisable(true);
		undoButton.setOnAction(e->undo());
		redoButton.setOnAction(e->redo());
		
		HBox controlPane = new HBox();
		controlPane.setPadding(new Insets(10));
		controlPane.setSpacing(10);
		controlPane.getChildren().addAll(shapeChoice, drawButton, selectButton, spacer, undoButton, redoButton);
		return controlPane;
	}
	
	private void constructPopupMenu() {
		MenuItem fillColorItem = new MenuItem("채우기 색 변경");
		fillColorItem.setOnAction(e->changeColor());
		MenuItem deleteItem = new MenuItem("삭제");
		deleteItem.setOnAction(e->deleteShape());
		popupMenu.getItems().addAll(fillColorItem, deleteItem);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane mainPane = new BorderPane();
		
		centerPane.setBackground(
			new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		centerPane.setMinWidth(500d);
		centerPane.setMinHeight(500d);
		centerPane.setOnMouseClicked(e->mouseHandle(e));
		
		mainPane.setCenter(centerPane);
		mainPane.setTop(constructControlPane());
		primaryStage.setTitle("도형 그리기");
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.setResizable(false);
		primaryStage.show();
		
		constructPopupMenu();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
