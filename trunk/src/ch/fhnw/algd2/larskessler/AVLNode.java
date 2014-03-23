package ch.fhnw.algd2.larskessler;

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
		// count own level too
		int leftHeight = getLeftHeight() + 1;
		int rightHeight = getRightHeight() + 1;
		
		if(leftHeight >= rightHeight) {
			return leftHeight;
		}
		
		return rightHeight;
	}

	@Override
	protected AbstractAVLNode ensureBalance() {
		// count levels on left side and right side; subtract right amount from left amount
		if(getBalance() > 1) {
			// left side is too damn high! --> rotateRight
			AbstractAVLNode left = this.getLeftChild();
			
			// right side of left child is too damn high!
			if(left.getBalance() == -1) {
				setLeft(left.rotateLeft());
			}
			
			// rebalance!
			return this.rotateRight();
		} else if (getBalance() < -1) {
			// right side is too damn high! --> rotateLeft
			AbstractAVLNode right = this.getRightChild();
			
			// left side of right child is too damn high!
			if(right.getBalance() == -1) {
				setRight(right.rotateRight());
			}
			
			// rebalance!
			return this.rotateLeft();
		} else {
			return this;
		}
	}

	@Override
	public AbstractAVLNode rotateLeft() {
		// "this" means currentRoot
		AbstractAVLNode newRoot = this.getRightChild();
		this.setRight(newRoot.getLeftChild());
		newRoot.setLeft(this);
		return newRoot;
	}

	@Override
	public AbstractAVLNode rotateRight() {
		// "this" means currentRoot
		AbstractAVLNode newRoot = this.getLeftChild();
		this.setLeft(newRoot.getRightChild());
		newRoot.setRight(this);
		return newRoot;
	}

}
