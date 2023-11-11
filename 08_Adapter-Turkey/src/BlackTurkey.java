/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론
 * @version 2023년도 2학기
 * @author 김상진
 * Head First Design Pattern
 * BlackTurkey.java: 칠면조의 한 종류
 */
public class BlackTurkey implements Turkey {
	@Override
	public void gobble() {
		System.out.println("블랙칠면조 고블 고블");

	}
	@Override
	public void fly() {
		System.out.println("푸드덕 푸드덕");
	}
}
