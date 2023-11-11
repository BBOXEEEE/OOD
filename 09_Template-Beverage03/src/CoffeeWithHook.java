import java.util.Scanner;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * Head First Design Pattern: 템플릿 패턴, 커피 예제 
 * @file CoffeeWithHook.java: 훅 메소드를 가지고 있는 커피 
 */
public class CoffeeWithHook extends CaffeineBeverage{
	protected void brew(){
		System.out.println("커피를 내림");
	}
	protected void addCondiment(){
		System.out.println("밀크와 설탕 추가");
	}
	protected boolean customerWantsCondiments(){
		Scanner in = new Scanner(System.in);
		System.out.print("밀크와 설탕을 추가하시겠습니까 (y/n)? ");
		String answer = in.nextLine().toLowerCase();
		in.close();
		return answer.equals("y");
	}
}
