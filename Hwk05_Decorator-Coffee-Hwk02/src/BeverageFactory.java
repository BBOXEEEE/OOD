import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 2019136056 박세현
 * @file BeverageFactory.java
 * 생성 메소드가 정의되어 있는 클래스 (자바 reflaction 활용)
 * 미리 모두 필요한 클래스 정보와 생성자 정보를 map에 저장함
 */
public class BeverageFactory {
	private static Map<String, Class<? extends Beverage>> beverageClassMap = new HashMap<>();
	private static Map<String, Constructor<? extends Beverage>> beverageConstructorMap = new HashMap<>();
	private static Map<String, Class<? extends CondimentDecorator>> condimentClassMap = new HashMap<>();
	private static Map<String, Constructor<? extends CondimentDecorator>> condimentConstructorMap = new HashMap<>();
	// restrictionTable
	private static Map<String, Restriction> restrictionTable = new HashMap<>();
	
	// Class Restriction
	public static class Restriction{
		public int maxAddition = 0;
		public Set<String> exclusionList = new HashSet<>();
		
		public Restriction(int maxAddition, Set<String> exclusionList) {
			this.maxAddition = maxAddition;
			this.exclusionList = exclusionList;
		}
	} 
	
	public static void addAdditionRestriction(String decorator, int maxAddition) {
		if(restrictionTable.containsKey(decorator)) {
			Restriction curr = restrictionTable.get(decorator);
			curr.maxAddition = maxAddition;
		}
		else {
			restrictionTable.put(decorator, new Restriction(maxAddition, new HashSet<>()));
		}
	}
	
	public static void addCoffeeRestriction(String decorator, String decoratee) {
		if(restrictionTable.containsKey(decorator)) {
			Restriction curr = restrictionTable.get(decorator);
			curr.exclusionList.add(decoratee);
		}
		else {
			Set<String> exclusionList = new HashSet<>();
			exclusionList.add(decoratee);
			restrictionTable.put(decorator, new Restriction(0, exclusionList));
		}
	}
	
	public static final BeverageFactory factory = new BeverageFactory();
	
	static {
		String[] beverageList = {"DarkRoast", "HouseBlend"};
		String[] condimentList = {"Milk", "Mocha", "Whip"};
		try {
			for(var beverage: beverageList) {
				beverageClassMap.put(beverage, Class.forName(beverage).asSubclass(Beverage.class));
				beverageConstructorMap.put(beverage, beverageClassMap.get(beverage).getDeclaredConstructor());
			}
			for(var condiment: condimentList) {
				condimentClassMap.put(condiment, Class.forName(condiment).asSubclass(CondimentDecorator.class));
				condimentConstructorMap.put(condiment, condimentClassMap.get(condiment).getDeclaredConstructor(Beverage.class));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Constructor<? extends Beverage> getBeverageConstructor(String beverage){
		if(!beverageConstructorMap.containsKey(beverage)) 
			throw new IllegalArgumentException("No such beverage exists: " + beverage);
		return beverageConstructorMap.get(beverage);
	}
	private static Constructor<? extends CondimentDecorator> getCondimentConstructor(String condiment, Map<String, Integer> decoratorCountMap){
		if(!condimentConstructorMap.containsKey(condiment)) 
			throw new IllegalArgumentException("No such condiment exists: " + condiment);
		else {
			// 장식 제한이 없거나 장식 제한보다 적은 횟수로 장식
			if(restrictionTable.get(condiment).maxAddition == 0 || decoratorCountMap.get(condiment) <= restrictionTable.get(condiment).maxAddition)
				return condimentConstructorMap.get(condiment);
			else
				throw new IllegalArgumentException(condiment + " can't be decorate more than " + restrictionTable.get(condiment).maxAddition);
		}
	}
	
	public static Beverage createCoffee(String coffee, String... addedCondiments) 
			throws Exception{
		Beverage beverage = (Beverage)getBeverageConstructor(coffee).newInstance();
		beverage = decorateBaseCoffee(coffee, beverage, addedCondiments);
		return beverage;
	}
	
	private static Beverage decorateBaseCoffee(String coffee, Beverage beverage, String... addedCondiments) 
			throws Exception {
		Arrays.sort(addedCondiments);
		
		Map<String, Integer> decoratorCountMap = new HashMap<>();
		for(String condiment: addedCondiments) {
			// condiment로 장식 제한된 coffee
			if(restrictionTable.get(condiment).exclusionList.contains(coffee))
				throw new IllegalArgumentException(coffee + " can't be decorate " + condiment);
			else {
				// 장식 횟수 count
				decoratorCountMap.put(condiment, decoratorCountMap.getOrDefault(condiment, 0) + 1);
				beverage = (Beverage)getCondimentConstructor(condiment, decoratorCountMap).newInstance(beverage);
			}
		}
		return beverage;
	}
}
