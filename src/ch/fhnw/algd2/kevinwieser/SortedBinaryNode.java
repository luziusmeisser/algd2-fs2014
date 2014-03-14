// Created by Kevin Wieser on 10.03.2014

package ch.fhnw.algd2.kevinwieser;

import ch.fhnw.algd2.lesson4.exercise.AbstractSortedBinaryNode;
import ch.fhnw.algd2.lesson4.exercise.BinaryNode;

public class SortedBinaryNode extends AbstractSortedBinaryNode {

	private BinaryNode root;
	
	public SortedBinaryNode(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int insert(String value) {
		if (root == null) {
			root = new BinaryNode(value, null,null);
		} //else if ()
		return 0;
		
	}

	@Override
	public void remove(String value) {
		// TODO Auto-generated method stub
		
	}

}
