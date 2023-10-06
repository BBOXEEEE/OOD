/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file Subject.java
 * 관찰자 패턴: Head First Pattern 예제
 * 관찰자 패턴: 관찰대상 interface
 * notifyObservers의 특징
 * - 보통 외부에서 호출해야 하는 메소드가 아님
 *  >> Subject interface에 선언하지 않을 수 있음
 * - push, pull 두 가지 형태로 구현 가능
 *  >> Subject interface에 포함할 경우 
 *     선택적으로 또 범용적으로 사용하도록 포함해야 함
 *  >> 보통 둘 중 하나만 사용할 것이므로 default로 구현하여 
 *     사용하지 않더라도 추가 조치 없이 관찰 대상을 정의할 수 있음
 *  >> 범용적으로 push를 위한 notifyObservers를 제공하기 위해
 *     Object 가변 인자를 이용함
 * - 이 버전에서는 보통 외부에서 호출하는 메소드가 아닌 
 *   notifyObservers를 Subject interface에서 제외함
 */
public interface Subject {
	void registerObserver(Observer o);
	void removeObserver(Observer o);
	// push 방법
	// default void notifyObservers(Object... data) {}
	// pull 방법
	// default void notifyObservers(Subject subject) {}
}
