/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론
 * @version 2023년도 2학기
 * @author 김상진
 * 플러그 가능 어댑터: 객체 어댑터
 * 서로 다른 이름으로 정의된 메소드를 모두 foo 이름을 통해 호출하고자 함
 * Cat::meow, Dog::bark, Frog::croak 
 * 실제 어댑티를 유지하는 것이 아님. 
 * 특정 객체의 특정 메소드를 호출하는 메소드를 유지함
 */
public class Adapter implements Target{
	private Target target;
	public Adapter(Target target) {
		this.target = target;
	}
	@Override
	public void foo() {
		target.foo();
	}
}
