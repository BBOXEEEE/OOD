import java.util.List;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 2019136056 박세현 
 * @file BlackJackPlayerHand.java
 * 탬플릿 메소드 패턴
 * 블랙잭 게임에서 각 플레이어의 패 정보 유지
 */
public class BlackJackPlayerHand {
	private List<Card> cards;
	private int score = 0;
	private boolean isBlackJack = false;
	public BlackJackPlayerHand(List<Card> cards) {
		this.cards = cards;
		score = computeScore();
	}
	public void init() {
		cards.clear();
		score = 0;
		isBlackJack = false;
	}
	public List<Card> getCards(){
		return cards;
	}
	public void addCard(Card card) {
		cards.add(card);
		score = computeScore();
	}
	public int getScore() {
		return score;
	}
	public boolean isBlackJack() {
		return isBlackJack;
	}
	public boolean isBust() {
		return score > 21;
	}
	
	private int computeScore() {
		int sum = 0;
		int ace = 0;
		int faceCard = 0;
		
		for (Card card: cards) {
			if (card.number() == 1) {
				sum += 1;
				++ace;
			}
			else if (card.number() > 10) {
				sum += 10;
				++faceCard;
			}
			else {
				sum += card.number();
			}
		}
		
		while (ace > 0) {
			if ((sum + ace * 10) <= 21) sum += ace * 10;
			--ace;
		}
		
		if (sum == 21 && faceCard > 0) isBlackJack = true;
		else isBlackJack = false;
		
		return sum;
	}
	public static BlackJackGameResult determineResult(
		BlackJackPlayerHand userHand, BlackJackPlayerHand dealerHand) {
		
		// user or computer -> bust
		if (userHand.isBust() && !dealerHand.isBust()) return BlackJackGameResult.USERLOST;
		if (!userHand.isBust() && dealerHand.isBust()) return BlackJackGameResult.USERWIN;
		// user & computer -> bust
		if (userHand.isBust() && dealerHand.isBust()) return BlackJackGameResult.DRAW;
			
		// user and computer -> not bust
		if (userHand.getScore() > dealerHand.getScore()) return BlackJackGameResult.USERWIN;
		else if (userHand.getScore() < dealerHand.getScore()) return BlackJackGameResult.USERLOST;
		else {
			if (userHand.getScore() == 21) {
				if (userHand.isBlackJack && !dealerHand.isBlackJack) return BlackJackGameResult.USERWIN;
				else if (!userHand.isBlackJack && dealerHand.isBlackJack) return BlackJackGameResult.USERLOST;
				else return BlackJackGameResult.DRAW;
			}
			return BlackJackGameResult.USERLOST;
		}
	}
}
