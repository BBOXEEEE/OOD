import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
	public String statement(){
		/*
		String result = String.format("고객 %s님의 대여목록:\n", name);
		result += rentals.stream()
			.map(r->String.format("\t%s\t%,d원\n", r.getMovieTitle(), r.getCharge()))
			.collect(Collectors.joining());	
		result += String.format("총금액: %,d원\n", getTotalCharge());
		result += String.format("적립포인트: %,d점\n", getTotalFrequencyRentalPoints());
		return result;
		*/
		
		/*
		String[] outputs = new String[4];
		outputs[0] = String.format("고객 %s님의 대여목록:\n", name);
		outputs[1] = rentals.stream()
			.map(r->String.format("\t%s\t%,d원\n", r.getMovieTitle(), r.getCharge()))
			.collect(Collectors.joining());	
		outputs[2] = String.format("총금액: %,d원\n", getTotalCharge());
		outputs[3] = String.format("적립포인트: %,d점\n", getTotalFrequencyRentalPoints());
		StringBuilder result = new StringBuilder(Arrays.stream(outputs).mapToInt(s->s.length()).sum());
		Arrays.stream(outputs).forEach(s->result.append(s));
		return result.toString();
		*/
		
		String[] outputs = new String[4];
		outputs[0] = String.format("고객 %s님의 대여목록:\n", name);
		outputs[1] = rentals.stream()
			.map(r->String.format("\t%s\t%,d원\n", r.getMovieTitle(), r.getCharge()))
			.collect(Collectors.joining());	
		outputs[2] = String.format("총금액: %,d원\n", getTotalCharge());
		outputs[3] = String.format("적립포인트: %,d점\n", getTotalFrequencyRentalPoints());
		return String.join("",outputs);
	}
	private int getTotalCharge(){
		return rentals.stream().mapToInt(Rental::getCharge).sum();
	}
	private int getTotalFrequencyRentalPoints(){
		return rentals.stream().mapToInt(Rental::getFrequentRentalPoints).sum();
	}
}
	
