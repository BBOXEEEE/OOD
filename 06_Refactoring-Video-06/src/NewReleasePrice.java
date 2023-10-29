/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * 리펙토링
 * NewReleasePrice: 최신 영화 대여금과 적립금 계산 전략 
 */
public class NewReleasePrice implements Price {
	// 대여금액: 최신>> 대여일 x 2,000원
	@Override public int getCharge(int daysRented) {
		return daysRented * 2000;
	}
	
	// 적립금액: 최신>> 기본 100점, 2일 이상이면 추가 100점
	@Override public int getFrequentRentalPoints(int daysRented){
		return daysRented > 1? 200: Price.super.getFrequentRentalPoints(daysRented);
	}
}
