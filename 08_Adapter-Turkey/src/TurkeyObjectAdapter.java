/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론
 * @version 2023년도 2학기
 * @author 김상진
 * Head First Design Pattern
 * TurkeyObjectAdapter: 객체 어댑터
 */
public class TurkeyObjectAdapter implements Duck {
	private Turkey turkey;
	public TurkeyObjectAdapter(Turkey turkey) {
		this.turkey = turkey;
	}
	@Override
	public void quack() {
		turkey.gobble();
	}
	@Override
	public void fly() {
		for(int i = 0; i < 5; ++i) turkey.fly();
	}
}
