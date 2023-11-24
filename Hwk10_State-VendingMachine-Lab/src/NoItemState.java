/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 2019136056 박세현 
 * 상태 패턴
 * NoItemState.java
 * 상태 객체 
 */
public class NoItemState implements VendingMachineState {
	@Override
	public void insertCash(VendingMachine vendingMachine, Currency currency, int amount) {
		vendingMachine.returnChange();
	}
}
