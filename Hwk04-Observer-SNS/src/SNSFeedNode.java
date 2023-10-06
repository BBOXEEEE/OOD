import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진
 * @file SNSFeedNode.java
 * 각 게시글 보여주는 GUI 노드
 */
public class SNSFeedNode extends VBox {
	HBox userPane = new HBox();
	ScrollPane contentsPane = null;
	public SNSFeedNode(String name, LocalDateTime postTime, String content){
		userPane.setPadding(new Insets(10d));
		userPane.setSpacing(10d);
		userPane.getChildren().addAll(new Label(name), new Label(getPostTimeInfo(postTime)));
		TextArea feedText = new TextArea();
		feedText.setText(Objects.requireNonNull(content));
		feedText.setEditable(false);
		feedText.setPrefWidth(300d);
		feedText.setWrapText(true);
		Text text = new Text();
		text.setWrappingWidth(feedText.getWidth());
		text.setText(content);
		feedText.setPrefHeight(text.getLayoutBounds().getHeight()+10d);
		contentsPane = new ScrollPane(feedText);
		contentsPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		contentsPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		
		setPadding(new Insets(10d));
		setAlignment(Pos.CENTER);
		setPrefWidth(300d);
		setBorder(new Border(new BorderStroke(
			Color.STEELBLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, 
			new BorderWidths(2))));
		getChildren().addAll(userPane, contentsPane);
	}
	
	// 하루가 경과되면 게시 날짜 정보 (현재 구현된 상태)
	// 하루가 경과되지 않으면 경과된 초, 분, 시간 정보 제시
	private String getPostTimeInfo(LocalDateTime postTime) {
		// 수정하여 완성하시오.
		// 현재 시간을 얻어와서 PostTime과 비교
		// 하루가 경과되면 현재 return된 것을 return
		// 초, 분, 시간 정보를 각각 몇초, 몇분, 몇시간이 지났는지 return
		
		LocalDateTime currentTime = LocalDateTime.now();
		Duration duration = Duration.between(postTime, currentTime);
		
		if(duration.toSeconds() < 60)
			return String.format("%d초전", duration.toSeconds());
		else if (duration.toMinutes() >= 1 && duration.toMinutes() < 60)
			return String.format("%d분전", duration.toMinutes());
		else if (duration.toHours() >= 1 && duration.toHours() < 24)
			return String.format("%d시간전", duration.toHours());
		else
			return String.format("%d년 %d월 %d일", 
					postTime.getYear(), postTime.getMonth().getValue(), postTime.getDayOfMonth());
	}
	
	public void addImage(String imageFileName) {
		Image image = new Image(imageFileName);
		ImageView imageView = new ImageView(image);
		if(image.getWidth()>200)
			imageView.setFitWidth(200d);
		imageView.setPreserveRatio(true);
		getChildren().clear();
		getChildren().addAll(userPane, imageView, contentsPane);
	}

}
