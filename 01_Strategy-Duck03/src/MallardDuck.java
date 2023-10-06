/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진
 * 전략 패턴: Head First Pattern 예제
 * 전략 패턴: 구체적 클라이언트 클래스 
 * MallardDuck.java: 청둥오리, 부모: Duck
 */
public class MallardDuck extends Duck {
	public MallardDuck(FlyStrategy flyingStrategy, QuackStrategy quackStrategy) {
		super(flyingStrategy, quackStrategy);
	}
	@Override
	public void display() {
		System.out.println("난 청둥오리");
	}
}
