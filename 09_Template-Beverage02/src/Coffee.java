/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * Head First Design Pattern: 템플릿 패턴, 커피 예제  
 * @file Coffee.java: 커피
 */
public class Coffee extends CaffeineBeverage{
	public void prepareRecipe(){
		boilWater();
		brewCoffeeGrinds();
		pourInCup();
		addSugarAndMilk();
	}
	private void brewCoffeeGrinds(){
		System.out.println("커피를 내림");
	}
	private void addSugarAndMilk(){
		System.out.println("밀크와 설탕 추가");
	}
}
