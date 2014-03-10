// Created by Luzius on 03.03.2014

package ch.fhnw.algd2.luzius;

import java.util.Collections;
import java.util.List;

import ch.fhnw.algd2.lesson4.exercise.BinaryNode;
import ch.fhnw.algd2.lesson4.exercise.IBinaryTreeTraverser;

/**
 * Achtung: funktioniert so nicht. :)
 */
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
		String result = "";
		if (currentLevel.size() == 0) {
			return result;
		} else {
			List<BinaryNode> nextLevel = new java.util.LinkedList<BinaryNode>();
			for (BinaryNode node : currentLevel) {
				if (node.getLeftChild() != null) {
					nextLevel.add(node.getLeftChild());
				}
				if (node.getRightChild() != null) {
					nextLevel.add(node.getRightChild());
				}
				result += node.getValue();
			}
			return result + traverseBreadthFirst(nextLevel);
		}
	}

	private String traverseDepthFirst(BinaryNode node) {
		if (node == null) {
			return "";
		} else {
			String left = traverseDepthFirst(node.getLeftChild());
			String middle = node.getValue();
			String right = traverseDepthFirst(node.getRightChild());
			return left + middle + right;
		}
	}

}
