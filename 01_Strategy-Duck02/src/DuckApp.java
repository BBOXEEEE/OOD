import java.util.ArrayList;
import java.util.List;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * 전략 패턴: Head First Pattern 예제
 * DuckApp.java: 오리응용 테스트 프로그램
 * 전략 패턴 적용하기 전 버전
 * 고무오리와 나무오리 추가 (과연 상속으로 모델링하는 것이 올바른 것인지, LSP측면)
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
		ducks.add(new MallardDuck());
		ducks.add(new RedheadDuck());
		ducks.add(new RubberDuck());
		ducks.add(new WoodenDuck());
		System.out.println("오리 수영 시뮬레이션");
		for(Duck duck: ducks) swimSimulation(duck);
		System.out.println("\n오리 날기 시뮬레이션");
		for(Duck duck: ducks) flySimulation(duck);
		System.out.println("\n오리 꽥꽥 시뮬레이션");
		for(Duck duck: ducks) quackSimulation(duck);
	}

}
