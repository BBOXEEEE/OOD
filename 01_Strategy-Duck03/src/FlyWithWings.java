/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * 전략 패턴: Head First Pattern 예제
 * 전략 패턴: 구체적 전략 클래스
 * FlyWithWings.java: 나는 전략 중 날개를 이용하여 나는 전략
 */
public class FlyWithWings implements FlyStrategy {
	@Override
	public void doFly() {
		System.out.println("나는 날개로 하늘을 날고 있어!!!");
	}
}
