import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file Friend.java
 * 친구 정보를 보여줄 때 사용하는 GUI 노드
 */
public class FriendTable extends TableView<Friend> {
	// 테이블 구성
	@SuppressWarnings("unchecked")
	public FriendTable() {
		TableColumn<Friend, String> colName = new TableColumn<>("이름");
		colName.setMinWidth(80d);
		colName.setStyle("-fx-alignment: center");
		colName.setCellValueFactory(
			f -> new SimpleStringProperty(f.getValue().name()));
		
		TableColumn<Friend, Integer> colFriends = new TableColumn<>("함께 아는 친구");
		colFriends.setMinWidth(80d);
		colFriends.setStyle("-fx-alignment: center");
		colFriends.setCellValueFactory(
			f -> new SimpleIntegerProperty(f.getValue().commonFriends()).asObject());
		
		getColumns().addAll(colName, colFriends);
		setSelectionModel(null);
	}
	
	// 테이블에서 보여줄 데이터 등록
	public void loadData(User user) {
		ObservableList<Friend> friendList = FXCollections.observableArrayList();
		
		Set<Integer> userFriends = user.getFriendList();
		for(var friendID: userFriends) {
			// 목록 중 한명의 친구 정보
			// 친구 정보의 친구 목록을 얻어와서 교집합의 크기를 계산
			Optional<User> friend = SNSServer.getServer().getUser(friendID);
			// 완성하세요.
			if(friend.isPresent()) {
				Set<Integer> friendFriends = friend.get().getFriendList();
				int mutualFriends = calculateMutualFriends(userFriends, friendFriends);
				friendList.add(new Friend(friend.get().getName(), mutualFriends));
			}
		}
		setItems(friendList);
	}
	
	private int calculateMutualFriends(Set<Integer> user, Set<Integer> friend) {
		Set<Integer> ret = new HashSet<>(user);
		ret.retainAll(friend);
		return ret.size();
	}
}
