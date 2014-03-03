// Created by Stephen Randles 03.03.2014

package ch.fhnw.algd2.stephenrandles;

public class BinaryTree<T extends Comparable<T>> {
	private Node<T> root;
	
	public T find(T item) {		
		return findNode(root, item).contents;
	}
	
	public void add(T item) {
		Node<T> newNode = new Node<T>(item);
		
		if (root == null) {
			root = newNode;
		} else {
			Node<T> newParentNode = findNode(root, item);
			
			if (item.compareTo(newParentNode.contents) < 0) {
				newParentNode.left = newNode;
			} else {
				newParentNode.right = newNode;
			}
		}
		
	}
	
	private Node<T> findNode(Node<T> start, T item) {		
		if (item.equals(start.contents) || (start.left == null && start.right == null) ) {
			return start;
		} else if (item.compareTo(start.contents) < 0) {
			return findNode(start.left, item);
		} else {
			return findNode(start.right, item);
		}
	}
	
	class Node<E> {
		private Node<E> left;
		private Node<E> right;
		private E contents;
		
		public Node(E contents) {
			this.contents = contents;
		}
	}

}
