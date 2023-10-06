/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * 전략 패턴: Head First Pattern 예제
 * MallardDuck.java: 청둥오리, 부모: Duck
 * 전략 패턴 적용하기 전 버전
 */
public class MallardDuck extends Duck {
	@Override
	public void display() {
		System.out.println("난 청둥오리");
	}
}
