import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 2019136056 박세현
 * 배열 기반 비정렬 범용 리스트
 */
public class UnsortedArrayList<T> implements Iterable<T> {
	private int capacity = 5;
	@SuppressWarnings("unchecked")
	private T[] items = (T[])(new Object[capacity]);
	private int size = 0;
	
	private class ArrayListIterator implements ListIterator<T>{
		private int cursor = 0;
		private int prev = -1;
		
		@Override public boolean hasNext() {
			return cursor < size;
		}
		@Override public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			prev = cursor;
			return items[cursor++];
		}
		@Override public boolean hasPrevious() {
			return cursor > 0;
		}
		@Override public T previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			prev = --cursor;
			return items[prev];
		}
		@Override public int nextIndex() {
			return cursor;
		}
		@Override public int previousIndex() {
			return cursor-1;
		}
		@Override public void remove() {
			if (prev < 0) {
				throw new IllegalStateException();
			}
			for (int i=0; i<size-1; ++i) {
				items[i] = items[i+1];
			}
			--size;
			cursor = prev;
			prev = -1;
		}
		@Override public void set(T e) {
			if (prev < 0) {
				throw new IllegalStateException();
			}
			items[prev] = e;
		}
		@Override public void add(T e) {
			if (size == capacity) {
				increaseCapacity();
			}
			for (int i=size; i>cursor; --i) {
				items[i] = items[i-1];
			}
			items[cursor++] = e;
			++size;
			prev = -1;
		}
	}
	
	public boolean isFull(){
		return false;
	}
	public boolean isEmpty(){
		return size == 0;
	}
	public int size() {
		return size;
	}
	
	public T peekBack() {
		if(isEmpty()) throw new IllegalStateException();
		return items[size - 1];
	}
	
	private void increaseCapacity() {
		capacity *= 2;
		items = Arrays.copyOf(items, capacity);
	}
	
	public void pushBack(T item){
		if(size == capacity) increaseCapacity();
		items[size++] = item;
	}
	
	public T popBack() {
		if(isEmpty()) throw new IllegalStateException();
		return items[--size];
	}
	
	public T get(int index){
		if(index < 0 || index >= size) 
			throw new IndexOutOfBoundsException("유효하지 않은 색인 사용");
		return items[index];
	}
	
	public void remove(T item) {
		for(int i=0; i<size; i++)
			if(items[i].equals(item)) {
				for(int j=i+1; j<size; j++)
					items[j-1] = items[j];
				--size;
				break;
			}
	}
	
	@SuppressWarnings("unchecked")
	public void pushBackAll(T... items) {
		for(var item: items) pushBack(item);
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
	
	public ListIterator<T> listIterator() {
		return new ArrayListIterator();
	}
}
