/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2023년도 2학기
 * @author 김상진
 * @file Mocha.java
 * 모카 첨가물
 */
public class Mocha implements Condiment {
	@Override
	public String getDescription() {
		return "모카";
	}
	@Override
	public int cost() {
		return 200;
	}
}
