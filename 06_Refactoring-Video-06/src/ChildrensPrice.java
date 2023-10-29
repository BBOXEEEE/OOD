/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * 리펙토링
 * ChildrensPrice: 아동 영화 대여금과 적립금 계산 전략
 */
public class ChildrensPrice implements Price {
	// 대여금액: 아동>> 기본 1,500원, 3일 이후 대여일 x 1,500원 
	// 적립금액: 아동>> 100점
	@Override public int getCharge(int daysRented) {
		return (daysRented > 3)? 1500 + (daysRented - 3) * 1500: 1500;
	}
}
