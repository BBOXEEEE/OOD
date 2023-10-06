import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 김상진
 * @file BeverageFactory.java
 * 생성 메소드가 정의되어 있는 클래스 (자바 reflaction 활용)
 * 미리 모두 필요한 클래스 정보와 생성자 정보를 map에 저장함
 */
public class BeverageFactory {
	private static Map<String, Class<? extends Beverage>> beverageClassMap = new HashMap<>();
	private static Map<String, Constructor<? extends Beverage>> beverageConstructorMap = new HashMap<>();
	private static Map<String, Class<? extends CondimentDecorator>> condimentClassMap = new HashMap<>();
	private static Map<String, Constructor<? extends CondimentDecorator>> condimentConstructorMap = new HashMap<>();
	
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
	private static Constructor<? extends CondimentDecorator> getCondimentConstructor(String condiment){
		if(!condimentConstructorMap.containsKey(condiment)) 
			throw new IllegalArgumentException("No such condiment exists: " + condiment);
		return condimentConstructorMap.get(condiment);
	}
	
	public static Beverage createCoffee(String coffee, String... addedCondiments) 
			throws Exception{
		Beverage beverage = (Beverage)getBeverageConstructor(coffee).newInstance();
		beverage = decorateBaseCoffee(beverage, addedCondiments);
		return beverage;
	}
	
	private static Beverage decorateBaseCoffee(Beverage beverage, String... addedCondiments) 
			throws Exception {
		Arrays.sort(addedCondiments);
		
		for(String condiment: addedCondiments)
			beverage = (Beverage)getCondimentConstructor(condiment).newInstance(beverage);
		return beverage;
	}
}
