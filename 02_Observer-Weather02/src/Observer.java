/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file Observer.java
 * 관찰자 패턴: Head First Pattern 예제
 * 관찰자 패턴: 관찰자 interface
 * push 방법 (데이터 전달)
 */
public interface Observer {
	void update(Object... data);
}
