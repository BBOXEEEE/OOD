
/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 파일명 RobotFrog2.java
 * @author 201913605 박세현
 * @version 2023년도 2학기
 * 연습문제 1
 */

public class RobotFrog2 {
	private Frog frog;
	private int battery;
	
	public RobotFrog2() {
		this.frog = new Frog();
		this.battery = 5;
	}
	
	public int getBattery() {
		return battery;
	}
	
	public void jump() {
		if(battery > 0) {
			frog.jump();
			--battery;
		} else {
			System.out.println("배터리 부족!");
		}
	}
	
	public void croak() {
		if(battery > 0) {
			frog.croak();
			--battery;
		} else {
			System.out.println("배터리 부족!");
		}
	}
	
	public void charge() {
		this.battery = 5;
	}
}
