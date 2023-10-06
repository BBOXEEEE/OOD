/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file WeatherData.java
 * 관찰자 패턴: Head First Pattern 예제
 * 관찰자 패턴: 구체적 관찰대상
 * 상속 대신에 포함 관계로 관찰 대상이 공통적으로 제공해야 하는 
 * 기능을 코드 중복 없이 구현함 
 * 자바는 단일 상속이므로 관찰 대상이 다른 클래스를 상속하는
 * 클래스이면 이 방법이 유일한 방법임
 */
public class WeatherData implements Subject {
	private Observable observable = new Observable();
	private float temperature;
	private float humidity;
	private float pressure;
	
	@Override
	public void registerObserver(Observer o) {
		observable.registerObserver(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observable.removeObserver(o);  
	}
	
	public void measurementChanged() {
		observable.notifyObservers(temperature, humidity, pressure);
	} // push
	
	public void setMeasurement(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		//measurementChanged();
	}

}
