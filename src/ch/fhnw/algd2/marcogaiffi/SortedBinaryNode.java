// Created by Marco on 16.03.2014

package ch.fhnw.algd2.marcogaiffi;

import ch.fhnw.algd2.lesson4.exercise.AbstractSortedBinaryNode;

public class SortedBinaryNode extends AbstractSortedBinaryNode {
	private final SortedBinaryNode parent;

	public SortedBinaryNode(String value) {
		this(value, null);
	}

	public SortedBinaryNode(String value, SortedBinaryNode parent) {
		super(value);
		this.parent = parent;
	}

	@Override
	public int insert(String value) {
		return insert(value, 1);
	}

	public int insert(String value, int steps) {
		int compare = value.compareTo(getValue());

		// value equals the value of this node
		if (compare == 0)
			return steps;

		// value has to be on the left side
		if (compare < 0) {
			if (left == null) {
				left = new SortedBinaryNode(value, this);
				return steps;
			} else
				return ((SortedBinaryNode) left).insert(value, steps + 1);
		}
		// values has to be on the right side
		// compare > 0
		else {
			if (right == null) {
				right = new SortedBinaryNode(value, this);
				return steps;
			} else
				return ((SortedBinaryNode) right).insert(value, steps + 1);
		}
	}

	@Override
	public void remove(String value) {
		int compare = value.compareTo(getValue());

		// Remove this node
		if (compare == 0) {
			if (left == null && right == null) {
				// replace current node with nothing
				replace(null);
			} else if (left == null) {
				// replace current node with the right
				replace((SortedBinaryNode) right);
			} else if (right == null) {
				// replace current node with the left
				replace((SortedBinaryNode) left);
			} else {
				// find lowest, remove it and add as replacement for the current
				// node
				SortedBinaryNode lowest = ((SortedBinaryNode) right).findLowest();
				remove(lowest.getValue());
				lowest.left = left;
				replace(lowest);
			}
		}
		// Node to remove is on the left side
		else if (compare < 0) {
			if (left != null)
				((SortedBinaryNode) left).remove(value);
		}
		// Node to remove is on the right side
		else {
			if (right != null)
				((SortedBinaryNode) right).remove(value);
		}
	}

	private void replace(SortedBinaryNode node) {
		if (parent == null)
			return;
		if (parent.left == this)
			parent.left = node;
		else if (parent.right == this)
			parent.right = node;
		else {
			System.out.println("sollte nicht passieren...");
		}
	}

	private SortedBinaryNode findLowest() {
		if (left == null)
			return this;
		return ((SortedBinaryNode) left).findLowest();
	}
}