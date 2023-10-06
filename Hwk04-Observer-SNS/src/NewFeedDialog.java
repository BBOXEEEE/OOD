import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Pair;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진
 * @file NewFeedDialog.java
 * 새 게시글을 등록할 때 사용하는 대화창
 */
public class NewFeedDialog {
	private Dialog<Pair<String, String>> dialog = new Dialog<>();
	private VBox contentPane = null;
	private ButtonBar buttonBar = null;
	private ScrollPane messagePane = null;
	private HBox buttonPane = null;
	private Node postButton = null;
	private TextArea messageArea = new TextArea();
	private String imageFileName = null;
	
	private void constructContentPane() {
		contentPane = new VBox();
		contentPane.setPrefWidth(320d);
		contentPane.setPadding(new Insets(10d));
		contentPane.setSpacing(10d);
		contentPane.setAlignment(Pos.CENTER);
		messageArea.setPrefWidth(300d);
		messageArea.setPrefRowCount(3);
		messageArea.setWrapText(true);
		messageArea.setPromptText("무슨 생각을 하고 계신가요?");
		messagePane = new ScrollPane(messageArea);
		messagePane.setHbarPolicy(ScrollBarPolicy.NEVER);
		
		buttonPane = new HBox();
		buttonPane.setPadding(new Insets(10d));
		buttonPane.setSpacing(10d);
		Button imageButton = new Button("사진");
		imageButton.setOnAction(this::getImage);
		buttonPane.getChildren().add(imageButton);
		
		contentPane.getChildren().addAll(buttonBar, messagePane, buttonPane);
	}
	
	private void getImage(ActionEvent event) {
		FileChooser.ExtensionFilter imageFilter
	    	= new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(imageFilter);
		File file = fileChooser.showOpenDialog(dialog.getOwner());
		if(file == null) return;
		copyFile(file);
		imageFileName = file.getName();
		//Image image = new Image("/image/"+file.getName());
		Image image = new Image("file:"+System.getProperty("user.home")+"/Documents/image/"+file.getName());
		ImageView imageView = new ImageView(image);
		if(image.getWidth() > 200)
			imageView.setFitWidth(200d);
		imageView.setPreserveRatio(true);
		contentPane.getChildren().clear();
		contentPane.getChildren().addAll(buttonBar, imageView, messagePane, buttonPane);
		dialog.getDialogPane().getScene().getWindow().sizeToScene(); 
		postButton.requestFocus();
	}
	
	private void copyFile(File source) {
		ByteBuffer buffer = ByteBuffer.allocate(4096); 
		try(
			SeekableByteChannel inChannel =
				Files.newByteChannel(source.toPath());
			SeekableByteChannel outChannel =
				//Files.newByteChannel(Path.of(System.getProperty("user.dir")+"/src/image/"+source.getName()),
				//StandardOpenOption.WRITE, StandardOpenOption.CREATE);
				Files.newByteChannel(Path.of(System.getProperty("user.home")+"/Documents/image/"+source.getName()),
						StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		){
			while(true) {
				buffer.rewind(); 
				if(inChannel.read(buffer) == -1) break; 
				buffer.flip(); 
				outChannel.write(buffer);
			} 
		}
		catch(Exception e) { 
			System.out.println(e.getMessage());
		}
	}
	
	private void constructDialog() {
		// 사용자 구성 Dialog 생성
		dialog.setTitle("게시물 만들기");

		// 등장할 버튼 설정
		ButtonType postButtonType = new ButtonType("게시", ButtonBar.ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(postButtonType, ButtonType.CANCEL);

		// 계정명이 입력된 경우에만 활성화
		postButton = dialog.getDialogPane().lookupButton(postButtonType);
		postButton.setDisable(true);

		buttonBar = (ButtonBar) dialog.getDialogPane().lookup(".button-bar");
		dialog.getDialogPane().getChildren().remove(buttonBar);
		// 입력 데이터에 대한 사전 검증
		messageArea.textProperty().addListener((observable, oldValue, newValue) -> {
			postButton.setDisable(newValue.trim().isEmpty());
		});

		constructContentPane();
		dialog.getDialogPane().setContent(contentPane);
		// 결과 받아오기
		dialog.setResultConverter(dialogButton -> {
			System.out.println("버튼 클릭");
			if(dialogButton == postButtonType) {
				return new Pair<>(messageArea.getText(), imageFileName);
			}
			return null;
		});
	}
	
	public NewFeedDialog() {
		constructDialog();
	}
	
	public Optional<Pair<String, String>> showAndWait(){
		imageFileName = null;
		return dialog.showAndWait();
	}
}
