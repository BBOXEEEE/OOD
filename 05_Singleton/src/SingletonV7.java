/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 김상진
 * @file SingletonV7.java
 * 싱글톤 패턴
 * 다중 쓰레드 문제 해결: 메소드 동기화 
 * 불필요한 잠금 때문에 비효율적인 방법
 */
public class SingletonV7 {
	private int count = 0;
	private static SingletonV7 unique = null;
	// 생성자는 반드시 private이어야 생성을 제한할 수 있음
	private SingletonV7() {}
	public static synchronized SingletonV7 getInstance() {
		if(unique == null) unique = new SingletonV7();
		return unique;
	}
	public void increase() {
		++count;
	}
	public int getCount() {
		return count;
	}
}

