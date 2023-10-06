/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 김상진
 * @file CoffeeTest.java
 * 생성 메소드 추가 버전
 * 인자로 클래스 이름과 같은 문자열을 제공해야 함
 * 한글을 사용하고 싶으면 별도 매핑 테이블이 필요함
 */

class Dog{
}

public class CoffeeTest {
	public static void main(String[] args) {
		try {
			Beverage beverage 
				= BeverageFactory.createCoffee("HouseBlend", "Mocha", "Whip", "Mocha");
			System.out.printf("%s: %,d원%n", 
				beverage.getDescription(), beverage.cost());
			beverage 
				= BeverageFactory.createCoffee("DarkRoast", "Milk", "Mocha");
			System.out.printf("%s: %,d원%n", 
					beverage.getDescription(), beverage.cost());
			beverage 
				= BeverageFactory.createCoffee("Dog", "Milk", "Mocha");
			//	= Beverage.createCoffee("Mocha", "Milk", "Mocha");
			//	= Beverage.createCoffee("DarkRoast", "DarkRoast", "Mocha");
			System.out.printf("%s: %,d원%n", 
					beverage.getDescription(), beverage.cost());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
