/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file PizzaStore.java
 * 팩토리 메소드 패턴: Head First Pattern 예제
 * 피자 가게를 아우르는 추상 타입
 * 팩토리 메소드 패턴: 팩토리 추상 타입
 */
public abstract class PizzaStore {
	public final Pizza orderPizza(String type){
		Pizza pizza = createPizza(type);
		pizza.prepare(); 
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza; 
	}
	// 밖에서 직접 호출하는 메소드가 아님
	// 생성 관련 로직은 하위 클래스에 위임
	protected abstract Pizza createPizza(String type);
}
