import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년 2학기
 * @author 김상진
 * 합성 패턴
 * 반복자를 이용한 합성 패턴을 위한 반복자 구현
 * 큐를 이용한 너비 우선 탐색 
 * 큐에 반복자 유지
 */
public class TreeIteratorBFS<T> implements Iterator<Node<T>> {
	Queue<Iterator<Node<T>>> queue = new ArrayDeque<>();
	public TreeIteratorBFS(Iterator<Node<T>> iterator){
		queue.add(iterator);
	}
	@Override
	public boolean hasNext() {
		if(queue.isEmpty()) return false;
		else{
			Iterator<Node<T>> iterator = queue.peek();
	 		if(iterator.hasNext()) return true;
			else{ 
				queue.poll(); 
				return hasNext(); 
			}
 		} 
	}
	@Override
	public Node<T> next() {
		Node<T> node = queue.peek().next();
		queue.add(node.childIterator());	// 단말의 경우에는 NullIterator가 add됨
		//return node;
		
		if(node instanceof NonLeaf) {
			while(!queue.peek().hasNext()) queue.poll();
		}
		return node instanceof NonLeaf? next(): node;
	}
}
