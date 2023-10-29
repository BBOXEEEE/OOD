import java.util.concurrent.ThreadLocalRandom;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * 프로토타입 패턴: Dungeons & Dragons 
 * Weapon: 모든 무기를 아우르는 추상 타입
 */
public abstract class Weapon {
	private final int bound;
	public Weapon(int bound) {
		this.bound = bound;
	}
	public int damage() {
		return ThreadLocalRandom.current().nextInt(bound) + 1;
	}
	public abstract int weight();
}

// 창
class Lance extends Weapon{
	public Lance() {
		super(12);
	}
	@Override public int weight() {
		return 6;
	}
}

// 단검
class Dagger extends Weapon{
	public Dagger() {
		super(4);
	}
	@Override public int weight() {
		return 1;
	}
}

// 큰활
class Longbow extends Weapon{
	public Longbow() {
		super(8);
	}
	@Override public int weight() {
		return 2;
	}
}

// 손도끼
class HandAxe extends Weapon{
	public HandAxe() {
		super(6);
	}
	@Override public int weight() {
		return 2;
	}
}