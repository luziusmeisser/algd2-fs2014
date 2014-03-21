// Created by Kevin Wieser on 21.03.2014
package ch.fhnw.algd2.kevinwieser;

import ch.fhnw.algd2.lesson5.exercise.AbstractAVLNode;

public class AVLNode extends AbstractAVLNode {

	public AVLNode(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected AbstractAVLNode createNode(String value) {
		return new AVLNode(value);		
	}

	@Override
	protected int calculateHeight() {
		return Math.max(getLeftHeight(), getRightHeight()) + 1;
	}

	@Override
	protected AbstractAVLNode ensureBalance() {
		
		int balance = getBalance();
		if (balance > 1) {
			if (getRightChild().getBalance() > -1) {
				return rotateLeft();
			} else if (getRightChild().getBalance() == -1) {
				getRightChild().rotateRight();
				return rotateLeft();
			}
		} else if (balance < -1) {
			if (getLeftChild().getBalance() < 1) {
				AbstractAVLNode node = getLeftChild().getRightChild();
				this.setLeft(node);
				return rotateRight();
			} else if (getLeftChild().getBalance() == 1) {
				getLeftChild().rotateLeft();
				return rotateLeft();
			}
		}
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractAVLNode rotateLeft() {
		AbstractAVLNode node = getRightChild();
		if (node == null) {
			return null;
		}
		this.setRight(node.getLeftChild());
		node.setLeft(this);
		return node;
	}

	@Override
	public AbstractAVLNode rotateRight() {
		AbstractAVLNode node = getLeftChild();
		if (node == null) {
			return null;
		}
		this.setRight(node.getRightChild());
		node.setRight(this);;
		return node;
	}

}
