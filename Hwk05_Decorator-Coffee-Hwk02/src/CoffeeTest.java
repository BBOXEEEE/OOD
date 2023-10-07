/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 2019136056 박세현
 * @file CoffeeTest.java
 * 생성 메소드 추가 버전
 * 인자로 클래스 이름과 같은 문자열을 제공해야 함
 * 한글을 사용하고 싶으면 별도 매핑 테이블이 필요함
 */

class Dog{
}

public class CoffeeTest {
	public static void main(String[] args) {
		// 장식 제한 규칙 추가
		BeverageFactory.addAdditionRestriction("Whip", 1);
		BeverageFactory.addAdditionRestriction("Milk", 1);
		BeverageFactory.addAdditionRestriction("Mocha", 2);
		BeverageFactory.addCoffeeRestriction("Mocha", "DarkRoast");

		try {
			// 정상적으로 동작
			Beverage beverage 
			= BeverageFactory.createCoffee("HouseBlend", "Mocha", "Whip", "Mocha");
			System.out.printf("%s: %,d원%n", 
					beverage.getDescription(), beverage.cost());

			// Milk or Whip은 1회까지 첨가 가능, Mocha는 2회까지 첨가 가능
			beverage
			= BeverageFactory.createCoffee("HouseBlend", "Milk", "Mocha", "Milk");
			//	= BeverageFactory.createCoffee("DarkRoast", "Whip", "Milk", "Whip");
			//	= BeverageFactory.createCoffee("HouseBlend", "Milk", "Mocha", "Mocha", "Mocha");
			System.out.printf("%s: %,d원%n", 
					beverage.getDescription(), beverage.cost());

			// DarkRoast는 Mocha 장식 제한
			beverage 
				= BeverageFactory.createCoffee("DarkRoast", "Milk", "Mocha");
			System.out.printf("%s: %,d원%n", 
					beverage.getDescription(), beverage.cost());
			
			// 예외 처리
			// beverage 
			//	= BeverageFactory.createCoffee("Dog", "Milk", "Mocha");
			//	= Beverage.createCoffee("Mocha", "Milk", "Mocha");
			//	= Beverage.createCoffee("DarkRoast", "DarkRoast", "Mocha");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
