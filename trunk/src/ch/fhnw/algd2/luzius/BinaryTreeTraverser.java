// Created by Luzius on 03.03.2014

package ch.fhnw.algd2.luzius;

import java.util.Collections;
import java.util.List;

import ch.fhnw.algd2.lesson4.exercise.BinaryNode;
import ch.fhnw.algd2.lesson4.exercise.IBinaryTreeTraverser;

public class BinaryTreeTraverser implements IBinaryTreeTraverser {

	@Override
	public String assemble(BinaryNode root, boolean depthFirst) {
		if (depthFirst) {
			return traverseDepthFirst(root);
		} else {
			return traverseBreadthFirst(Collections.singletonList(root));
		}
	}

	private String traverseBreadthFirst(List<BinaryNode> currentLevel) {
		List<BinaryNode> nextLevel = new java.util.LinkedList<BinaryNode>();
		String result = "";
		for (BinaryNode node: currentLevel){
			result += node.getValue();
			nextLevel.add(node.getLeftChild());
			nextLevel.add(node.getRightChild());
		}
		return result + traverseBreadthFirst(nextLevel);
	}

	private String traverseDepthFirst(BinaryNode node) {
		if (node != null) {
			String left = traverseDepthFirst(node.getLeftChild());
			String middle = node.getValue();
			String right = traverseDepthFirst(node.getRightChild());
			return left + middle + right;
		} else {
			return "";
		}
	}

}
