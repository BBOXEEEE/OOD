
/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 파일명 FrogTest.java
 * @author 201913605 박세현
 * @version 2023년도 2학기
 * 연습문제 1
 */

public class FrogTest {
	public static void main(String[] args) {
		// Frog Test
		System.out.println("=== Frog1 ===");
		Frog frog1 = new Frog();
		frog1.jump();
		frog1.croak();
		
		// 상속을 이용한 RobotFrog Test
		System.out.println("=== Frog2 ===");
		RobotFrog1 frog2 = new RobotFrog1();
		frog2.jump();
		frog2.croak();
		System.out.println("frog2 current battery : " + frog2.getBattery());
		frog2.charge();
		System.out.println("frog2 current battery : " + frog2.getBattery());
		
		// 포함관계를 이용한 RobotFrog Test
		System.out.println("=== Frog3 ===");
		RobotFrog2 frog3 = new RobotFrog2();
		frog3.jump();
		frog3.croak();
		System.out.println("frog3 current battery : " + frog3.getBattery());
		frog3.charge();
		System.out.println("frog3 current battery : " + frog3.getBattery());
	}
}
