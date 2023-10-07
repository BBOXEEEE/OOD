/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 2019136056 박세현
 * @file Beverage.java
 * 장식패턴에서 장식대상 추상클래스
 * 과제 1. equals 메소드 재정의
 */
public abstract class Beverage{
	private String description = "이름없는 음료";
	// 이 메소드의 문제점???
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return description;
	}
	public abstract int cost();
	
	public boolean equals(Beverage other) {
		if(this.getClass() != other.getClass()) return false;
		return true;
	}
}
