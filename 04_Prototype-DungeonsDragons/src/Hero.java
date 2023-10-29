import java.util.Objects;

/**
 * @file Hero.java
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * 프로토타입 패턴: Dungeons & Dragons 
 * Hero: 영웅 클래스 (구체적 프로토타입)
 */
public class Hero implements Cloneable{
	private int hp = 10;
	private int strength;
	private int dexterity;
	private Weapon weapon; 	// 불변 객체
	private Armor armor;	// 불변 객체
	protected Hero(Weapon weapon, Armor armor) {
		this.weapon = Objects.requireNonNull(weapon);
		this.armor = Objects.requireNonNull(armor);
	}
	
	protected void initAbilities(int strength, int dexterity) {
		this.strength = strength;
		this.dexterity = dexterity;
	}
	
	// 능력에 따른 가산점 계산
	private int computeModifier(int ability) {
		 int modifier = ability - 10;
		 if(modifier < 0) --modifier;
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
	
	@Override public Hero clone() throws CloneNotSupportedException{
		return (Hero)super.clone();
	}
	
	//debug
	@Override
	public String toString() {
		return String.format("HP: %d, Strength: %d, Dexterity: %d, Weapon: %s, Armor: %s",
			hp, strength, dexterity, weapon.getClass().getName(), armor.getClass().getName());
	}
}
