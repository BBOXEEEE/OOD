
/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 파일명 RobotFrog1.java
 * @author 201913605 박세현
 * @version 2023년도 2학기
 * 연습문제 1
 */

public class RobotFrog1 extends Frog{
	private int battery;
	
	public RobotFrog1() {
		this.battery = 5;
	}
	
	public int getBattery() {
		return battery;
	}
	
	@Override
	public void jump() {
		if(battery > 0) {
			System.out.println("폴짝폴짝");
			--battery;
		} else {
			System.out.println("배터리 부족!");
		}
	}
	
	@Override
	public void croak() {
		if(battery > 0) {
			System.out.println("개굴개굴");
			--battery;
		} else {
			System.out.println("배터리 부족!");
		}
	}
	
	public void charge() {
		this.battery = 5;
	}
}
