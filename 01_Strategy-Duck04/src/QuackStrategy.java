/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진 
 * 전략 패턴: Head First Pattern 예제
 * 전략 패턴: 전략 interface
 * QuackStrategy.java: 우는 전략
 * 이 버전에서는 인자를 활용하고 있지 않지만 클라이언트 객체를 인자로
 * 받는 형태로 전략 메소드를 정의하고 있음
 */

@FunctionalInterface
public interface QuackStrategy {
	void doQuack(Duck duck);
	
	static void quack(Duck duck) {
		System.out.println("꽥꽥");
	}
	
	static void muteQuack(Duck duck) {
	}
	
	static void squeak(Duck duck) {
		System.out.println("삑삑");
	}
}
