
class ParentWithClone implements Cloneable{
	@Override public ParentWithClone clone() 
		throws CloneNotSupportedException{
		return (ParentWithClone)super.clone();
	}
}

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2021년도 2학기
 * @author 김상진
 * @file SingletonV5.java
 * 싱글톤 패턴
 * 다른 클래스를 상속받아 싱글톤을 구현하는 경우 clone의 문제점
 * clone할 수 없도록 주석처리된 부분을 사용해야 함
 */
public class SingletonV5 extends ParentWithClone{
	private int count = 0;
	private static SingletonV5 unique = null;
	// 생성자는 반드시 private이어야 생성을 제한할 수 있음
	private SingletonV5() {}
	public static SingletonV5 getInstance() {
		if(unique == null) unique = new SingletonV5();
		return unique;
	}
	public void increase() {
		++count;
	}
	public int getCount() {
		return count;
	}
	/*
	@Override public SingletonV5 clone() 
			throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}
	*/
}

