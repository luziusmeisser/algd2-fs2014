// Created by Luzius on 03.03.2014

package ch.fhnw.algd2.luzius;

import java.util.Collections;
import java.util.List;

import ch.fhnw.algd2.lesson4.exercise.IBinaryNode;

public class BinaryTreeTraverser<T extends IBinaryNode> {

	@SuppressWarnings("unchecked")
	public void visit(IVisitor<T> visitor, T root) {
		if (root != null) {
			visit(visitor, (T) root.getLeftChild());
			visitor.visit(root);
			visit(visitor, (T) root.getRightChild());
		}
	}

	public String assemble(IBinaryNode root, boolean depthFirst) {
		if (depthFirst) {
			return traverseDepthFirst(root);
		} else {
			return traverseBreadthFirst(Collections.singletonList(root));
		}
	}

	private String traverseBreadthFirst(List<IBinaryNode> currentLevel) {
		String result = "";
		if (currentLevel.size() == 0) {
			return result;
		} else {
			List<IBinaryNode> nextLevel = new java.util.LinkedList<IBinaryNode>();
			for (IBinaryNode node : currentLevel) {
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

	private String traverseDepthFirst(IBinaryNode node) {
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
