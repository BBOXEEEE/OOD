/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * 전략 패턴: Head First Pattern 예제
 * 전략 패턴: 구체적 클라이언트 클래스
 * RedheadDuck.java: 빨간머리오리, 부모: Duck
 */
public class RedheadDuck extends Duck {
	// 이 방법은 구체적 클래스와 의존관계를 맺지 않지만 생성할 때 불편함
	/*
	public RedheadDuck(FlyStrategy flyingStrategy, QuackStrategy quackStrategy) {
		super(flyingStrategy, quackStrategy);
	}
	*/
	public RedheadDuck() {
		super(FlyStrategy::flyWithWings, QuackStrategy::quack);
	}
	@Override
	public void display() {
		System.out.println("난 빨간머리오리");
	}
}
