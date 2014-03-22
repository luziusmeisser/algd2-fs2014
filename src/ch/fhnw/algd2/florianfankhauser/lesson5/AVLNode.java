// Created by Florian Fankhauser on ${date}

package ch.fhnw.algd2.florianfankhauser.lesson5;

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
		return getLeftHeight() > getRightHeight() ? getLeftHeight() : getRightHeight();
	}

	@Override
	protected AbstractAVLNode ensureBalance() {
		if (getBalance() < -1) {
			// Right to high
			if (getRightChild().getBalance() > 0)  {
				setRight(getRightChild().rotateRight());
				return rotateLeft();
			} else {
				return rotateLeft();
			}
		} else if (getBalance() > 1) {
			// Left to high
			if (getLeftChild().getBalance() < 0) {
				setLeft(getLeftChild().rotateLeft());
				return rotateRight();
			} else{
				rotateRight();
			}
		}
		return this;
	}

	@Override
	public AbstractAVLNode rotateLeft() {
		AbstractAVLNode right = getRightChild();
		AbstractAVLNode rl = right.getLeftChild();
		right.setLeft(this);
		setRight(rl);
		return right;
	}

	@Override
	public AbstractAVLNode rotateRight() {
		AbstractAVLNode left = getLeftChild();
		AbstractAVLNode lr = left.getRightChild();
		left.setRight(this);
		setLeft(lr);
		return left;
	}
}
