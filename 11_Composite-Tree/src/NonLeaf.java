import java.util.ArrayList;
import java.util.Iterator;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년 2학기
 * @author 김상진
 * 합성 패턴: 중간 노드
 * 일반 트리 구현: 각 트리의 노드는 특정 타입의 레이블을 가지고 있음
 */
public class NonLeaf<T> extends Node<T> {
	private ArrayList<Node<T>> childs = new ArrayList<>(); 
	public NonLeaf(T label){
		super(label); 
	}
	public int numberOfChilds(){
		return childs.size();
	}
	@Override public boolean equals(Object other) {
		if(!super.equals(other)) return false;
		NonLeaf<?> node = (NonLeaf<?>)other;
		return childs.equals(node.childs);
	}
	@Override
	public void add(Node<T> node) {
		childs.add(node);
	}
	@Override
	public void remove(Node<T> node) {
		childs.remove(node);
	}
	@Override
	public Node<T> getChild(int index) {
		if(index >= 0 && index < childs.size())
			return childs.get(index);
		else throw new IndexOutOfBoundsException("해당 색인에 해당되는 노드가 없음");
	}
	@Override public Iterator<Node<T>> iterator() {
		return new TreeIteratorDFS<T>(childs.iterator());
		//return new TreeIteratorBFS<T>(childs.iterator());
		//return new BFSIterator<T>(this);
		//return new AnotherBFSIterator<T>(this);
		//return new DFSIterator<T>(this);
	}
	@Override protected Iterator<Node<T>> childIterator(){
		return childs.iterator();
	}
	@Override
	public String list() {
		String output = getLabel()+"\n";
		indent += " ".repeat(5);
		for(var node: childs){
			output += indent + node.list();
		}
		indent = indent.substring(0,indent.length()-5);
		return output;
	}
}
