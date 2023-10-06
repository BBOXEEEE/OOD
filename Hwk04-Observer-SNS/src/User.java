import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 관찰자 패턴 실습
 * @file User.java
 * @author 김상진
 * SNS 가입자
 */
public class User{
	private int id;
	private String name;
	private String email;
	private Set<Integer> friendList = new TreeSet<>();
	private Deque<SNSFeed> timeline = new LinkedList<>();
	private Deque<SNSFeed> feeds = new LinkedList<>(); // 사용자가 직접 작성한 게시글 모음
	
	// 뷰와 사용자는 1:1 관계. Hard Coded Notification
	private SNSWindow snsWindow = null;
	
	public User(int userID, String name, String email) {
		if(userID < 0) throw new IllegalArgumentException();
		this.id = userID;
		this.name = Objects.requireNonNull(name);
		this.email = Objects.requireNonNull(email);
	}
	
	public int getID() {
		return id; 
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Set<Integer> getFriendList(){
		return friendList;
	}
	
	public Deque<SNSFeed> getTimeline(){
		return timeline;
	}
	
	public Deque<SNSFeed> getFeeds(){
		return feeds;
	}
	
	
	public void setView(SNSWindow snsWindow) {
		this.snsWindow = snsWindow;
	}

	public void addFriend(int friendID) {
		if(friendList.contains(friendID)) 
			throw new IllegalArgumentException();
		else friendList.add(friendID);
	}
	
	// 관찰자 패턴에서 관찰 대상이 통보 메소드
	public void update(SNSFeed feed) {
		if(feed.posterID() == id) {
			feeds.addLast(feed);
			timeline.addLast(feed);
			if(snsWindow != null) snsWindow.updateFeeds();
		}
		else if(friendList.contains(feed.posterID())) {
			timeline.addLast(feed);
			if(snsWindow != null) snsWindow.updateTimeline();
		}
		else throw new IllegalArgumentException();
	}
}
