/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file Friend.java
 * 친구 정보를 보여줄 때 사용하는 레코드
 */
public record Friend(String name, int commonFriends) {
	public Friend{
		if(commonFriends < 0) 
			throw new IllegalArgumentException();
	}
}
