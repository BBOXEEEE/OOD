import java.util.concurrent.ThreadLocalRandom;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * 프로토타입 패턴: Dungeons & Dragons 
 * HeroTest.java: 테스트 프로그램
 */
public class HeroTest {
	public static void fight(Hero attacker, Hero defender) {
		int dice = ThreadLocalRandom.current().nextInt(20)+1;
		int attackPoint = dice+attacker.getAttackModifier();
		int ac = defender.getArmorClass();
		System.out.printf("주사위: %d, 공격 포인트: %d, 상대방 AC: %d%n",
			dice, attackPoint, ac);
		if(dice == 1) return;
		if(dice == 20 || attackPoint > ac) {
			int damage = attacker.getWeapon().damage();
			System.out.printf("damage = %d%n", damage);
			defender.updateHP(-damage);
		}
	}
	
	public static void main(String[] args) {
		HeroFactory heroFactory = new HeroFactory();
		Hero hero1 = heroFactory.getHero(HeroType.Archer);
		Hero hero2 = heroFactory.getHero(HeroType.Paladin);
		Hero hero3 = heroFactory.getHero(HeroType.Thief);;
		System.out.println(hero1);
		System.out.println(hero2);
		System.out.println(hero3);
		
		fight(hero1, hero2);
		System.out.println(hero1);
		System.out.println(hero2);
		fight(hero2, hero1);
		System.out.println(hero1);
		System.out.println(hero2);
	}

}
