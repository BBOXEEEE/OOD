/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file Armor.java
 * 추상 팩토리 패턴: Dungeons & Dragons 
 * Armor: 모든 갑옷을 아우르는 추상 타입
 */
public abstract class Armor {
	public abstract int armorClass(int modifier);
	public abstract int weight();
}

// LeatherArmor 갑옷
class LeatherArmor extends Armor{
	@Override
	public int armorClass(int modifier) {
		return 11 + modifier;
	}

	@Override public int weight() {
		return 10;
	}
}

// ChainMail 갑옷
class ChainMail extends Armor{
	@Override public int armorClass(int modifier) {
		return 16;
	}
	@Override public int weight() {
		return 55;
	}
}