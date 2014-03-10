// Created by Stephen Randles 10.03.2014

package ch.fhnw.algd2.stephenrandles;

import ch.fhnw.algd2.lesson4.exercise.BinaryNode;
import ch.fhnw.algd2.lesson4.exercise.IBinaryTreeTraverser;

public class BinaryTreeTraverser implements IBinaryTreeTraverser {

	@Override
	public String assemble(BinaryNode root, boolean depthFirst) {
		if (depthFirst) {
			return walkNodeDeepFirst(root);			
		} else {
			return walkNode(root);
		}
	}
	
	private String walkNode(BinaryNode node) {
		if (node != null) {
			String s = "";
			if (node.getLeftChild() != null)
				s += node.getLeftChild().getValue();
			if (node.getRightChild() != null) {
				s += node.getRightChild().getValue();
			}
			return walkNode(node.getLeftChild()) + walkNode(node.getRightChild()) + s;
		}
		return "";
	}
	
	private String walkNodeDeepFirst(BinaryNode node) {
		if (node != null) {
			return walkNodeDeepFirst(node.getLeftChild()) + node.getValue() + walkNodeDeepFirst(node.getRightChild());
		}
		return "";
	}
}
