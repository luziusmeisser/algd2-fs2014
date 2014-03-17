// Created by Luzius on 03.03.2014

package ch.fhnw.algd2.lesson4.exercise;

public class BinaryNode implements IBinaryNode {

	private String value;
	protected BinaryNode left, right;
	
	public BinaryNode(String value, BinaryNode left, BinaryNode right){
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public BinaryNode(String string) {
		this(string, null, null);
	}

	public String getValue(){
		return value;
	}
	
	public BinaryNode getLeftChild(){
		return left;
	}
	
	public BinaryNode getRightChild(){
		return right;
	}

}
