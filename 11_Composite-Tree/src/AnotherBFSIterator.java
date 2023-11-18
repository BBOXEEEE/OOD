import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년 2학기
 * @author 김상진
 * 합성 패턴
 * 큐를 이용한 너비 우선 탐색 반복자
 * 노드를 큐에 유지함
 * next에서 자식 노드를 큐에 추가함
 */
public class AnotherBFSIterator<T> implements Iterator<Node<T>> {
	Queue<Node<T>> queue = new ArrayDeque<>();
	public AnotherBFSIterator(Node<T> node){
		queue.add(node);
	}
	@Override
	public boolean hasNext() {
		return !queue.isEmpty(); 
	}
	// 단말만 반환
	@Override public Node<T> next() {
		Node<T> curr = queue.peek();
		if(curr instanceof NonLeaf) {
 			for(int i = 0; i < curr.numberOfChilds(); ++i)
 				queue.add(curr.getChild(i));
 			queue.poll();
 			return next();
 		}
		else return queue.poll();
	}
	/*
	// 모든 노드 반환
	@Override public Node<T> next() {
		Node<T> curr = queue.peek();
		if(curr instanceof NonLeaf) {
 			for(int i=0; i<curr.numberOfChilds(); i++)
 				queue.add(curr.getChild(i));
 		}
		return queue.poll();
	}
	*/
}
