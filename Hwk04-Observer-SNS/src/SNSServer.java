import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진
 * @file SNSServer.java 
 * SNS 서비스에서 통신 서버와 데이터베이스 서버 역할을 함 
 */
public class SNSServer {
	// 데이터베이스 역할을 위한 두 개의 맵
	private Map<String, Integer> userMails = new HashMap<>(); // 가입 중복 여부 체크를 위한 수단
	private Map<Integer, User> users = new HashMap<>();
	private static SNSServer uniqueInstance = null;
	private static int lastUserID = 0;
	private SNSServer() {}
	// 싱글톤으로 모델링, 전역 변수처럼 사용할 수 있음
	public static SNSServer getServer() {
		if(uniqueInstance == null) uniqueInstance = new SNSServer();
		return uniqueInstance;
	}
	
	public boolean isDuplicateUser(String email) {
		return userMails.containsKey(email);
	}
	
	public int addUser(String name, String email) {
		if(!isDuplicateUser(email)) {
			int userID = ++lastUserID;
			users.put(userID, new User(userID, name, email));
			userMails.put(email, userID);
			return userID;
		}
		return -1;
	}
	
	public Optional<User> getUser(int userID){
		if(users.containsKey(userID)) {
			return Optional.of(users.get(userID));
		}
		return Optional.empty();
	}
	
	public Collection<User> getUsers(){
		return users.values();
	}
	
	public void newFeed(SNSFeed feed) {
		if(!users.containsKey(feed.posterID())) 
			throw new IllegalArgumentException();
		User poster = users.get(feed.posterID());
		poster.update(feed); // 게시자에게 전달
		// 완성하세요. (게시자 친구에게 전달해야 함)
		// 게시자의 친구 정보를 얻어서 모든 친구에게 update로 feed를 전달
		Set<Integer> friends = poster.getFriendList();
		for(var friendID: friends) {
			Optional<User> friend = SNSServer.getServer().getUser(friendID);
			friend.get().update(feed);
		}
	}
}

/** 숙제 질문에 대한 답변
 * 1) 관찰자 패턴은 크게 push, pull 방법으로 나눌 수 있다. 
 * 		게시글 게시에 적용한 관찰자 패턴은 push와 pull 중 어디에 해당하는지 제시하고, 그 이유를 간단히 설명하시오.
 * -> push 방법에 해당한다. SNSServer 클래스에서 newFeed() 메소드에서 새로운 게시글이 있을 때 작성자와 작성자의 친구들에게 새로운 게시물이 있다고 통보한다.
 * 		게시자와 친구에게 update() 메소드로 게시물을 전달하면 User 클래스의 update 메소드는 게시물의 게시자를 기준으로 SNS 타임라인에 추가하고 있기 때문이다.
 * 
 * 2) 사용자는 여러 다른 사용자와 친구 관계를 맺고 있기 때문에 복수의 관찰 대상을 관찰하는 형태이다. 
 * 		이와 같이 다중 관찰 대상 환경에서는 통보를 받았을 때 관찰 대상을 구분할 수 있어야 한다. 
 * 		이 응용 에서는 관찰 대상을 어떻게 구분하는지 설명하시오. 
 * 		또 관찰 대상을 실제 구분하는 것이 필요한 것인지 설명하시오.
 * -> 새로운 게시글을 통보 받았을 때 이 응용에서는 관찰 대상은 posterID 라는 게시자를 식별하는 변수로 판단한다.
 * 		게시자가 User에서 유지 중인 id인지, 친구 목록에 있는 User의 id인지를 판단한다.
 * 		게시자가 본인이라면 타임라인 뿐만 아니라 내 게시글 목록에도 추가해야하기 때문에 이 응용에서는 관찰 대상을 구분하는 것이 필요하다.
 * 
 * 3) 실제 코드를 보면 이 두 객체 외에 관찰 대상과 관찰자로 모델링하는 것이 있다.
 * 		그것이 무엇인지 제시하시오.
 * -> FriendTable의 loadData() 메소드의 ObservableList이다. ObservableList는 데이터 변경 사항을 감지해 UI에 반영하는데 사용된다.
 * 		친구 목록을 friendList에 추가하고 setItems를 통해 JavaFX 테이블에 업데이트된 데이터를 반영하면 UI에 적용된다.
 * 
 * 4) 이 응용처럼 관찰자가 관찰자인 동시에 관찰 대상이면 관찰자 패턴을 적용할 때 주의할 점이 있다.
 * 		그것이 무엇인지 설명하시오. 실제 이 응용에서는 이 문제가 나타나지 않는다.
 * -> 관찰 대상이 관찰자에게 통보하고, 다시 이 관찰자가 관찰 대상이되어 다른 관찰 대상에게 통보하는 경우와 같이 연쇄적인 통보가 발생하면 통보가 무한 반복될 수 있다는 문제점이 있다.
 * <답안> User와 SNSWindow!
 * 
 * 5) 현재 각 사용자가 유지하는 친구 목록만 이용하여 새 친구를 추천하는 알고리즘을 제안하시오.
 * -> 각 사용자마다 친구 목록을 불러온다. 그리고 그 친구 목록을 순회하며 친구의 친구 목록을 불러온다. 함께 아는 친구를 찾고 함께 아는 친구의 수가 가장 많은 친구를 새 친구로 추천한다.
 * 		예를 들어 A의 친구는 B, C, D가 있다고 가정한다.
 * 		B는 A, E, F와 친구이다.
 * 		C는 A, F, G와 친구이다.
 * 		D는 A, F, G, H와 친구이다.
 * 		위 알고리즘을 적용한다면 A에게 추천되는 새 친구는 B, C, D가 모두 친구인 F가 추천된다.
 */