/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론
 * @version 2023년도 2학기
 * @author 김상진
 * Head First Design Pattern
 * TurkeyTwoWayAdapter: 양방향 어댑터
 * fly 메소드???
 * target과 adaptee가 모두 interface로 정의되어 있는 경우에만 
 * 이와 같은 형태로 구현 가능
 */
public class TurkeyTwoWayAdapter implements Turkey, Duck{
	private Turkey turkey;
	public TurkeyTwoWayAdapter(Turkey turkey) {
		this.turkey = turkey;
	}
	@Override
	public void gobble() {
		turkey.gobble();
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
