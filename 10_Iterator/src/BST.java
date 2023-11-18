import java.util.Iterator;
import java.util.ArrayList;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * 반복자 패턴
 * 이진 검색 트리
 * 전체를 방문하여 리스트에 보관한 후, 리스트를 이용하여 반복함
 * 효율성 측면에서는 바람직하지는 않음 (반복자를 사용한다고 항상 모든 요소를 방문하는 것은 아님) 
 */

class TreeNode<T>{
	public T key;
	public TreeNode<T> left = null;
	public TreeNode<T> right = null;
	public TreeNode(T key) {
		this.key = key;
	}
	@Override public String toString() {
		return key.toString();
	}
}

public class BST<T extends Comparable<T>> implements Iterable<T>{
	public enum TraversalType {PREORDER, INORDER, POSTORDER, BFS};
	private TreeNode<T> root = null;
	private int size = 0;
	private TraversalType currentType = TraversalType.INORDER;
	
	public BST() {}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public boolean isFull() {
		return false;
	}
	public void setTraversalType(TraversalType traversalType) {
		currentType = traversalType;
	}
	
	public void add(T key) {
		if(isEmpty()) root = new TreeNode<T>(key);
		else {
			TreeNode<T> parentNode = findNode(root, key);
			if(parentNode.key.equals(key)) return;
			else if(parentNode.key.compareTo(key)>0)
				parentNode.left = new TreeNode<T>(key);
			else parentNode.right = new TreeNode<T>(key);
		}
		++size;
	}
	
	public boolean find(T key) {
		if(isEmpty()) return false;
		return findNode(root, key).key == key;
		
	}
	
	public void remove(T key) {
		if(isEmpty()) return;
		TreeNode<T> parent = null;
		TreeNode<T> curr = root;
		while(curr != null) {
			if(curr.key.equals(key)) break;
			parent = curr;
			curr = (curr.key.compareTo(key) > 0)? curr.left: curr.right;
		}
		if(curr == null) return;
		TreeNode<T> delNode = curr;
		if(curr.left != null && curr.right != null) {
			parent = curr;
			curr = curr.left;
			while(curr.right != null) {
				parent = curr;
				curr = curr.right;
			}
			delNode.key = curr.key;
			delNode = curr;
		}
		removeNode(parent, delNode);
		--size;
	}
	
	private TreeNode<T> findNode(TreeNode<T> currNode, T key) {
		if(currNode.key.equals(key)) return currNode;
		TreeNode<T> nextNode = (currNode.key.compareTo(key) > 0)? currNode.left: currNode.right;
		return nextNode == null? currNode: findNode(nextNode, key);

	}
	
	private void removeNode(TreeNode<T> parent, TreeNode<T> child){
		TreeNode<T> grandChild = child.left != null? child.left: child.right;
		if(parent == null) root = grandChild;
		else {
			if(parent.key.compareTo(child.key) >= 0) parent.left = grandChild;
			else parent.right = grandChild;
		}
	} 
	
	private void inorder(TreeNode<T> currNode, ArrayList<T> list) {
		if(currNode.left != null) inorder(currNode.left, list);
		list.add(currNode.key);
		if(currNode.right != null) inorder(currNode.right, list);
	}
	
	private void preorder(TreeNode<T> currNode, ArrayList<T> list) {
		list.add(currNode.key);
		if(currNode.left != null) preorder(currNode.left, list);
		if(currNode.right != null) preorder(currNode.right, list);
	}
	
	private void postorder(TreeNode<T> currNode, ArrayList<T> list) {
		if(currNode.left != null) postorder(currNode.left, list);
		if(currNode.right != null) postorder(currNode.right, list);
		list.add(currNode.key);
	}
	
	@Override
	public Iterator<T> iterator() {
		if(currentType != TraversalType.BFS) {
			ArrayList<T> list = new ArrayList<>(); // snapshot 방식, 추가공간 사용
			switch(currentType) {
			case PREORDER: preorder(root, list); break;
			case POSTORDER: postorder(root, list); break;
			default: inorder(root, list); break;
				// return new BST_Inorder_Iterator<T>(root);
			}
			return list.iterator();
		}
		else return new BST_BFS_Iterator<T>(root);
	}
}
