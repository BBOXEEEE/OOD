import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @file HeroFactory.java
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * 프로토타입 패턴: Dungeons & Dragons 
 * 영웅의 종류마다 기본적으로 가지는 갑옷과 무기가 있음
 * 이들을 갖춘 영웅 객체를 생성하여 유지한 다음 이들을 복제하여 영웅을 생성함
 */

public class HeroFactory {
	private Map<HeroType, Hero> registry = new EnumMap<>(HeroType.class);
	{
		registry.put(HeroType.Archer, new Hero(new Longbow(), new LeatherArmor()));
		registry.put(HeroType.Paladin, new Hero(new Lance(), new ChainMail()));
		registry.put(HeroType.Thief, new Hero(new Dagger(), new LeatherArmor()));
	}
	
	// 6면 주사위를 4개 던져 가장 높은 3개의 합
	private int computeAbilityScore() {
		int[] dices = new int[4];
		for(int i = 0; i < dices.length; ++i)
			dices[i] = ThreadLocalRandom.current().nextInt(6)+1;
		Arrays.sort(dices);
		int sum = 0;
		for(int i = 1; i < dices.length; ++i)
			sum += dices[i];
		return sum;
	}
	
	public Hero getHero(HeroType type) {
		Hero hero = null;
		try {
			hero = registry.get(type).clone();
			hero.initAbilities(computeAbilityScore(), computeAbilityScore());
		}
		catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return hero;
	}
}