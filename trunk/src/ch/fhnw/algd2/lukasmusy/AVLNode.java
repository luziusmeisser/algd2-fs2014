//created by lukas.musy on Mar 23, 2014

package ch.fhnw.algd2.lukasmusy;

import ch.fhnw.algd2.lesson5.exercise.AbstractAVLNode;

public class AVLNode extends AbstractAVLNode {

	public AVLNode(String value) {
		super(value);
	}

	@Override
	protected AbstractAVLNode createNode(String value) {
		return new AVLNode(value);
	}

	@Override
	protected int calculateHeight() {
		int height = Math.max(getLeftHeight(), getRightHeight());
		return height;
	}

	@Override
	protected AbstractAVLNode ensureBalance() {
		if(getBalance() > 1) {
			AbstractAVLNode left = getLeftChild();
			if(left.getBalance() == -1) {
				setLeft(left.rotateLeft());
			}
			return rotateRight();
		}
		
		else if(getBalance() < -1) {
			AbstractAVLNode right = getRightChild();
			if(right.getBalance() == 1) {
				setRight(right.rotateRight());
			}
			return rotateLeft();
		}
		return this;
	}

	@Override
	public AbstractAVLNode rotateLeft() {
		AbstractAVLNode parent = getRightChild();
		if(parent == null) return this;
		
		setRight(parent.getLeftChild());
		parent.setLeft(this);
		return parent;
	}

	@Override
	public AbstractAVLNode rotateRight() {
		AbstractAVLNode parent = getLeftChild();
		if(parent == null) return this;
		
		setLeft(parent.getRightChild());
		parent.setRight(this);
		return parent;
	}

}
