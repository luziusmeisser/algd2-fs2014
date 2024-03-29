// Created by Stephen Randles 24.02.2014

package ch.fhnw.algd2.stephenrandles.lesson02;

import java.util.NoSuchElementException;
import ch.fhnw.algd2.lesson2.exercise.ISkipList;

public class SkipList<T extends Comparable<T>> implements ISkipList<T> {
	private final int MAX_LEVEL = 100;
	
	private Node<T> start;
	private int STEPS_FOR_LAST_FIND;
		
	public SkipList() {
		this.start = new Node<T>(null, MAX_LEVEL);		
	}
	
	/**
	 * @param item
	 * @return Returns <code>item</code> if it's contained in the list.
	 * Otherwise, the closest match from the start of the list is returned.
	 */
	public T find(T item) {
		STEPS_FOR_LAST_FIND = 0;
		
		int level = MAX_LEVEL;
		Node<T> currentNode = this.start;
		
		while (level >= 0) {			
			while (currentNode.getNext(level) != null && item.compareTo(currentNode.getNext(level).getContents()) >= 0) {				
				currentNode = currentNode.getNext(level);
				STEPS_FOR_LAST_FIND++;
			}			
			level--;
		}

		return currentNode.getContents();
	}
	
	@Override
	public void add(T item) {
		Node<T> currentNode = this.start;		
		Node<T> newNode = new Node<>(item, defineNodeLevel());
		
		for (int level = MAX_LEVEL; level >= 0; level--) {
			
			// Follow next node as long as item is larger than node's contents
			while (currentNode.getNext(level) != null && item.compareTo(currentNode.getNext(level).getContents()) >= 0) {				
				currentNode = currentNode.getNext(level);
			}
			
			// Relink nodes
			if (level <= newNode.getLevel()) {
				newNode.setNext(level, currentNode.getNext(level));
				currentNode.setNext(level, newNode);
			}
		}		
	}

	@Override
	/**
	 * Removes first element from the list.
	 */
	public T removeFirst() {
		if (!start.hasNext())
			throw new NoSuchElementException();
		
		Node<T> removedNode = start.getNext(0);
		
		for (int level = removedNode.getLevel(); level >= 0; level--) {
			start.setNext(level, removedNode.getNext(level));
		}		
		
		return removedNode.getContents();
	}

	@Override
	public int countStepsTo(T item) {
		find(item);
		return STEPS_FOR_LAST_FIND;
	}
	
	private int defineNodeLevel() {
		int level = 0;
		while (Math.random() < 0.5) {
			level++;
		}
		return level;
	}
	
	public void printList() {
		Node<T> node = start;
		System.out.println();
		while(node.hasNext()) {
			node = node.getNext(0);
			
			System.out.print("[ ");
			System.out.print(node.getContents().toString());
			System.out.print(" (" + node.getLevel() +")");
			System.out.print(" ]");
			System.out.print(" -> ");			
		}
		System.out.println();
	}
	
	class Node<E> {
		private Node<E>[] targets;
		private E contents;
		
		@SuppressWarnings("unchecked")
		public Node(E contents, int level) {
			this.contents = contents;
			this.targets = new Node[level+1];
		}
		
		public boolean hasNext() {
			return this.targets[0] != null;
		}
		
		public void setNext(int level, Node<E> targetNode) {
			this.targets[level] = targetNode;
		}

		public Node<E> getNext(int level) {
			return this.targets[level];
		}

		public E getContents() {
			return this.contents;
		}
		
		public int getLevel() {
			return targets.length - 1;
		}
	}

}
