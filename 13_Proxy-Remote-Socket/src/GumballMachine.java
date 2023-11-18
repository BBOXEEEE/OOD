import java.util.Objects;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진
 * @file GumballMachine.java
 * 껌볼기기
 * 로컬 기기나 원격 기기나 동일 리모컨으로 처리하기 GumballMachineRemote를 구체화하고 있음
 * 껌볼기기가 정확하게 구현되어 있지 않음
 */
public class GumballMachine implements GumballMachineRemote{
	private String location;
	private int numberOfGumballs = 0;
	private GumballState currentState = GumballState.SoldOutState;
	public GumballMachine(String location, int numberOfGumballs) {
		this.location = Objects.requireNonNull(location);
		if(numberOfGumballs > 0) {
			this.numberOfGumballs = numberOfGumballs;
			currentState = GumballState.NoCoinState;
		}
	}
	public String getLocation() {
		return location;
	}
	public int getCount() {
		return numberOfGumballs;
	}
	public GumballState getState() {
		return currentState;
	}
	public void dispense() {
		if(numberOfGumballs > 0) --numberOfGumballs;
	}
}
