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
		
		if (this.getLeftChild() == null && this.getRightChild() == null) {
			return 1;
		} else {
			int height = (this.getLeftHeight() > this.getRightHeight()) ? this.getLeftHeight() : this.getRightHeight();
			height++;
			return height;
		}
	}

	@Override
	protected AbstractAVLNode ensureBalance() {
		int factor = this.getLeftHeight() - this.getRightHeight();
		if (factor > 1) {
			rotateRight();
		} else if (factor < -1) {
			rotateLeft();
		}
		
		return null;
	}

	@Override
	public AbstractAVLNode rotateLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractAVLNode rotateRight() {
		// TODO Auto-generated method stub
		return null;
	}

}
