// Created by Stephen Randles 11.03.2014

package ch.fhnw.algd2.stephenrandles;

import java.util.concurrent.atomic.AtomicInteger;

import ch.fhnw.algd2.lesson4.exercise.AbstractSortedBinaryNode;

public class SortedBinaryNode extends AbstractSortedBinaryNode {
	private AtomicInteger stepCounter = new AtomicInteger();
	private SortedBinaryNode parent;

	public SortedBinaryNode(String value) {
		super(value);
	}
	
	public SortedBinaryNode(String value, SortedBinaryNode parent) {
		super(value);
		this.parent = parent;
	}

	@Override
	public int insert(String value) {
		int steps = stepCounter.incrementAndGet();
		
		if (value.compareTo(this.getValue()) < 0) {
			if (this.getLeftChild() == null) {
				this.left = new SortedBinaryNode(value, this);
				stepCounter.set(0);				
			} else {
				this.getLeftChild().insert(value);
			}
		} else if (value.compareTo(this.getValue()) > 0) {
			if (this.getRightChild() == null) {
				this.right = new SortedBinaryNode(value, this);
				stepCounter.set(0);
			} else {
				this.getRightChild().insert(value);
			}			
		}
		
		return steps;
	}

	@Override
	public void remove(String value) {
		if (this.parent == null) // Root
			return;
		
		SortedBinaryNode nodeToMove;
		
		if (this.right != null) {
			// Find lowest value on right tree -> guaranteed to be smaller than all right values, and bigger than all left values
			nodeToMove = ((SortedBinaryNode) this.right).findLowestValueNode(); 
			// Remove from the tree for now
			nodeToMove.parent.left = null;
		} else {
			// No right tree:
			// Find highest value on left tree -> guaranteed to be bigger than all other left values, and smaller than all right values
			nodeToMove = ((SortedBinaryNode) this.left).findHighestValueNode(); 
			// Remove from the tree for now
			nodeToMove.parent.right = null;
		}
		
		
		// Replace this node with the found node
		nodeToMove.parent = this.parent;
		nodeToMove.left = this.left;
		nodeToMove.right = this.right;
		
		if (this.parent != null) { // If not root
			if (this.parent.getLeftChild() == this) {
				this.parent.left = nodeToMove;
			} else {
				this.parent.right = nodeToMove;
			}
		}
	}

	
	private SortedBinaryNode findLowestValueNode() {
		if (left == null) {
			return this;
		} else {
			return ((SortedBinaryNode)left).findLowestValueNode();
		}
	}
	
	private SortedBinaryNode findHighestValueNode() {
		if (right == null) {
			return this;
		} else {
			return ((SortedBinaryNode)right).findHighestValueNode();
		}
	}

}
