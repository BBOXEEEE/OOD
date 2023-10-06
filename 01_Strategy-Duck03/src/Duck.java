import java.util.Objects;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * 전략 패턴: Head First Pattern 예제
 * 전략 패턴: 추상 클라이언트 클래스
 * Duck.java: 추상클래스, 오리
 */
public abstract class Duck {
	private FlyStrategy flyingStrategy;
	private QuackStrategy quackStrategy;
	// 관계 주입
	public Duck(FlyStrategy flyingStrategy, QuackStrategy quackStrategy) {
		setFlyStrategy(flyingStrategy);
		setQuackStrategy(quackStrategy);
	}
	public void setFlyStrategy(FlyStrategy flyingStrategy) {
		this.flyingStrategy = Objects.requireNonNull(flyingStrategy);
	}
	public void setQuackStrategy(QuackStrategy quackStrategy) {
		this.quackStrategy = Objects.requireNonNull(quackStrategy);
	}
	public void quack() {
		quackStrategy.doQuack();
	}
	public void swim() {
		System.out.println("난 물에서 수영하고 있어");
	}
	public void fly() {
		flyingStrategy.doFly();
	}
	public abstract void display();
}
