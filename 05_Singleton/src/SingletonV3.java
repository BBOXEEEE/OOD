/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 김상진
 * @file SingletonV3.java
 * 싱글톤 패턴
 * 내부 클래스 사용. lazy instantiation
 * 다중쓰레드 환경 문제 없음
 */
public class SingletonV3 {
	private int count = 0;
	private static class SingletonHolder{
		private static final SingletonV3 unique = new SingletonV3();
	}
	// 생성자는 반드시 private이어야 생성을 제한할 수 있음
	private SingletonV3() {}
	public static SingletonV3 getInstance() {
		return SingletonHolder.unique;
	}
	
	public void increase() {
		++count;
	}
	public int getCount() {
		return count;
	}
}
