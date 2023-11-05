/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file CeilingFanOffCommand.java: 
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * CeilingFanOffCommand: 천장형 선풍기 끄기 명령
 * 명령 패턴에서 명령 객체
 */
public class CeilingFanOffCommand implements Command {
	private CeilingFan fan;
	private CeilingFan.SPEED previousSpeed = CeilingFan.SPEED.OFF;
	public CeilingFanOffCommand(CeilingFan fan){
		this.fan = fan;
	}
	@Override
	public void execute() {
		if(fan.getSpeed() != CeilingFan.SPEED.OFF) {
			previousSpeed = fan.getSpeed();
			fan.setSpeed(CeilingFan.SPEED.OFF);
		}
	}
	@Override
	public void undo() {
		fan.setSpeed(previousSpeed);
	}
}
