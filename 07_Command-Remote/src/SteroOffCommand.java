/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file SteroOffCommand.java
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * SteroOffCommand: 오디오 끄기 명령 
 * 명령 패턴에서 명령 객체
 */
public class SteroOffCommand implements Command {
	private Stero stero;
	private boolean previouslyOn = false;
	private Stero.InputType previousInput = Stero.InputType.CD;
	private int previousVolume = 0;
	
	public SteroOffCommand(Stero stero){
		this.stero = stero;
	}
	@Override
	public void execute() {
		if(stero.isOn()) {
			previouslyOn = true;
			previousInput = stero.getCurrentInput();
			previousVolume = stero.getVolume();
			stero.off();
		}
		else {
			previouslyOn = false;
		}
	}
	@Override
	public void undo() {
		if(previouslyOn) {
			stero.on();
			stero.setInput(previousInput);
			stero.setVolume(previousVolume);
		}
	}
}
