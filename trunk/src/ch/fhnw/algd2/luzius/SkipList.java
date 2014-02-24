// Created by Luzius on Feb 22, 2014

package ch.fhnw.algd2.luzius;

import java.util.Random;

import ch.fhnw.algd2.lesson2.exercise.ISkipList;

public class SkipList<T extends Comparable<T>> implements ISkipList<T> {

	private Random RAND = new Random(133);

	private Node root;

	public void add(T item) {
		if (root == null) {
			root = new Node<T>(item);
		} else {
			Node neu = new Node();
			Node before = find(item);

		}
	}

	public T removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	public int countStepsTo(T item) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void remove(T item) {

	}

	class Node<T> {

		private Node<T>[] forwards;
		private T value;

		@SuppressWarnings("unchecked")
		public Node(T item) {
			this.value = item;
			int size = calcHeight();
			this.forwards = new Node[size];
		}
		
		public void connectTo(Node<T> next){
			for (int i = 0; i < forwards.length && next != null; i++) {
				next = next.findNext(i);
				this.forwards[i] = next;
			}
		}

		private Node<T> findNext(int level) {
			int myLevel = getLevel();
			if (level <= myLevel) {
				return this;
			} else {
				Node<T> next = forwards[myLevel];
				return next == null ? null : next.findNext(level);
			}
		}

		private int getLevel() {
			return forwards.length - 1;
		}

		private int calcHeight() {
			int height = 1;
			while (RAND.nextBoolean()) {
				height++;
			}
			return height;
		}

	}

}
