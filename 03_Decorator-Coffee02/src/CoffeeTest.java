/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 김상진
 * @file CoffeeTest.java
 * 테스트 프로그램
 * 장식패턴을 사용하지 않고 커피 첨가물 추가에 따른 가격 계산
 */
public class CoffeeTest {
	public static void main(String[] args) {
		Beverage beverage = new HouseBlend();
		beverage.addCondiment(new Mocha());
		beverage.addCondiment(new Whip());
		beverage.addCondiment(new Mocha());
		System.out.printf("%s: %,d원%n", 
			beverage.getDescription(), beverage.cost());
		
		beverage = new DarkRoast();
		beverage.addCondiment(new Milk());
		beverage.addCondiment(new Mocha());
		System.out.printf("%s: %,d원%n", 
			beverage.getDescription(), beverage.cost());
	}
}
