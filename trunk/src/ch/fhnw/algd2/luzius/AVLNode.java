// Created by Luzius on Mar 17, 2014

package ch.fhnw.algd2.luzius;

import ch.fhnw.algd2.lesson5.exercise.AbstractAVLNode;

public class AVLNode extends AbstractAVLNode {

	public AVLNode(String value) {
		super(value);
	}

	@Override
	protected AVLNode createNode(String value) {
		return new AVLNode(value);
	}

	@Override
	protected AbstractAVLNode ensureBalance() {
		int balance = getBalance();
		if (balance > 1){
			// left side too high
			AbstractAVLNode left = getLeftChild();
			if (left.getBalance() == -1){
				setLeft(left.rotateLeft());
			}
			return rotateRight();
		} else if (balance < -1){
			// right side too high
			AbstractAVLNode right = getRightChild();
			if (right.getBalance() == 1){
				setRight(right.rotateRight());
			}
			return rotateLeft();
		} else {
			return this;
		}
	}
	
	@Override
	public AbstractAVLNode rotateLeft() {
		AbstractAVLNode newParent = getRightChild();
		setRight(newParent.getLeftChild());
		newParent.setLeft(this);
		return newParent;
	}

	@Override
	public AbstractAVLNode rotateRight() {
		AbstractAVLNode newParent = getLeftChild();
		setLeft(newParent.getRightChild());
		newParent.setRight(this);
		return newParent;
	}
	
	@Override
	protected int calculateHeight() {
		return Math.max(getRightHeight(), getLeftHeight()) + 1;
	}

}
