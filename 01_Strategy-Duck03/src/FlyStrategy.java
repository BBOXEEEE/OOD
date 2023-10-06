/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진 
 * 전략 패턴: Head First Pattern 예제
 * FlyingStrategy.java: 나는 전략
 * 전략 패턴: 전략 	interface
 */

@FunctionalInterface
public interface FlyStrategy {
	void doFly();
}
