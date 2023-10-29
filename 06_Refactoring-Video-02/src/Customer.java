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
	// 적립금액: 일반, 아동, 최신>> 100점
	// 적립금액: 최신>> 2일 이상이면 추가 100점
	public String statement(){
		int totalprice = 0;
		int frequentRentalPoints = 0;
		String result = String.format("고객 %s님의 대여목록:\n", name);
		for(Rental rental: rentals){
			//Eclipse-shortcut: Command-Alt-M
			int thisprice = computeRentalPrice(rental);
			frequentRentalPoints += 100;
			if((rental.getMovie().getPriceCode() == PriceCode.NEW_RELEASE) &&
				rental.getDaysRented() > 1)
				frequentRentalPoints += 100;
			result += String.format("\t%s\t%,d원\n", rental.getMovie().getTitle(), thisprice);
			totalprice += thisprice;
		}
		result += String.format("총금액: %,d원\n", totalprice);
		result += String.format("적립포인트: %d점\n", frequentRentalPoints);
		return result;
	}
	// Extract Method //
	// 사용하는 변수: 
	//	1) thisAmount: 이 과정에서 계산되는 값 => 지역변수 => 반환값
	//	2) rental: 이 과정에서 참조되는 값 => 인자
	// Extract Method할 때 함수 이름, 변수 이름을 가독성 있게
	/*
		int thisAmount = 0;
		switch(rental.getMovie().getPriceCode()){
		case REGULAR:
			thisAmount += 2000;
			if(rental.getDaysRented()>2)
				thisAmount += (rental.getDaysRented() - 2) * 1500;
			break;
		case NEW_RELEASE:
			thisAmount += rental.getDaysRented() * 2000;
			break;
		case CHILDRENS:
			thisAmount += 1500;
			if(rental.getDaysRented() > 3)
				thisAmount += (rental.getDaysRented() - 3) * 1500;
			break;
		}
	*/
	// 대여금액: 일반>> 기본 2,000원, 2일 이후 대여일 x 1,500원
	// 대여금액: 최신>> 대여일 x 2,000원
	// 대여금액: 아동>> 기본 1,500원, 3일 이후 대여일 x 1,500원 
	private int computeRentalPrice(Rental rental){
		int price = 0;
		switch(rental.getMovie().getPriceCode()){
		case REGULAR:
			price += 2000;
			if(rental.getDaysRented() > 2)
				price += (rental.getDaysRented() - 2) * 1500;
			break;
		case NEW_RELEASE:
			price += rental.getDaysRented() * 2000;
			break;
		case CHILDRENS:
			price += 1500;
			if(rental.getDaysRented() > 3)
				price += (rental.getDaysRented() - 3) * 1500;
			break;
		}
		return price;
	}
}
