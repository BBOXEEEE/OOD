import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 김상진
 * @file Beverage.java
 * 첨가물을 ArrayList에 유지하는 형태 (전략 패턴 아님)
 */
public abstract class Beverage{
	private String description = "이름없는 음료";
	private List<Condiment> condiments = new ArrayList<>();
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		/*
		String cList = "";
		for(Condiment c: condiments) cList += c.getDescription()+", ";
		cList = cList.substring(0,cList.length()-2); 
		*/
		String cList = condiments.stream()
			.sorted(Comparator.comparing(Condiment::getDescription))
			.map(c->c.getDescription())
			.collect(Collectors.joining(", "));
		return description+", "+cList;
	}
	// 필요하면 여기서 다양한 첨가물의 제한 요구사항을 구현할 수 있음
	public void addCondiment(Condiment condiment){
		condiments.add(condiment);
	}
	public int cost(){
		/*
		int total = 0;
		for(Condiment c: condiments) total += c.cost(); 
		 */
		return condiments.stream()
				.map(c -> c.cost()).reduce(0, Integer::sum);
	}
}
