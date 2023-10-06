import java.util.ArrayList;
import java.util.List;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * 전략 패턴: Head First Pattern 예제
 * DuckApp.java: 오리응용 테스트 프로그램
 * 전략 패턴 적용 버전
 */
public class DuckApp {
	public static void flySimulation(Duck duck) {
		duck.display();
		duck.fly();
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
		Duck duck1 = new MallardDuck(new FlyWithWings(), new Quack());
		//Duck duck2 = new RedheadDuck(new FlyWithWings(), new Quack());
		Duck duck2 = new RedheadDuck();
		ducks.add(duck1);
		ducks.add(duck2);
		Duck duck3 = new RubberDuck(new FlyNoWay(), new Squeak());
		ducks.add(duck3);
		ducks.add(new WoodenDuck(new FlyNoWay(), new MuteQuack()));
		System.out.println("오리 수영 시뮬레이션");
		for(Duck duck: ducks) swimSimulation(duck);
		System.out.println("\n오리 날기 시뮬레이션");
		for(Duck duck: ducks) flySimulation(duck);
		System.out.println("\n오리 꽥꽥 시뮬레이션");
		for(Duck duck: ducks) quackSimulation(duck);
		duck1.setFlyStrategy(new FlyWithRocket());
		duck2.setFlyStrategy(new FlyNoWay());
		duck3.setFlyStrategy(()->{System.out.println("난 풍선으로 날아요");});
		System.out.println("\n오리 날기 시뮬레이션");
		for(Duck duck: ducks) flySimulation(duck);
	}
}
