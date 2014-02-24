// Created by Luzius on Feb 22, 2014

package ch.fhnw.algd2.luzius;

import java.util.NoSuchElementException;
import java.util.Random;

import ch.fhnw.algd2.lesson2.exercise.ISkipList;

public class SkipList<T extends Comparable<T>> implements ISkipList<T> {

	public static final int MAX_HEIGHT = 20;

	private Random RAND = new Random(133);

	private RootNode root = new RootNode();

	public void add(T item) {
		Node<T> neu = new Node<T>(item);
		root.insert(neu, neu.getLevel());
	}

	public T removeFirst() {
		Node<T> item = root.removeSuccessor();
		return item.value;
	}

	public int countStepsTo(T item) {
		return root.countStepsTo(item);
	}

	class RootNode extends Node<T> {
		public RootNode() {
			super(null);
		}

		public Node<T> removeSuccessor() {
			Node<T> successor = forwards[0];
			if (successor == null) {
				throw new NoSuchElementException();
			} else {
				for (int i = 0; i < successor.forwards.length; i++) {
					forwards[i] = successor.forwards[i];
				}
				return successor;
			}
		}

		protected int calcHeight() {
			return MAX_HEIGHT;
		}

	}

	class Node<N extends Comparable<N>> implements Comparable<Node<N>> {

		protected Node<N>[] forwards;
		private N value;

		@SuppressWarnings("unchecked")
		public Node(N item) {
			this.value = item;
			int size = calcHeight();
			this.forwards = new Node[size];
		}

		public int countStepsTo(N item) {
			for (int i = forwards.length - 1; i >= 0; i--) {
				if (forwards[i] != null) {
					if (item.compareTo(forwards[i].value) >= 0) {
						return forwards[i].countStepsTo(item) + 1;
					}
				}
			}
			return 0;
		}

		public void insert(Node<N> neu, int level) {
			if (level >= 0) {
				if (forwards[level] == null) {
					forwards[level] = neu; // end of list on that level
					insert(neu, level - 1);
				} else if (neu.compareTo(forwards[level]) < 0) {
					// in between this and next node on that level
					neu.forwards[level] = forwards[level];
					forwards[level] = neu;
					insert(neu, level - 1);
				} else {
					forwards[level].insert(neu, level);
				}
			}
		}

		private int getLevel() {
			return forwards.length - 1;
		}

		protected int calcHeight() {
			int height = 1;
			while (RAND.nextBoolean()) {
				height++;
			}
			return Math.min(MAX_HEIGHT, height);
		}

		@Override
		public int compareTo(Node<N> o) {
			return value.compareTo(o.value);
		}

		public String toString() {
			return value == null ? null : value.toString();
		}

	}

}
