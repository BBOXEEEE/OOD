/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진 
 * 전략 패턴: Head First Pattern 예제
 * 전략 패턴: 구체적 전략 클래스
 * MuteQuack.java: 울 수 없는 전략 (dummy object)
 */
public class MuteQuack implements QuackStrategy {
	@Override
	public void doQuack() {
	}
}
