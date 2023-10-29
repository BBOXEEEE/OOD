/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file PizzaFactory.java
 * 팩토리 메소드 패턴: Head First Pattern 예제
 * 피자 생성 전략 클래스
 * 생성 메소드 + 전략 패턴
 */
public class PizzaFactory {
	public Pizza createPizza(String type) {
		switch(type){
		case "치즈": return new CheesePizza();
		case "포테이토": return new PotatoPizza();
		} // 마르게리타, 하와이안, 고르곤졸라, 페퍼로니, 불고기
		return null;
	}
}
