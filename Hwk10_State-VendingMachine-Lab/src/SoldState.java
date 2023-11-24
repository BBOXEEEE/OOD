/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 2019136056 박세현 
 * 상태 패턴
 * SoldState.java
 * 상태 객체 
 */
public class SoldState implements VendingMachineState {
	@Override
	public void dispenseItem(VendingMachine vendingMachine, Item item) {
		vendingMachine.removeItem(item);
		int currentCash = vendingMachine.getInsertedBalance() - item.price;
		CashRegister userCashRegister = vendingMachine.getChange(currentCash);
		vendingMachine.setUserCashRegister(userCashRegister);
		if (vendingMachine.canBuyAnyItem()) {
			vendingMachine.changeToHasCashState();
		} else {
			vendingMachine.returnChange();
			if (vendingMachine.isEmpty()) vendingMachine.changeToNoItemState();
			else vendingMachine.changeToNoCashState();
		}
	}
}
