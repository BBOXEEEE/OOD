import java.util.Iterator;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년 2학기
 * @author 김상진
 * 합성 패턴: 추상 노드
 * 일반 트리 구현: 각 트리의 노드는 특정 타입의 레이블을 가지고 있음
 */
public abstract class Node<T> implements Iterable<Node<T>>{
	public static String indent = "";
	private T label;
	private boolean hasChanged = false;
	public Node(T name){
		this.label = name;
	}
	public T getLabel(){
		return label;
	}
	public boolean hasChanged(){
		return hasChanged;
	}
	public void setChanged(boolean flag){
		hasChanged = flag;
	}
	public abstract String list();
	
	@Override public boolean equals(Object other) {
		if(other == null || getClass() != other.getClass()) return false;
		if(this == other) return true;
		Node<?> node = (Node<?>)other;
		return label.equals(node.label) && hasChanged == node.hasChanged;
	}
	public int numberOfChilds(){
		return 0;
	}
	public abstract void add(Node<T> node);
	public abstract void remove(Node<T> node);
	public abstract Node<T> getChild(int index);
	public abstract Iterator<Node<T>> iterator();
	protected abstract Iterator<Node<T>> childIterator();
}
