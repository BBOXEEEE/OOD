/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2023도 2학기 
 * 리펙토링
 * Rental 클래스: 대여정보 
 * @author 김상진 
 */
public class Rental {
	private Movie movie;
	private int daysRented;
	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		if(daysRented < 1) throw new IllegalArgumentException("대여기간은 1이상이어야 함");
		this.daysRented = daysRented;
	}
	public String getMovieTitle() {
		return movie.getTitle();
	}
	public int getDaysRented() {
		return daysRented;
	}
	public int getCharge(){
		return movie.getCharge(daysRented);
	}
	public int getFrequentRentalPoints(){
		return movie.getFrequentRentalPoints(daysRented);
	}
}
