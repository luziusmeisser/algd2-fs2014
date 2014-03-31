// Created by Luzius on 10.03.2014

package ch.fhnw.algd2.lesson5.exercise;

import ch.fhnw.algd2.lesson4.exercise.IBinaryNode;

public abstract class AbstractAVLNode implements IBinaryNode {

	private String value;

	private int height;
	private AbstractAVLNode left, right;

	public AbstractAVLNode(String value) {
		this.value = value;
		this.height = 1;
	}
	
	public final String getValue(){
		return value;
	}
	
	public AbstractAVLNode getLeftChild() {
		return left;
	}

	public void setLeft(AbstractAVLNode left) {
		this.left = left;
		this.height = calculateHeight(); // update height on every change
	}

	public AbstractAVLNode getRightChild() {
		return right;
	}

	public void setRight(AbstractAVLNode right) {
		this.right = right;
		this.height = calculateHeight(); // update height on every change
	}

	// Instantiates a new node (cannot instantiate AbstractAVLNode because abstract)
	protected abstract AbstractAVLNode createNode(String value);
	
	// Updates the height of this node
	protected abstract int calculateHeight();
	
	/**
	 *  Makes sure that this node is balanced. More precisely, this method:
	 *  - checks whether a rebalance is necessary at all (the difference between left height and right height is bigger than 1)
	 *  - if it is necessary, performs one of the four AVL rebalancing operations
	 *  - in either case, returns the (maybe new) top node of that subtree, so the parent can update it
	 *  It is probably helpful to implement two helper methods rotateLeft and rotateRight. 
	 */
	protected abstract AbstractAVLNode ensureBalance();
	
	public abstract AbstractAVLNode rotateLeft();
	
	public abstract AbstractAVLNode rotateRight();

	public final int getHeight() {
		return height;
	}
	
	protected final int getLeftHeight(){
		return left == null ? 0 : left.getHeight();
	}
	
	protected final int getRightHeight(){
		return right == null ? 0 : right.getHeight();
	}
	
	public final int getBalance(){
		return getLeftHeight() - getRightHeight();
	}

	public final void insert(String value) {
		int comp = value.compareTo(getValue());
		if (comp == 0) {
			// same value, ignore
		} else {
			// smaller -> left side
			boolean insertLeft = comp < 0;
			if (insertLeft) {
				if (left == null) {
					setLeft(createNode(value));
				} else {
					left.insert(value);
					setLeft(left.ensureBalance()); 
					assert left.getBalance() >= -1;
					assert left.getBalance() <= 1 : left.getClass().getName();
				}
			} else {
				if (right == null) {
					setRight(createNode(value));
				} else {
					right.insert(value);
					setRight(right.ensureBalance());
					assert right.getBalance() >= -1;
					assert right.getBalance() <= 1;
				}
			}
		}
	}
	
	@Override
	public String toString(){
		return "(" + getLeftChild() + ", " + getValue() + ", " + getRightChild() + ")";
	}
	
}
