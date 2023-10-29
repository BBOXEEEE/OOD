/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 김상진
 * @file SingletonV4.java
 * 싱글톤 패턴
 * 열거형 사용. lazy instantiation
 * 다중쓰레드 환경 문제 없음
 */
public enum SingletonV4 {
	UNIQUE;
	
	private int count = 0;
	public void increase() {
		++count;
	}
	public int getCount() {
		return count;
	}
}
