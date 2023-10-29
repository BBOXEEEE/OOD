import java.lang.reflect.Constructor;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 김상진
 * @file SingletonKiller.java
 * 다중 쓰레드 뿐만 아니라 자바에서는 reflection 라이브러리를 이용하여
 * 싱글톤을 무력화시킬 수 있음. 열거형 버전은 reflection을 이용하여도 가능하지 않음
 */
public class SingletonKiller {
	public static void cloneTest() {
		SingletonV5.getInstance().increase();
		System.out.println(SingletonV5.getInstance().getCount());
		try {
			SingletonV5 cloned = (SingletonV5) SingletonV5.getInstance().clone();
			cloned.increase();
			System.out.println(cloned.getCount());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		System.out.println(SingletonV5.getInstance().getCount());
	}
	
	public static void reflectionTest() {
		SingletonV1.getInstance().increase();
		System.out.println(SingletonV1.getInstance().getCount());
		try {
			Constructor<SingletonV1> constructor 
				= SingletonV1.class.getDeclaredConstructor();
			constructor.setAccessible(true);
			SingletonV1 object = (SingletonV1) constructor.newInstance();
			object.increase();
			System.out.println(object.getCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//reflectionTest();
		cloneTest();
	}
}
