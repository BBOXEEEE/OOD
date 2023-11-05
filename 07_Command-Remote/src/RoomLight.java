/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @file RoomLight.java: 방등
 * @author 김상진
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * RoomLight: 방등, 명령 패턴에서 receiver 객체
 */
public class RoomLight {
	private String location;
	public RoomLight(String location) {
		this.location = location;
	}
	public void on(){
		System.out.printf("%s: 불이 켜짐%n", location);	
	}
	public void off(){
		System.out.printf("%s: 불이 꺼짐%n", location);
	}
}
