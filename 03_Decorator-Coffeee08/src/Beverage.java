/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 김상진
 * @file Beverage.java
 * 장식패턴에서 장식대상 추상클래스
 * 실습 4. 장식된 decoratee 얻는 메소드 추가 (클래스 정보 반환)
 */
public abstract class Beverage{
	private String description = "이름없는 음료";
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return description;
	}
	public abstract int cost();
	public Class<? extends Beverage> getCoffee(){
		return this.getClass();
	}
}
