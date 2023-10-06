import java.time.LocalDateTime;
import java.util.Deque;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file SNSWindow
 * 각 사용자의 SNS창 (View+Controller 역할, 모델은 User 클래스)
 * 관찰자 패턴: User subject에 대한 관찰자
 */
public class SNSWindow extends Stage{
	private User user;
	private VBox timelinePane = new VBox();
	private FriendTable friendTable = new FriendTable();
	
	public SNSWindow(User user) {
		this.user = user;
		timelinePane.setPadding(new Insets(10d));
		timelinePane.setSpacing(10d);
		ScrollPane pane = new ScrollPane(timelinePane);
		
		VBox topPane = new VBox();
		topPane.getChildren().addAll(
			constructControlPane(), constructNewFeedPane(), pane);
		
		setTitle(user.getName());
		setScene(new Scene(topPane, 330d, 300d));
	}
	
	private Pane constructControlPane() {
		HBox controlPane = new HBox();
		controlPane.setPadding(new Insets(10d));
		controlPane.setSpacing(10d);
		Button homeButton = new Button("타임라인");
		Button friendListButton = new Button("친구");
		Button alarmButton = new Button("알림");
		Button userTimeLineButton = new Button("내 게시물");
		controlPane.getChildren().addAll(
			homeButton, friendListButton, alarmButton, userTimeLineButton);
		
		homeButton.setOnAction(e->updateTimeline());
		userTimeLineButton.setOnAction(e->updateFeeds());
		friendListButton.setOnAction(this::updateFriends);
		return controlPane;
	}
	
	private Pane constructNewFeedPane() {
		HBox postPane = new HBox();
		postPane.setPadding(new Insets(10d));
		postPane.setSpacing(10d);
		Button newPostButton = new Button("무슨 생각을 하고 계신가요?");
		newPostButton.setOnAction(this::newPost);
		postPane.getChildren().add(newPostButton);
		return postPane;
	}
	
	private void newPost(ActionEvent event) {
		NewFeedDialog dialog = new NewFeedDialog();
		Optional<Pair<String, String>> ret = dialog.showAndWait();
		if(ret.isPresent()) {
			SNSFeed newFeed = new SNSFeed(
				user.getID(), user.getName(),
				ret.get().getKey(), ret.get().getValue(), LocalDateTime.now());
			SNSServer.getServer().newFeed(newFeed);
		}
	}
	
	public void updateFeeds() {
		timelinePane.getChildren().clear();
		updateTimelinePane(user.getFeeds());
	}
	
	public void updateTimeline() {
		timelinePane.getChildren().clear();
		updateTimelinePane(user.getTimeline());
	}
	
	private void updateTimelinePane(Deque<SNSFeed> feeds) {
		var it = feeds.descendingIterator();
		while(it.hasNext()) {
			SNSFeed feed = it.next();
			SNSFeedNode feedNode = new SNSFeedNode(
		    	feed.posterName(), feed.postTime(), feed.content());
		    if(feed.imageFileName() != null) {
		    	feedNode.addImage("file:"+System.getProperty("user.home")+"/Documents/image/"+feed.imageFileName());
		    }
		    timelinePane.getChildren().add(feedNode);
		}
	}
	
	private void updateFriends(ActionEvent event) {
		friendTable.loadData(user);
		timelinePane.getChildren().clear();
		timelinePane.getChildren().add(friendTable);
	}
	
	@SuppressWarnings("unused")
	private MenuBar constructMenuBar() {
		MenuBar menuBar = new MenuBar();
		return menuBar;
	}
	
	public static boolean snsConfirmDialog(String title, String content,
			String okButton, String cancelButton){
		Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(null);
    	alert.setContentText(content);
    	ButtonType buttonTypeOK = new ButtonType(okButton, ButtonData.OK_DONE);
    	ButtonType buttonTypeCancel = new ButtonType(cancelButton, ButtonData.CANCEL_CLOSE);
    	alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
    	ImageView icon = new ImageView(new Image("koreatech.jpg"));
		icon.setFitHeight(80);
		icon.setPreserveRatio(true);
		alert.setGraphic(icon);
    	Optional<ButtonType> result = alert.showAndWait();
    	return (result.isPresent()&&result.get() == buttonTypeOK);
	}
}
