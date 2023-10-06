/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 김상진
 * @file Whip.java
 * 크림 첨가물
 */
public class Whip implements Condiment {
	@Override
	public String getDescription() {
		return "크림";
	}
	@Override
	public int cost() {
		return 500;
	}
}
