/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론
 * @version 2023년도 2학기
 * @author 김상진 
 * Head First Design Pattern
 * TurkeyAdapterTest.java: 테스트 프로그램
 */
public class TurkeyAdapterTest {
	public static void turkeyTest(Turkey turkey) {
		turkey.gobble();
		turkey.fly();
	}
	public static void duckTest(Duck duck) {
		duck.quack();
		duck.fly();
	}
	
	// 객체 어댑터는 다양한 칠면조에 대한 어댑터로 사용 가능
	public static void main(String[] args) {
		MallardDuck duck = new MallardDuck();
		BlueSlateTurkey bsTurkey = new BlueSlateTurkey();
		BlackTurkey bTurkey = new BlackTurkey();
		TurkeyObjectAdapter turkeyObjectAdapter1 = new TurkeyObjectAdapter(bsTurkey);
		TurkeyObjectAdapter turkeyObjectAdapter2 = new TurkeyObjectAdapter(bTurkey);
		TurkeyClassAdapter turkeyClassAdapter = new TurkeyClassAdapter();
		TurkeyTwoWayAdapter turkey2WayAdapter = new TurkeyTwoWayAdapter(bsTurkey);
		
		System.out.println("오리 테스트");
		duckTest(duck);
		duckTest(turkeyObjectAdapter1);
		duckTest(turkeyObjectAdapter2);
		duckTest(turkeyClassAdapter);
		duckTest(turkey2WayAdapter);
		
		System.out.println("\n칠면조 테스트");
		turkeyTest(bsTurkey);
		turkeyTest(turkeyClassAdapter);
		turkeyTest(turkey2WayAdapter);
	}
}
