/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @file CeilingFan.java
 * @author 김상진
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * CeilingFan: 천장형 선풍기, 명령 패턴에서 receiver 객체
 */
public class CeilingFan {
	public enum SPEED {OFF, LOW, MEDIUM, HIGH};
	private SPEED speed = SPEED.OFF;

	public void setSpeed(CeilingFan.SPEED speed){
		if(this.speed != speed){
			this.speed = speed;
			if(speed == SPEED.OFF) System.out.println("선풍기 꺼짐");
			else System.out.printf("선풍기 켜짐. 현재 속도: %d%n", speed.ordinal());
		}
	}
	public SPEED getSpeed() {
		return speed;
	}
}
