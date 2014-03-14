// Created by Stephen Randles 11.03.2014

package ch.fhnw.algd2.stephenrandles.lesson04;

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
		SortedBinaryNode nodeToDelete = find(value);
		SortedBinaryNode nodeToMove = null;
		
		if (nodeToDelete == null || nodeToDelete.parent == null) // Not found, or root
			return;
		
		if (nodeToDelete.left != null){
			nodeToMove = ((SortedBinaryNode) nodeToDelete.left).findHighestValueNode(); 
			nodeToMove.parent.right = null;
		} else if (nodeToDelete.right != null) {
			nodeToMove = ((SortedBinaryNode) nodeToDelete.right).findLowestValueNode(); 
			nodeToMove.parent.left = null;
		} 
		
		if (nodeToMove != null) {
		// Give nodeToMove same parent & children as nodeToDelete node
			nodeToMove.parent = nodeToDelete.parent;
			nodeToMove.left = nodeToDelete.left;
			nodeToMove.right = nodeToDelete.right;
		}
		
		// Modify nodeToDelete's parent: Replace nodeToDelete with nodeToMove
		if (nodeToDelete.parent.getLeftChild() == nodeToDelete) {
			nodeToDelete.parent.left = nodeToMove;
		} else {
			nodeToDelete.parent.right = nodeToMove;
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
	
	private SortedBinaryNode find(String value) {
		if (value.compareTo(this.getValue()) == 0) {
			return this;
		} else if (left != null && value.compareTo(this.getValue()) < 0) {
			return ((SortedBinaryNode)left).find(value);
		} else if (right != null && value.compareTo(this.getValue()) > 0) {
			return ((SortedBinaryNode)right).find(value);
		} else {
			return null;
		}		
	}

}
