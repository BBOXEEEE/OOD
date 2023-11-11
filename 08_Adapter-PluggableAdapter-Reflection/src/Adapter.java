import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론
 * @version 2023년도 2학기
 * @author 김상진
 * 플러그 가능 어댑터: 객체 어댑터
 * 자바의 reflection library 이용
 */
public class Adapter implements Target{
	Object adaptee;
	String cryfunc;
	public Adapter(Object adaptee, String cryfunc) {
		this.adaptee = Objects.requireNonNull(adaptee);
		this.cryfunc = Objects.requireNonNull(cryfunc);
	}
	@Override
	public void foo() {
		try {
			Class<?> adapteeClass = adaptee.getClass();
			Method cry = adapteeClass.getMethod(cryfunc);
			cry.invoke(adaptee);
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
