// Created by Stephen Randles 03.03.2014

package ch.fhnw.algd2.stephenrandles;

public class BinaryTree {
	
	
	
	public BinaryTree() {
		
	}
	
	class Node<E> {
		private Node<E> left;
		private Node<E> right;
		private E contents;
		
		@SuppressWarnings("unchecked")
		public Node(E contents) {
			this.contents = contents;
		}
	}

}
