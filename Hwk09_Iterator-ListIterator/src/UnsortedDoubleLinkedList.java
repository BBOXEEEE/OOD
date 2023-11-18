import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 2019136056 박세현
 * 이중 연결구조 기반 비정렬 범용 리스트
 */
public class UnsortedDoubleLinkedList<T> implements Iterable<T> {
	private static class Node<T>{
		private T item = null;
		private Node<T> prev = null;
		private Node<T> next = null;
		public Node(T item) {
			this.item = item;
		}
	}
	private class LinkedListIterator implements ListIterator<T>{
		@SuppressWarnings("unused")
		private Node<T> curr = head;
		private int index = 0;
		private int prev = -1;
		
		@Override
		public boolean hasNext() {
			return curr!=null;
		}
		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T ret = curr.item;
			curr = curr.next;
			prev = index++;
			return ret;
		}
		@Override
		public boolean hasPrevious() {
			return curr!=null && curr.prev!=null;
		}
		@Override
		public T previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			if (curr == null) curr = tail;
			else curr = curr.prev;
			prev = --index;
			return curr.item;
		}
		@Override
		public int nextIndex() {
			return index;
		}
		@Override
		public int previousIndex() {
			return index-1;
		}
		@Override
		public void remove() {
			if (prev < 0) {
				throw new IllegalStateException();
			}
			Node<T> node = head;
			for (int i=0; i<prev-1; ++i)
				node = node.next;
			if (prev == 0) {
				popFront();
				--index;
			}
			else if (node == tail) {
				popBack();
				--index;
			}
			else {
				node.next = curr.next;
				curr.next.prev = node;
				curr = node.next;
				--size;
			}
			prev = -1;
		}
		@Override
		public void set(T e) {
			if (prev < 0) {
				throw new IllegalStateException();
			}
			curr.prev.item = e;
			prev = -1;
		}
		@Override
		public void add(T e) {
			Node<T> node = new Node<>(e);
			if (curr == head) pushFront(e);
			else if (curr == null) pushBack(e);
			else {
				curr.prev.next = node;
				node.prev = curr.prev;
				node.next = curr;
				curr.prev = node;
				++size;
			}
			++index;
			prev = -1;
		}		
	}
	
	private Node<T> head = null;
	private Node<T> tail = null;
	private int size = 0;
	
	public boolean isFull() {
		return false;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public int size() {
		return size;
	}
	
	public T get(int index) {
		if(index < 0 || index >= size) 
			throw new IndexOutOfBoundsException();
		Node<T> curr = head;
		for(int i = 0; i < index; ++i) {
			curr = curr.next;
		}
		return curr.item;
	}

	public void pushFront(T item) {
		Node<T> newNode = new Node<>(item);
		newNode.next = head;
		if(head != null) head.prev = newNode;
		else tail = newNode;
		head = newNode;
		++size;
	}
	
	public T popFront() {
		if(isEmpty()) throw new IllegalStateException();
		Node<T> popNode = head;
		head = head.next;
		if(head != null) head.prev = null;
		else tail = null;
		--size;
		return popNode.item;
	}
	
	public T peekFront() {
		if(isEmpty()) throw new IllegalStateException();
		return head.item;
	}
	
	public void pushBack(T item) {
		Node<T> newNode = new Node<>(item);
		if(tail == null) head = tail = newNode;
		else{
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		++size;
	}
	
	public T popBack() {
		if(isEmpty()) throw new IllegalStateException();
		Node<T> popNode = tail;
		tail = tail.prev;
		if(tail != null) tail.next = null;
		else head = null;
		--size;
		return popNode.item;
	}
	
	public T peekBack() {
		if(isEmpty()) throw new IllegalStateException();
		return tail.item;
	}
	
	@SuppressWarnings("unchecked")
	public void pushBackAll(T... items) {
		for(var item: items) pushBack(item);
	}
	
	public boolean find(T item) {
		if(isEmpty()) return false;
		Node<T> curr = head;
		while(curr != null) {
			if(curr.item.equals(item)) return true;
			curr = curr.next;
		}
		return false;
	}
	
	public void removeFirst(T item) {
		if(isEmpty()) return;
		Node<T> dummy = new Node<>(null);
		dummy.next = head;
		head.prev = dummy;
		Node<T> curr = head;
		while(curr != null) {
			if(curr.item.equals(item)) {
				curr.prev.next = curr.next;
				if(curr.next != null) curr.next.prev = curr.prev;
				if(curr == tail) tail = tail.prev;
				--size;
				break;
			}
			else curr = curr.next;
		}
		head = dummy.next;
		if(head == null) tail = null;
		else head.prev = null;
	}
	
	@Override public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	
	public ListIterator<T> listIterator() {
		return new LinkedListIterator();
	}
}
