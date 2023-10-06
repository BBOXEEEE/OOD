/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 김상진
 * @file CondimentDecorator.java
 * 장식패턴에서 장식자 추상 클래스
 */
public abstract class CondimentDecorator extends Beverage {
	protected Beverage beverage;
	
	public CondimentDecorator(Beverage beverage) {
		this.beverage = beverage;
	}
	
	public abstract String getDescription();
	
	public Class<? extends Beverage> getCoffee(){
		if(beverage instanceof CondimentDecorator)
			return beverage.getCoffee();
		else return beverage.getClass();
	}
}
