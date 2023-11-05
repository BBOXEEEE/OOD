import java.util.Arrays;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file SimpleRemoteControl.java
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * RemoteControl: 만능 리모컨
 * 버튼 수 5개: On 5개, Off 5개, undo 1개
 * 명령 패턴에서 invoker
 */
public class RemoteControl {
	private Command[] onCommands = new Command[5];
	private Command[] offCommands = new Command[5];
	private Command undoCommand = NoCommand.unique;
	
	public RemoteControl(){
		// 모두 NoCommand 널 객체로 초기화
		Arrays.fill(onCommands, NoCommand.unique);
		Arrays.fill(offCommands, NoCommand.unique);
	}
	
	public void setCommand(int slot, Command onCommand, Command offCommand){
		if(slot < 0 || slot >= onCommands.length) 
			throw new IndexOutOfBoundsException("없는 slot");
		onCommands[slot] = onCommand;
		offCommands[slot] = offCommand;
	}
	
	public void onButtonWasPressed(int slot){
		if(slot < 0 || slot >= onCommands.length) 
			throw new IndexOutOfBoundsException("없는 slot");
		onCommands[slot].execute();
		undoCommand = onCommands[slot];
	}
	
	public void offButtonWasPressed(int slot){
		if(slot < 0 || slot >= onCommands.length) 
			throw new IndexOutOfBoundsException("없는 slot");
		offCommands[slot].execute();
		undoCommand = offCommands[slot];
	}
	
	public void undoButtonWasPressed() {
		undoCommand.undo();
		undoCommand = NoCommand.unique;
	}
}
