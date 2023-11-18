import java.util.Iterator;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년 2학기
 * @author 김상진
 * 합성 패턴
 * 반복자를 이용한 반복자 구현에서 널 객체
 */
public class NullIterator<T> implements Iterator<Node<T>> {
	@Override
	public boolean hasNext() {
		return false;
	}
	@Override
	public Node<T> next() {
		throw new UnsupportedOperationException("이것 호출되면 곤란");
	}
}
