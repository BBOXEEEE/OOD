/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 2019136056 박세현
 * @file Beverage.java
 * 장식패턴에서 장식대상 추상클래스
 * 과제 2. 생성 메소드에 장식 제한 기능 추가
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
	//public Beverage(){}
	protected Beverage(){}
	// 장식대상과 장식자의 생성자를 protected로 한 이유는? private으로 할 수 없는 이유는?
}