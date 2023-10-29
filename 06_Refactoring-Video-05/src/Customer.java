import java.util.ArrayList;
import java.util.Objects;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * Customer 클래스: 고객 대여정보
 * - 대여목록 출력 데이터 생성
 * Rental에 있는 메소드를 Movie로 이동한 버전,  
 */
public class Customer {
	private String name;
	private ArrayList<Rental> rentals = new ArrayList<>();
	public Customer(String name){
		this.name = Objects.requireNonNull(name);
	}
	public String getName() {
		return name;
	}
	public void addRental(Rental rental){
		rentals.add(Objects.requireNonNull(rental));
	}
	/*
	public String statement(){
		int totalprice = 0;
		int frequentRentalPoints = 0;
		String result = String.format("고객 %s님의 대여목록:\n", name);
		for(Rental rental: rentals){
			//Replace Temp with Query
			//int thisprice = rental.getCharge();
			frequentRentalPoints += rental.getFrequentRentalPoints();
			result += String.format("\t%s\t%,d원\n", 
				rental.getMovie().getTitle(), rental.getCharge());
			totalprice += rental.getCharge();
		}
		result += String.format("총금액: %,d원\n", totalprice);
		result += String.format("적립포인트: %d점\n", frequentRentalPoints);
		return result;
	}
	*/
	// 고객의 대여목록 출력 데이터 생성
	// Replace Temp with Query
	// 메소드의 응집성과 효율성 간 상충 
	public String statement(){
		String result = String.format("고객 %s님의 대여목록:\n", name);
		for(Rental rental: rentals)
			result += String.format("\t%s\t%,d원\n", 
				rental.getMovie().getTitle(), rental.getCharge());
		result += String.format("총금액: %,d원\n", getTotalCharge());
		result += String.format("적립포인트: %d점\n", getTotalFrequentRentalPoints());
		return result;
	}
	
	private int getTotalCharge() {
		int totalPrice = 0;
		for(Rental rental: rentals)
			totalPrice += rental.getCharge();
		return totalPrice;
	}
	
	private int getTotalFrequentRentalPoints() {
		int frequentRentalPoints = 0;
		for(Rental rental: rentals)
			frequentRentalPoints += rental.getFrequentRentalPoints();
		return frequentRentalPoints;
	}
}
