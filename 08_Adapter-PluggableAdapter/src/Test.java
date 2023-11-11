/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론
 * @version 2023년도 2학기
 * @author 김상진
 * 플러그 가능 어댑터: 객체 어댑터
 * 테스트 프로그램
 */
public class Test {
	public static void simulate(Target target) {
		target.foo();
	}
	
	public static void main(String[] args) {
		Dog dog = new Dog();
		Cat cat = new Cat();
		Frog frog = new Frog();
		simulate(new Adapter(dog::bark));
		simulate(new Adapter(cat::meow));
		simulate(new Adapter(frog::croak));
	}
}
