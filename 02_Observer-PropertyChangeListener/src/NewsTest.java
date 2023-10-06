/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file NewsTest.java
 * 관찰자 패턴: java.beans.PropertyChangeSupport 활용한 관찰자 패턴의 구현
 * 테스트 프로그램
 */
public class NewsTest {
	public static void main(String[] args) {
		NewsAgency publisher = new NewsAgency();
		NewsSubscriber observer1 = new NewsSubscriber();
		FancyNewsSubscriber observer2 = new FancyNewsSubscriber();
		 
		publisher.registerObserver(observer1);
		publisher.registerObserver(observer2);
		
		publisher.setNews("리버풀 0: 뉴캐슬 1");
		publisher.setNews("리버풀 1: 뉴캐슬 1");
		publisher.setNews("리버풀 1: 뉴캐슬 1"); // 통보되지 않음
		publisher.setNews("리버풀 2: 뉴캐슬 1");
		publisher.setNews("리버풀 3: 뉴캐슬 1");	
	}
}
