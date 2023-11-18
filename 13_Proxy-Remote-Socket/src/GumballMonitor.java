import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진
 * @file GumballMachine.java
 * 껌볼기기 모니터
 */
public class GumballMonitor {
	private GumballMachineRemote gumballMachine;
	public GumballMonitor(GumballMachineRemote gumballMachine) {
		setGumballMachine(gumballMachine);
	}
	public void setGumballMachine(GumballMachineRemote gumballMachine) {
		this.gumballMachine = Objects.requireNonNull(gumballMachine);
	}
	public void report() {
		try {
			System.out.println("껌볼 기계 위치: " 
				+ gumballMachine.getLocation());
			System.out.println("껌볼 수: " 
				+ gumballMachine.getCount());
			System.out.println("기계 상태: " 
				+ gumballMachine.getState());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		GumballMachineRemote gumballMachine =
			new GumballMachineProxy("localhost", 6666);
		GumballMonitor gumballMonitor = new GumballMonitor(gumballMachine);
		gumballMonitor.report();
		gumballMonitor.setGumballMachine(new GumballMachine("제2공학관", 20));
		gumballMonitor.report();
		
	}
}
