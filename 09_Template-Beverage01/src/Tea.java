/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * Head First Design Pattern: 템플릿 패턴, 커피 예제  
 * @file Tea.java: 차
 */
public class Tea {
	public void prepareRecipe(){
		boilWater();
		steepTeaBag();
		pourInCup();
		addLemon();
	}
	private void boilWater(){
		System.out.println("물을 끓임");
	}
	private void steepTeaBag(){
		System.out.println("티백을 담그다");
	}
	private void pourInCup(){
		System.out.println("컵에 따르다");
	}
	private void addLemon(){
		System.out.println("레몬 추가");
	}
}
