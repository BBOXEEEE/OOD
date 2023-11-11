/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론
 * @version 2023년도 2학기
 * @author 김상진
 * Head First Design Pattern
 * TurkeyClassAdapter: 클래스 어댑터, 기본적으로 양방향으로 사용 가능
 * Duck이 interface가 아니면 이 형태가 가능하지 않음
 * Duck이 클래스인 경우에는 어댑터 패턴을 위한 interface의 정의가 필요함
 */
public class TurkeyClassAdapter extends BlueSlateTurkey implements Duck {
	@Override
	public void quack() {
		gobble();
	}
	@Override
	public void fly() {
		for(int i = 0; i < 5; ++i) super.fly();
	}
}
