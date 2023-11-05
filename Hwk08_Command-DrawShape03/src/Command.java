/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 2019136056 박세현
 * Lab 06. 명령 패턴
 * 실습 3. Command Manager를 이용한 undo/redo 기능 구현
 * Command.java: 명령 interface
 */
public interface Command {
	void excute();
	void undo();
	void redo();
}
