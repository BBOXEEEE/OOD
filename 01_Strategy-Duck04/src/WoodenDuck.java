/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * 전략 패턴: Head First Pattern 예제
 * 전략 패턴: 구체적 클라이언트 클래스
 * WoodenDuck.java: 나무오리, 부모: Duck
 */
public class WoodenDuck extends Duck {
	public WoodenDuck(FlyStrategy flyingStrategy, QuackStrategy quackStrategy) {
		super(flyingStrategy, quackStrategy);
	}
	@Override
	public void display() {
		System.out.println("난 나무오리");
	}
}
