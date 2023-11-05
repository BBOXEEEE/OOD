/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file RoomLightOnCommand.java
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * RoomLightOnCommand: 방등 점등 명령
 * 명령 패턴에서 명령 객체
 */
public class RoomLightOnCommand implements Command {
	private RoomLight light;
	public RoomLightOnCommand(RoomLight light) {
		this.light = light;
	}
	@Override
	public void execute() {
		light.on();
	}
	@Override
	public void undo() {
		light.off();
	}
}
