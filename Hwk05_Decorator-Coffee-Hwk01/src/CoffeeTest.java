/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 2019136056 박세현
 * @file CoffeeTest.java
 * 테스트 프로그램
 */
public class CoffeeTest {
	public static void main(String[] args) {
		Beverage beverage = new HouseBlend();
		beverage = new Mocha(beverage);
		beverage = new Whip(beverage);
		beverage = new Mocha(beverage);
		System.out.printf("%s: %,d원%n", 
			beverage.getDescription(), beverage.cost());
		
		beverage = new DarkRoast();
		beverage = new Mocha(beverage);
		beverage = new Milk(beverage);
		System.out.printf("%s: %,d원%n", 
			beverage.getDescription(), beverage.cost());
		
		// equals method test
		Beverage houseBlend1 = new HouseBlend();
		houseBlend1 = new Mocha(houseBlend1);
		houseBlend1 = new Whip(houseBlend1);
		houseBlend1 = new Milk(houseBlend1);
		
		Beverage houseBlend2 = new HouseBlend();
		houseBlend2 = new Whip(houseBlend2);
		houseBlend2 = new Milk(houseBlend2);
		houseBlend2 = new Mocha(houseBlend2);
		
		System.out.println(houseBlend1.equals(houseBlend2));
	}
}
