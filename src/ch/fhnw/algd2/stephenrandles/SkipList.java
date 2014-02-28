// Created by Stephen Randles 24.02.2014

package ch.fhnw.algd2.stephenrandles;

import java.util.Stack;

import ch.fhnw.algd2.lesson2.exercise.ISkipList;

public class SkipList<T extends Comparable<T>> implements ISkipList<T> {
	private final int MAX_LEVEL = 100;
	
	private Node<T> start;
	private Node<T> end;
	
	private Stack<History<Node<T>>> trace;
	
	public SkipList() {
		this.start = new Node<T>(null, MAX_LEVEL);
		this.end = new Node<T>(null, MAX_LEVEL);
		
		for (int i=0; i <= MAX_LEVEL; i++) {
			this.start.setNext(i, end);
		}
		
	}
	
	public T find() {
		
		return null;
	}

	@Override
	public void add(T item) {
		
		int currentLevel = MAX_LEVEL;
		Node<T> currentNode = this.start;
		Node<T> nextNode;
		
		trace = new Stack<>();
		
		while (currentLevel > 0) {
			nextNode = currentNode.getNext(currentLevel);
			
			while (nextNode.getContents() != null && item.compareTo(nextNode.getContents()) > 0) {				
				trace.add(new History<>(currentNode, currentLevel));
				
				currentNode = nextNode;
				nextNode = currentNode.getNext(currentLevel);
			}			
			currentLevel--;
		}
		
		Node<T> newNode = new Node<>(item, defineNodeLevel());
		
		// Link all nodes from trace to the new node
		// Link each level of the node to the next node at that level or higher
		updateNodeLinks(trace, newNode);
		
	}

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countStepsTo(T item) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private boolean getRandomBoolean() {
		return Math.random() < 0.5;
	}
	
	private int defineNodeLevel() {
		int level = 0;
		while (getRandomBoolean()) {
			level++;
		}
		return level;
	}
	
	private void updateNodeLinks(Stack<History<Node<T>>> trace, Node<T> newNode) {
		int topLevel = newNode.getLevel();
		
		for (History<Node<T>> history : trace) {
			for (int level = topLevel; level >= history.getLastLevel(); level--) {
				// TODO link new node to next nodes
				newNode.setNext(level, history.getVisitedNode().getNext(level));
				//TODO Link prior node to new node
				history.getVisitedNode().setNext(level, newNode);
			}
		}
	}
	
	public void printList() {
		Node<T> node = start.getNext(0);
		System.out.println();
		while(node.hasNext()) {
			System.out.print("[ ");
			System.out.print(node.getContents().toString());
			System.out.print(" ]");
			System.out.print(" -> ");
			
			node = node.getNext(0);
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
			return targets.length;
		}
	}
	
	class History<F extends Node<T>> {
		private Node<T> visitedNode;
		private int lastLevel;
		
		public History(Node<T> visitedNode, int lastLevel) {
			this.visitedNode = visitedNode;
			this.lastLevel = lastLevel;
		}
		
		public Node<T> getVisitedNode() {
			return this.visitedNode;
		}
		
		public int getLastLevel() {
			return this.lastLevel;
		}		
	}

}
