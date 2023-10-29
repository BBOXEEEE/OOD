import java.util.Objects;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진  
 * 리펙토링
 * Rental 클래스: 대여정보 
 */
public class Rental {
	private Movie movie;	// 대여영화
	private int daysRented;	// 대여기간
	public Rental(Movie movie, int daysRented) {
		this.movie = Objects.requireNonNull(movie);
		if(daysRented < 1) throw new IllegalArgumentException("대여기간은 1이상이어야 함");
		this.daysRented = daysRented;
	}
	public Movie getMovie() {
		return movie;
	}
	public int getDaysRented() {
		return daysRented;
	}
}
