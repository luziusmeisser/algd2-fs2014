// Created by Kevin Wieser on 10.03.2014


package ch.fhnw.algd2.kevinwieser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.fhnw.algd2.lesson4.exercise.BinaryNode;
import ch.fhnw.algd2.lesson4.exercise.IBinaryTreeTraverser;

public class BinaryTreeTraverser implements IBinaryTreeTraverser {

	@Override
	public String assemble(BinaryNode root, boolean depthFirst) {
		if(depthFirst){
		    return traverseDepth(root);
		}else{
		    return traverseWidth(Collections.singletonList(root));
		}
	}
	
	
	private String traverseWidth(List<BinaryNode> nodes) {
		String result = "";
		List<BinaryNode> nextNodes = new ArrayList<BinaryNode>();
		for (BinaryNode node : nodes) {

			if (node.getLeftChild() != null) {
				nextNodes.add(node.getLeftChild());
			}
			if (node.getRightChild() != null) {
				nextNodes.add(node.getRightChild());
			}

			result += node.getValue();
		}

		if (nextNodes.size() > 0) {
			result += traverseWidth(nextNodes);
		}

		return result;
	}

	private String traverseDepth(BinaryNode current) {
		String left;
		String right;

		if (current.getLeftChild() == null) {
			left = "";
		} else {
			left = this.traverseDepth(current.getLeftChild());
		}
		if (current.getRightChild() == null) {
			right = "";
		} else {
			right = this.traverseDepth(current.getRightChild());
		}

		return left + current.getValue() + right;
	}

}
