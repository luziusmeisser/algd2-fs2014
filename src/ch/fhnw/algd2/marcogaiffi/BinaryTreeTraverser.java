// Created by Marco on 10.03.2014

package ch.fhnw.algd2.marcogaiffi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.fhnw.algd2.lesson4.exercise.BinaryNode;
import ch.fhnw.algd2.lesson4.exercise.IBinaryTreeTraverser;

public class BinaryTreeTraverser implements IBinaryTreeTraverser {

	/**
	 * Traverses the tree from the given root and assembles all encountered
	 * values (Strings). Null values should be ignored.
	 */

	@Override
	public String assemble(BinaryNode root, boolean depthFirst) {
		if (depthFirst) {
			return traverseDepth(root);
		} else {
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

	public static String traverseDepth(BinaryNode current) {
		String left;
		String right;

		if (current.getLeftChild() == null) {
			left = "";
		} else {
			left = traverseDepth(current.getLeftChild());
		}
		if (current.getRightChild() == null) {
			right = "";
		} else {
			right = traverseDepth(current.getRightChild());
		}

		return left + current.getValue() + right;
	}
}
