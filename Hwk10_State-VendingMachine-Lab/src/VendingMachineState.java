/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 2019136056 박세현 
 * 상태 패턴
 * VendingMachineState.java
 * 자판기의 상태가 제공해야 하는 interface
 * State Driven Transition (상태 중심 전이)
 * 필요한 메소드만 재정의하도록 default 빈메소드로 정의
 */
public interface VendingMachineState {
	default void insertCash(VendingMachine vendingMachine, Currency currency, int amount) {};
	default void selectItem(VendingMachine vendingMachine, Item item) throws ChangeNotAvailableException {};
	default void cancel(VendingMachine vendingMachine) {};
	default void dispenseItem(VendingMachine vendingMachine, Item item) {};
}
