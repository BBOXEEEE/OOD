/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file SoccerObserver.java
 * 관찰자 패턴: 구체적 관찰자
 * pull 방법
 */
public class SoccerObserver implements Observer{

	@Override
	public void update(Object... data) {
		if(data.length == 1 && data[0].getClass() == SoccerServer.class) {
			SoccerServer server = (SoccerServer)data[0];
			System.out.println(server.getCurrentScore());
		}
	}

}
