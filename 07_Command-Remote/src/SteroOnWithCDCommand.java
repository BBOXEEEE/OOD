/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file SteroOnWithCDCommand.java
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * SteroOnWithCDCommand: 오디오의 CD 켜기 명령
 * 명령 패턴에서 명령 객체
 */
public class SteroOnWithCDCommand implements Command {
	private Stero stero;
	private boolean isOn = false;
	private Stero.InputType inputType = Stero.InputType.CD;
	private int volume = 0;
	public SteroOnWithCDCommand(Stero stero){
		this.stero = stero;
	}
	@Override
	public void execute() {
		isOn = stero.isOn();
		inputType = stero.getCurrentInput();
		volume = stero.getVolume();
		stero.on();	
		stero.setInput(Stero.InputType.CD);
		stero.setVolume(11);
	}
	@Override
	public void undo() {
		if(isOn){
			stero.on();
			stero.setInput(inputType);
			stero.setVolume(volume);
		}
		else stero.off();
	}
}
