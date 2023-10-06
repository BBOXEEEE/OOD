/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file Subject.java
 * 관찰자 패턴: Head First Pattern 예제
 * 관찰자 패턴: 관찰대상 interface
 */
public interface Subject {
	void registerObserver(Observer o);
	void removeObserver(Observer o);
	void notifyObservers();
}
