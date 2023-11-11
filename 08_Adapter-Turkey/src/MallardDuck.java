/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론
 * @version 2023년도 2학기
 * @author 김상진
 * Head First Design Pattern
 * MallardDuck.java: 청둥오리
 */
public class MallardDuck implements Duck {
	@Override
	public void quack() {
		 System.out.println("꽥꽥");
	}
	@Override
	public void fly() {
		System.out.println("하늘을 날고 있어");
	}
}
