/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 김상진
 * @file SingletonV6.java
 * 싱글톤 패턴
 * volatile, synchronized 등을 이용하여 다중 쓰레드 문제 해결
 * double checked locking 기법
 */
public class SingletonV6 {
	private int count = 0;
	private volatile static SingletonV6 unique = null;
	// 생성자는 반드시 private이어야 생성을 제한할 수 있음
	private SingletonV6() {}
	public static SingletonV6 getInstance() {
		if(unique == null) 
			synchronized (SingletonV6.class) {
				if(unique == null) unique = new SingletonV6();
			}
		return unique;
	}
	public void increase() {
		++count;
	}
	public int getCount() {
		return count;
	}
}

