import java.util.Objects;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file SoccerObserver.java
 * 관찰자 패턴: 구체적 관찰 대상
 * pull 방법
 */
public class SoccerServer implements Subject{
	private Observable observable = new Observable();
	private String currentScore = null;
	@Override
	public void registerObserver(Observer o) {
		observable.registerObserver(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observable.removeObserver(o);  
	}
	
	String getCurrentScore() {
		return currentScore;
	}
	
	void updateScore(String score) {
		currentScore = Objects.requireNonNull(score);
		observable.notifyObservers(this);
	}
	
}
