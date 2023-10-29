/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * 리펙토링
 * PriceCode 열거형: 대여금과 적립금 계산 전략
 */
public enum PriceCode {
	CHILDRENS{
		@Override public int getCharge(int daysRented) {
			return (daysRented > 3)? 1500 + (daysRented - 3) * 1500: 1500;
		}		
	}, 
	REGULAR{
		@Override public int getCharge(int daysRented) {
			return (daysRented > 2)? 2000 + (daysRented - 2) * 1500: 2000;
		}		
	}, 
	NEW_RELEASE{
		@Override public int getCharge(int daysRented) {
			return daysRented * 2000;
		}
		@Override public int getFrequentRentalPoints(int daysRented){
			return daysRented > 1? 200: super.getFrequentRentalPoints(daysRented);
		}		
	};
	public abstract int getCharge(int daysRented);
	public int getFrequentRentalPoints(int daysRented){
		return 100;
	}
}
