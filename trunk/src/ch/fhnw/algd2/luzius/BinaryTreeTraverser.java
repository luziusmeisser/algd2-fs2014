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
		List<BinaryNode> nextLevel = new java.util.LinkedList<BinaryNode>();
		for (BinaryNode node : currentLevel) {
			if (node.getLeftChild() != null) {
				nextLevel.add(node.getLeftChild());
			}
		}
		return result + traverseBreadthFirst(nextLevel);
	}

	private String traverseDepthFirst(BinaryNode node) {
		String left = traverseDepthFirst(node.getLeftChild());
		String middle = node.getValue();
		return left + middle;
	}

}
