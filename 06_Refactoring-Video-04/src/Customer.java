import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진
 * Customer 클래스: 고객 대여정보
 * - 대여목록 출력 데이터 생성
 */
public class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<>();
	public Customer(String name){
		this.name = Objects.requireNonNull(name);
	}
	public String getName() {
		return name;
	}
	public void addRental(Rental rental){
		rentals.add(Objects.requireNonNull(rental));
	}
	// 대여목록 출력 데이터를 생성하고, 대여금액과 적립금액을 계산함 // 3가지 기능 수행
	public String statement(){
		int totalprice = 0;
		int frequentRentalPoints = 0;
		String result = String.format("고객 %s님의 대여목록:\n", name);
		for(Rental rental: rentals){
			//Replace Temp with Query
			//int thisprice = rental.getCharge();
			//Eclipse-shortcut: Command-Alt-I
			frequentRentalPoints += rental.getFrequentRentalPoints(); // 총 적립포인트
			result += String.format("\t%s\t%,d원\n", 
					rental.getMovie().getTitle(), rental.getCharge()); // 각 대여마다 출력정보 
			totalprice += rental.getCharge(); // 총 대여금액 
		}
		result += String.format("총금액: %,d원\n", totalprice);
		result += String.format("적립포인트: %d점\n", frequentRentalPoints);
		return result;
	}
	/*
	private int computeFrequentRentalPoints(Rental rental) {
		int frequentRentalPoint = 100;
		if((rental.getMovie().getPriceCode() == PriceCode.NEW_RELEASE) &&
			rental.getDaysRented() > 1)
			frequentRentalPoint += 100;
		return frequentRentalPoint;
	}
	*/
}
