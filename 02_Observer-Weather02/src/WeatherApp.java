/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file WeatherApp.java 
 * 관찰자 패턴: Head First Pattern 예제 + 축구 진행 상황 알림
 * 날씨 응용 테스트 프로그램
 */
public class WeatherApp {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay();
		weatherData.registerObserver(currentConditionDisplay);
		weatherData.registerObserver(new StatisticDisplay());
		
		weatherData.setMeasurement(30, 65, 30.4f);
		weatherData.measurementChanged();
		weatherData.setMeasurement(28, 55, 29.2f);
		weatherData.measurementChanged();
		weatherData.setMeasurement(29, 50, 30.8f);
		weatherData.measurementChanged();
		weatherData.setMeasurement(28, 55, 30.2f);
		weatherData.measurementChanged();
		weatherData.setMeasurement(31, 55, 29.2f);
		weatherData.measurementChanged();
		weatherData.setMeasurement(30, 60, 28.2f);
		weatherData.measurementChanged();
		weatherData.removeObserver(currentConditionDisplay);
		
		SoccerServer server = new SoccerServer();
		SoccerObserver client1 = new SoccerObserver();

		server.registerObserver(client1);

		server.updateScore("Korea 1: Brazil 0");
		server.updateScore("Korea 2: Brazil 1");
	}

}
