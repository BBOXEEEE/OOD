/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file CurrentConditionDisplay.java
 * 관찰자 패턴: Head First Pattern 예제
 * 관찰자 패턴: 구체적 관찰자
 * 최신 정보를 보여주는 응용
 */
public class CurrentConditionDisplay implements Observer {
	private float temperature;
	private float humidity;
	private float pressure;

	public void display() {
		System.out.println("===최신 날씨 정보===");
		System.out.printf("현재온도: %.2f%n", temperature);
		System.out.printf("현재습도: %.2f%n", humidity);
		System.out.printf("현재기압: %.2f%n", pressure);
	}

	@Override
	public void update(Object... data) {
		if(data.length != 3) throw new IllegalArgumentException();
		this.temperature = (Float)data[0];
		this.humidity = (Float)data[1];
		this.pressure = (Float)data[2];
		display();
	}
}
