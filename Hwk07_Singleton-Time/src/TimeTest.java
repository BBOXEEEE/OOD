
/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 2019136056 박세현 
 * Singleton
 * 객체 풀 실습 - TimeTest
 */
public class TimeTest {
	public static void main(String[] args) {
		Time t1 = Time.of(2, 10, 5);
		Time t2 = Time.of(10, 2, 5);
		Time t3 = Time.of(5, 2, 10);
		
		// 3가지 시간에 대한 값
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);
		
		Time t4 = Time.of(1, 2, 3);
		Time t5 = Time.of(1, 2, 3);
		Time t6 = Time.of(2, 2, 3);
		
		// t4 & t5 같은 객체인지
		System.out.println(t4==t5);
		System.out.println(t4==t6);
	}

}
