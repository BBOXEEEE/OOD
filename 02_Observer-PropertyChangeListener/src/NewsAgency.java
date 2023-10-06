import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file NewsAgency.java
 * 관찰자 패턴: java.beans.PropertyChangeSupport 활용한 관찰자 패턴의 구현
 * 역할: 관찰 대상
 */
public class NewsAgency {
	private String news;

	// 관찰자 기능 구현
	private PropertyChangeSupport support = new PropertyChangeSupport(this);
	
	public NewsAgency() {}
	
	public void registerObserver(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
 
    public void removeObserver(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl); // equals 주의 
    }
 
    public void setNews(String value) {
    	// notifyobserver에 해당
        support.firePropertyChange("news", this.news, value);
        this.news = value;
    }
}
