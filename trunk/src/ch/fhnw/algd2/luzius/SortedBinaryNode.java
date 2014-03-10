// Created by Luzius on 10.03.2014

package ch.fhnw.algd2.luzius;

import ch.fhnw.algd2.lesson4.exercise.AbstractSortedBinaryNode;
import ch.fhnw.algd2.lesson4.exercise.BinaryNode;

public class SortedBinaryNode extends AbstractSortedBinaryNode {

	public SortedBinaryNode(String value) {
		super(value);
	}

	@Override
	public int insert(String value) {
		int comp = value.compareTo(getValue());
		if (comp == 0) {
			// same value, ignore
			return 0;
		} else if (comp < 0) {
			// smaller -> left side
			if (left == null) {
				left = new SortedBinaryNode(value);
				return 1;
			} else {
				return 1 + ((AbstractSortedBinaryNode) left).insert(value);
			}
		} else {
			// larger -> right side
			if (right == null) {
				right = new SortedBinaryNode(value);
				return 1;
			} else {
				return 1 + ((AbstractSortedBinaryNode) right).insert(value);
			}
		}
	}

	@Override
	public void remove(String value) {
		doRemove(value);
	}

	protected AbstractSortedBinaryNode doRemove(String value){
		int comp = value.compareTo(getValue());
		if (comp == 0){
			if (left == null){
				return (AbstractSortedBinaryNode) right;
			} else if (right == null){
				return (AbstractSortedBinaryNode) left;
			} else {
				SortedBinaryNode rn = (SortedBinaryNode)right;
				String lowest = rn.findLowestValue();
				SortedBinaryNode replacement = new SortedBinaryNode(lowest);
				replacement.left = this.left;
				replacement.right = rn.doRemove(lowest);
				return replacement;
			}
		} else if (comp < 0){
			if (left != null){
				this.left = ((SortedBinaryNode)left).doRemove(value);
			}
			return this;
		} else {
			if (right != null){
				this.right = ((SortedBinaryNode)right).doRemove(value);
			}
			return this;	
		}
	}
}
