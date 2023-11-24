/**                                                                                 
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 2019136056 박세현 
 * 상태 패턴
 * HasCashState.java
 * 상태 객체  
 */
public class HasCashState implements VendingMachineState {
	@Override
	public void selectItem(VendingMachine vendingMachine, Item item) throws ChangeNotAvailableException {
		if(vendingMachine.canBuyItem(item)) {
			vendingMachine.changeToSoldState();
		}
		else {
			if(vendingMachine.canBuyAnyItem()) {
				throw new ChangeNotAvailableException(false);
			} else {
				vendingMachine.returnChange();
				vendingMachine.changeToNoCashState();
				throw new ChangeNotAvailableException(true);
			}
		}
	}

	@Override
	public void cancel(VendingMachine vendingMachine) {
		vendingMachine.returnChange();
		vendingMachine.changeToNoCashState();
	}
}
