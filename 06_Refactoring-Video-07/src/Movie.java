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
	private String title;
	private PriceCode priceCode;
	// 생성자 의존 주입 방식
	public Movie(String title, PriceCode priceCode) {
		this.title = Objects.requireNonNull(title);
		this.priceCode = priceCode;
	}
	public String getTitle() {
		return title;
	}
	public PriceCode getPriceCode() {
		return priceCode;
	}
	public int getCharge(int daysRented){
		return priceCode.getCharge(daysRented);
	}
	public int getFrequentRentalPoints(int daysRented){
		return priceCode.getFrequentRentalPoints(daysRented);
	}
}

