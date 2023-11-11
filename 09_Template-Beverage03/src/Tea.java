/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * Head First Design Pattern: 템플릿 패턴, 커피 예제 
 * @file Tea.java: 차
 */
public class Tea extends CaffeineBeverage{
	protected void brew(){
		System.out.println("티백을 담그다");
	}
	protected void addCondiment(){
		System.out.println("레몬 추가");
	}
}
