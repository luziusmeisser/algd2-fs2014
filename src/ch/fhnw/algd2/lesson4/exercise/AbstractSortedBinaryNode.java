// Created by Luzius on 10.03.2014

package ch.fhnw.algd2.lesson4.exercise;

/**
 * Exercise 4.2: extends this class to implement the node of a
 * sorted binary tree.
 */
public abstract class AbstractSortedBinaryNode extends BinaryNode {

	public AbstractSortedBinaryNode(String value) {
		super(value);
	}
	
	public AbstractSortedBinaryNode getLeftChild(){
		return (AbstractSortedBinaryNode) super.getLeftChild();
	}
	
	public AbstractSortedBinaryNode getRightChild(){
		return (AbstractSortedBinaryNode) super.getRightChild();
	}
	
	/**
	 * Inserts the string into the tree and returns the number of steps it took to find the right place.
	 * Does nothing if the value is already present.
	 */
	public abstract int insert(String value);

	/**
	 * Removes the value from the tree (if present).
	 */
	public abstract void remove(String value);

	public String findLowestValue() {
		if (left == null){
			return getValue();
		} else {
			return ((AbstractSortedBinaryNode)left).findLowestValue();
		}
	}
	
}
