/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * 리펙토링
 * RegularPrice: 최신 영화 대여금과 적립금 계산 전략
 */
public class RegularPrice implements Price {
	// 대여금액: 일반>> 기본 2,000원, 2일 이후 대여일 x 1,500원
	// 적립금액: 일반>> 100점
	@Override public int getCharge(int daysRented) {
		return (daysRented > 2)? 2000 + (daysRented - 2) * 1500: 2000;
	}
}
