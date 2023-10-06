import java.util.ArrayList;
import java.util.List;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * 전략 패턴: Head First Pattern 예제
 * DuckApp.java: 오리응용 테스트 프로그램
 * 전략 패턴 적용 버전
 * 람다 표현식의 활용
 */
public class DuckApp {
	public static void flySimulation(Duck duck) {
		duck.display();
		duck.fly.doFly();
	}
	public static void swimSimulation(Duck duck) {
		duck.display();
		duck.swim();
	}
	public static void quackSimulation(Duck duck) {
		duck.display();
		duck.quack();
	}
	public static void main(String[] args) {
		List<Duck> ducks = new ArrayList<>();
		Duck duck1 = new MallardDuck(FlyStrategy::flyWithWings, QuackStrategy::quack);
		Duck duck2 = new RedheadDuck();
		ducks.add(duck1);
		ducks.add(duck2);
		Duck duck3 = new RubberDuck(FlyStrategy::flyNoWay, QuackStrategy::squeak);
		ducks.add(duck3);
		ducks.add(new WoodenDuck(FlyStrategy::flyNoWay, QuackStrategy::muteQuack));
		System.out.println("오리 수영 시뮬레이션");
		for(Duck duck: ducks) swimSimulation(duck);
		System.out.println("\n오리 날기 시뮬레이션");
		for(Duck duck: ducks) flySimulation(duck);
		System.out.println("\n오리 꽥꽥 시뮬레이션");
		for(Duck duck: ducks) quackSimulation(duck);
		duck1.fly = FlyStrategy::flyWithRocket;
		duck2.fly = FlyStrategy::flyNoWay;
		duck3.fly = ()->{System.out.println("난 풍선으로 날아요");};
		System.out.println("\n오리 날기 시뮬레이션");
		for(Duck duck: ducks) flySimulation(duck);
	}
}
