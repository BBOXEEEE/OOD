
/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 김상진
 * @file MultiThreadTest.java
 * 싱글톤 패턴
 * 다중쓰레드의 문제점
 */

// 일반 버전
class TaskUsingV1 implements Runnable{
	@Override
	public void run() {
		SingletonV1 s = SingletonV1.getInstance();
		System.out.println(s);
	}
}

class TaskUsingV2 implements Runnable{
	@Override
	public void run() {
		SingletonV2 s = SingletonV2.unique;
		System.out.println(s);
	}
}

// inner class 활용버전
class TaskUsingV3 implements Runnable{
	@Override
	public void run() {
		SingletonV3 s = SingletonV3.getInstance();
		System.out.println(s);
	}
}

// 열거형 활용버전
class TaskUsingV4 implements Runnable{
	@Override
	public void run() {
		SingletonV4 s = SingletonV4.UNIQUE;
		System.out.println(System.identityHashCode(s));
	}
}

// double-checked locking 사용버전
class TaskUsingV6 implements Runnable{
	@Override
	public void run() {
		SingletonV6 s = SingletonV6.getInstance();
		System.out.println(s);
	}
}

class TaskUsingV7 implements Runnable{
	@Override
	public void run() {
		SingletonV7 s = SingletonV7.getInstance();
		System.out.println(s);
	}
}

public class MultiThreadTest {
	public static void main(String[] args) {
		for(int i = 0; i<10; i++) {
			Thread thread = new Thread(new TaskUsingV1());
			thread.start();
		}
	}
}
