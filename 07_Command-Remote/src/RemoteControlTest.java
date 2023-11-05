/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file RemoteControlTest.java
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * 만능 리모컨 테스트 프로그램
 */
public class RemoteControlTest {
	public static void main(String[] args) {
		RemoteControl remoteControl = new RemoteControl();
		RoomLight roomLight = new RoomLight("안방");
		Stero stero = new Stero();
		CeilingFan fan = new CeilingFan();
		
		Command roomLightOnCommand = new RoomLightOnCommand(roomLight);
		Command roomLightOffCommand = new RoomLightOffCommand(roomLight);
		Command steroOnWithCDCommand = new SteroOnWithCDCommand(stero);
		Command steroOnWithUSBCommand = new SteroOnWithUSBCommand(stero);
		Command steroOffCommand = new SteroOffCommand(stero);
		Command ceilingFanHighCommand = new CeilingFanHighCommand(fan);
		Command ceilingFanOffCommand = new CeilingFanOffCommand(fan);
		
		
		remoteControl.setCommand(0, roomLightOnCommand, roomLightOffCommand);
		remoteControl.setCommand(1, steroOnWithCDCommand, steroOffCommand);
		remoteControl.setCommand(2, steroOnWithUSBCommand, steroOffCommand);
		remoteControl.setCommand(3, ceilingFanHighCommand, ceilingFanOffCommand);
		
		remoteControl.onButtonWasPressed(0);
		remoteControl.undoButtonWasPressed();
		remoteControl.onButtonWasPressed(2);
		remoteControl.onButtonWasPressed(1);
		remoteControl.offButtonWasPressed(1);
		remoteControl.undoButtonWasPressed();
		remoteControl.onButtonWasPressed(3);
		remoteControl.offButtonWasPressed(3);
		remoteControl.undoButtonWasPressed();
	}
}
