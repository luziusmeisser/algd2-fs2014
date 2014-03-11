// Created by Stephen Randles 11.03.2014

package ch.fhnw.algd2.stephenrandles;

import java.util.concurrent.atomic.AtomicInteger;

import ch.fhnw.algd2.lesson4.exercise.AbstractSortedBinaryNode;

public class SortedBinaryNode extends AbstractSortedBinaryNode {
	private AtomicInteger stepCounter = new AtomicInteger();

	public SortedBinaryNode(String value) {
		super(value);
	}

	@Override
	public int insert(String value) {
		int steps = stepCounter.incrementAndGet();
		
		if (value.compareTo(this.getValue()) < 0) {
			if (this.getLeftChild() == null) {
				this.left = new SortedBinaryNode(value);
				stepCounter.set(0);				
			} else {
				this.getLeftChild().insert(value);
			}
		} else if (value.compareTo(this.getValue()) > 0) {
			if (this.getRightChild() == null) {
				this.right = new SortedBinaryNode(value);
				stepCounter.set(0);
			} else {
				this.getRightChild().insert(value);
			}			
		}
		
		return steps;
	}

	@Override
	public void remove(String value) {
		

	}

}
