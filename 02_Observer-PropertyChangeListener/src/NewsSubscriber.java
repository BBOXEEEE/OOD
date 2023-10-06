import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file FancyNewsSubscriber.java
 * 관찰자 패턴: java.beans.PropertyChangeSupport 활용한 관찰자 패턴의 구현
 * 역할: 관찰자
 */
public class NewsSubscriber implements PropertyChangeListener {
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("속보: "+(String)evt.getNewValue());
	} // update
}
