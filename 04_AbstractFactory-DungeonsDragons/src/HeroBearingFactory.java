/**
 * @file HeroBearingFactory.java
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2022년도 2학기 
 * @author 김상진
 * 추상 팩토리 패턴: Dungeons & Dragons 
 * HeroBearingFactory: 추상 팩토리
 * 영웅의 종류마다 기본적으로 가지는 갑옷과 무기가 있음
 */
public abstract class HeroBearingFactory {
	abstract Weapon createWeapon();
	abstract Armor createArmor();
}

class ArcherBearingFactory extends HeroBearingFactory{
	@Override
	Weapon createWeapon() {
		return new Longbow();
	}
	@Override
	Armor createArmor() {
		return new LeatherArmor();
	}
}

class PaladinBearingFactory extends HeroBearingFactory{
	@Override
	Weapon createWeapon() {
		return new Lance();
	}
	@Override
	Armor createArmor() {
		return new ChainMail();
	}
}

class ThiefBearingFactory extends HeroBearingFactory{
	@Override
	Weapon createWeapon() {
		return new Dagger();
	}
	@Override
	Armor createArmor() {
		return new LeatherArmor();
	}
}