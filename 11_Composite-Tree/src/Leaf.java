import java.util.Iterator;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년 2학기
 * @author 김상진
 * 합성 패턴: 단말 노드
 */
public class Leaf<T> extends Node<T> {
	public Leaf(T label) { 
		super(label);
	}
	@Override
	public void add(Node<T> node) {
		throw new UnsupportedOperationException("단말노드");
	}
	@Override
	public void remove(Node<T> node) {
		throw new UnsupportedOperationException("단말노드");
	}
	@Override
	public Node<T> getChild(int index) {
		throw new UnsupportedOperationException("단말노드");
	}
	@Override public Iterator<Node<T>> iterator() {
		return new NullIterator<T>();
	}
	@Override protected Iterator<Node<T>> childIterator() {
		return new NullIterator<T>();
	}
	@Override
	public String list() {
		return getLabel()+"\n";
	}

}
