/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * Head First Design Pattern: 템플릿 패턴, 커피 예제  
 * @file CaffeineBeverage.java: 음료
 */
public abstract class CaffeineBeverage {
	public abstract void prepareRecipe();
	protected void boilWater(){
		System.out.println("물을 끓임");
	}
	protected void pourInCup(){
		System.out.println("컵에 따르다");
	}
}
