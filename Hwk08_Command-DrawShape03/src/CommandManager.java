import java.util.Stack;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 2019136056 박세현
 * Lab 06. 명령 패턴
 * 실습 3. Command Manager를 이용한 undo/redo 기능 구현
 * CommandManager.java: 명령을 관리하는 명령 관리자 클래스
 */
public class CommandManager {
	private Stack<Command> undoStack = new Stack<>();
	private Stack<Command> redoStack = new Stack<>();
	
	public void excute(Command command) {
		undoStack.add(command);
		command.excute();
		redoStack.clear();
	}
	
	public boolean undo() {
		Command command = undoStack.pop();
		redoStack.add(command);
		command.undo();

		if(undoStack.isEmpty())
			return true;
		else
			return false;
	}
	
	public boolean redo() {
		Command command = redoStack.pop();
		undoStack.add(command);
		command.redo();

		if (redoStack.isEmpty())
			return true;
		else
			return false;
	}

}
