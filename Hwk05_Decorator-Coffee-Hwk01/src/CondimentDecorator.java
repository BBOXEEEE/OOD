import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 2019136056 박세현
 * @file CondimentDecorator.java
 * 장식패턴에서 장식자 추상 클래스
 * 과제 1. equals 메소드 재정의
 */
public abstract class CondimentDecorator extends Beverage {
	protected Beverage beverage;
	protected CondimentDecorator(Beverage beverage){
		this.beverage = beverage;
	}
	
	@Override
	public abstract String getDescription();
	
	public boolean equals(Beverage o) {
		List<String> beverage = Arrays.asList(this.getDescription().replaceAll(" ","").split(","));
		List<String> other = Arrays.asList(o.getDescription().replaceAll(" ","").split(","));
		
		if(beverage.size() != other.size()) return false;
		
		Collections.sort(beverage);
		Collections.sort(other);
		for(int i=0; i<beverage.size(); ++i)
			if(!beverage.get(i).equals(other.get(i))) return false;
		return true;
	}
}
