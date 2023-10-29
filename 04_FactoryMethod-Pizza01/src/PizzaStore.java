/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file PizzaStore.java
 * 팩토리 메소드 패턴: Head First Pattern 예제
 * 피자 가게: 피자를 orderPizza 메소드에서 생성함
 * 팩토리 메소드 패턴을 적용하기 전 버전
 */
public class PizzaStore {
	// type-safe 하지 않음. 이것을 극복하는 방법은?
	public Pizza orderPizza(String type){
		Pizza pizza = null;
		// 이 부분이 변할 수 있는 부분
		switch(type){
		case "치즈": pizza = new CheesePizza(); break;
		case "포테이토": pizza = new PotatoPizza(); break;
		} // 마르게리타, 하와이안, 고르곤졸라, 페퍼로니, 불고기
		// 이 부분은 변하지 않을 부분
		pizza.prepare(); 
		pizza.bake(); 
		pizza.cut();
		pizza.box();
		return pizza; 
	}
}
