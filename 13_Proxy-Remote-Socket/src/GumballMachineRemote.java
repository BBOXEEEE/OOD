import java.io.IOException;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진
 * @file GumballMachineRemote.java
 * 로컬기기, 원격기기가 모두 구현해야 하는 interface
 */
public interface GumballMachineRemote {
	String getLocation() throws IOException;
	int getCount() throws IOException;
	GumballState getState() throws IOException;
}
