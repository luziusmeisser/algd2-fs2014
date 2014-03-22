// Created by Stephen Randles 17.03.2014

package ch.fhnw.algd2.stephenrandles.lesson05;

import ch.fhnw.algd2.lesson5.exercise.AbstractAVLNode;

public class AVLNode extends AbstractAVLNode {

	public AVLNode(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected AbstractAVLNode createNode(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int calculateHeight() {
		int height = (this.getLeftHeight() > this.getRightHeight()) ? this.getLeftHeight() : this.getRightHeight();
		return height + 1;
	}

	@Override
	protected AbstractAVLNode ensureBalance() {
		int factor = this.getLeftHeight() - this.getRightHeight();
		if (factor > 1) {			// Left-heavy 
			if (this.getLeftChild().getBalance() < -1 ) {	// Left-right case
				this.getLeftChild().rotateLeft();
			}			
			return rotateRight();
		} else if (factor < -1) {	// Right-heavy
			if (this.getRightChild().getBalance() > 1) {	// Right-left case
				this.getRightChild().rotateRight();
			}
			return rotateLeft();
		}
		return null;
	}

	@Override
	public AbstractAVLNode rotateLeft() {
		AbstractAVLNode newRoot = this.getRightChild(); 
		newRoot.setLeft(this);
		this.setRight(null);
		
		return newRoot;
	}

	@Override
	public AbstractAVLNode rotateRight() {
		AbstractAVLNode newRoot = this.getLeftChild(); 
		newRoot.setRight(this);
		this.setLeft(null);
		
		return newRoot;
	}

}
