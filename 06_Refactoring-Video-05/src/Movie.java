import java.util.Objects;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * 리펙토링
 * Movie 클래스: 영화정보
 * 영화분류: 아동, 일반, 최신  
 */
public class Movie {
	private String title;			// 영화제목
	private PriceCode priceCode;	// 영화분류코드
	public Movie(String title, PriceCode priceCode) {
		this.title = Objects.requireNonNull(title);
		setPriceCode(priceCode);
	}
	public String getTitle() {
		return title;
	}
	public PriceCode getPriceCode() {
		return priceCode;
	}
	public void setPriceCode(PriceCode priceCode) {
		this.priceCode = priceCode;
	}
	public int getCharge(int daysRented){
		return switch(priceCode){
			case REGULAR -> (daysRented > 2)? 2000 + (daysRented - 2) * 1500: 2000;
			case NEW_RELEASE -> daysRented * 2000;
			case CHILDRENS -> (daysRented > 3)? 1500 + (daysRented - 3) * 1500: 1500;
		};
	}
	public int getFrequentRentalPoints(int daysRented){
		return switch(priceCode){
			case NEW_RELEASE -> (daysRented > 1)? 200: 100;
			default -> 100;
		};
	}
}
