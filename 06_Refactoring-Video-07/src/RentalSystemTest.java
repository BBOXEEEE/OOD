import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진
 * 리펙토링
 * 테스트 프로그램
 * 버전 7. 대여금/적립금 계산 전략 열거형 하나로 구현
 */
class RentalSystemTest {
	// 대여금액: 일반>> 기본 2,000원, 2일 이후 대여일 x 1,500원
	// 대여금액: 최신>> 대여일 x 2,000원
	// 대여금액: 아동>> 기본 1,500원, 3일 이후 대여일 x 1,500원 
	// 적립금액: 일반, 아동, 최신>> 100점
	// 적립금액: 최신>> 2일 이상이면 추가 100점
	@Test
	void rentalBasicTest() {
		Customer sangjin = new Customer("김상진");
		sangjin.addRental(new Rental(new Movie("어벤져스: 엔드게임", PriceCode.REGULAR), 2));
		sangjin.addRental(new Rental(new Movie("엘리멘탈", PriceCode.CHILDRENS), 2));
		sangjin.addRental(new Rental(new Movie("아바타: 물의 길", PriceCode.NEW_RELEASE), 2));
		String expectedResult = 
			"고객 김상진님의 대여목록:\n"+
			"\t어벤져스: 엔드게임\t2,000원\n" +
			"\t엘리멘탈\t1,500원\n" +
			"\t아바타: 물의 길\t4,000원\n" +
			"총금액: 7,500원\n" +
			"적립포인트: 400점\n";
		assertEquals(sangjin.statement(), expectedResult);
	}
	
	@Test
	void rentalAdvancedTest() {
		Customer sangjin = new Customer("김상진");
		sangjin.addRental(new Rental(new Movie("어벤져스: 엔드게임", PriceCode.REGULAR), 4));
		sangjin.addRental(new Rental(new Movie("소울", PriceCode.CHILDRENS), 4));
		sangjin.addRental(new Rental(new Movie("오펜하이머", PriceCode.NEW_RELEASE), 3));
		String expectedResult = 
			"고객 김상진님의 대여목록:\n"+
			"\t어벤져스: 엔드게임\t5,000원\n" +
			"\t소울\t3,000원\n" +
			"\t오펜하이머\t6,000원\n" +
			"총금액: 14,000원\n" +
			"적립포인트: 400점\n";
		assertEquals(sangjin.statement(), expectedResult);
	}
}
