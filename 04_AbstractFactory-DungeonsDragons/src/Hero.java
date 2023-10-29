import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file Hero.java
 * 추상 팩토리 패턴: Dungeons & Dragons 
 * Hero: 영웅 클래스 (역할: 추상 팩토리의 클라이언트)
 */
public class Hero {
	private int hp = 10;
	private int strength;
	private int dexterity;
	private Weapon weapon;
	private Armor armor;
	public Hero(HeroBearingFactory bearingFactory) {
		weapon = bearingFactory.createWeapon();
		armor = bearingFactory.createArmor();
		strength = determineAbilityScore();
		dexterity = determineAbilityScore();
	}
	
	// 6면 주사위를 4개 던져 가장 높은 3개의 합
	private int determineAbilityScore() {
		int[] dices = new int[4];
		for(int i = 0; i < dices.length; ++i)
			dices[i] = ThreadLocalRandom.current().nextInt(6) + 1;
		Arrays.sort(dices);
		int sum = 0;
		for(int i = 1; i < dices.length; ++i)
			sum += dices[i];
		return sum;
	}
	
	// 능력에 따른 가산점 계산
	private int computeModifier(int ability) {
		 int modifier = ability-10;
		 if(modifier<0) --modifier;
		 return modifier/2;
	}
	
	public int getAttackModifier() {
		return computeModifier(strength);
	}
	
	public int getArmorClass() {
		return armor.armorClass(computeModifier(dexterity));
	}
	
	public void updateHP(int point) {
		hp += point;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}
	
	public Armor getArmor() {
		return armor;
	}
	
	//debug
	@Override
	public String toString() {
		return String.format("HP: %d, Strength: %d, Dexterity: %d, Weapon: %s, Armor: %s",
			hp, strength, dexterity, weapon.getClass().getName(), armor.getClass().getName());
	}
}
