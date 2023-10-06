/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @answer 김상진
 * 전략 패턴: Head First Pattern 예제
 * 전략 패턴: 구체적 클라이언트 클래스
 * RubberDuck.java: 고무오리, 부모: Duck
 */
public class RubberDuck extends Duck {
	public RubberDuck(FlyStrategy flyingStrategy, QuackStrategy quackStrategy) {
		super(flyingStrategy, quackStrategy);
	}
	@Override
	public void display() {
		System.out.println("난 고무오리");
	}
}
