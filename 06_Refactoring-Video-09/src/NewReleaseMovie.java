/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진
 * NewReleaseMovie 클래스: 최신 영화 
 */
public class NewReleaseMovie extends Movie {
	public NewReleaseMovie(String title) {
		super(title);
	}
	// 대여금액: 최신>> 대여일 x 2,000원
	@Override public int getCharge(int daysRented){
		return daysRented*2000;
	}
	
	// 적립금액: 최신>> 기본 100점, 2일 이상이면 추가 100점
	@Override public int getFrequentRentalPoints(int daysRented){
		return (daysRented > 1)? 200: 100;
	}
}

