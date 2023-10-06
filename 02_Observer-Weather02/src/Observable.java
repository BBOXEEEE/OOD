import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file Observevable.java
 * 관찰자 패턴: Head First Pattern 예제
 * 관찰자 패턴: 관찰 대상이 반드시 제공해야 하는 메소드를 구현
 * notifyObservers는 외부에서 필요한 메소드가 아니므로
 * 접근 권한을 protected로 설정함
 */
public class Observable implements Subject {
	private List<Observer> observers = new ArrayList<>();
	
	@Override
	public void registerObserver(Observer o) {
		observers.add(Objects.requireNonNull(o));
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);  
	}

	// push
	protected void notifyObservers(Object... data) {
		 observers.forEach(o->o.update(data));
	}
	
	// pull
	protected void notifyObservers(Subject subject) {
		 observers.forEach(o->o.update(subject));
	}

}
