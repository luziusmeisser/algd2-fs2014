// Created by Stephen Randles 10.03.2014

package ch.fhnw.algd2.stephenrandles;

import java.util.Queue;
import java.util.LinkedList;

import ch.fhnw.algd2.lesson4.exercise.BinaryNode;
import ch.fhnw.algd2.lesson4.exercise.IBinaryTreeTraverser;

public class BinaryTreeTraverser implements IBinaryTreeTraverser {

	@Override
	public String assemble(BinaryNode root, boolean depthFirst) {
		if (depthFirst) {
			return assembleDepthFirst(root);			
		} else {
			return assembleBreadthFirst(root);
		}
	}
	
	private String assembleBreadthFirst(BinaryNode root) {
		StringBuilder sb = new StringBuilder();
		Queue<BinaryNode> q = new LinkedList<BinaryNode>();
		BinaryNode node;
		
		q.add(root);		
		
		while (!q.isEmpty()) {
			node = q.poll();
			sb.append(node.getValue());

			if (node.getLeftChild() != null)
				q.add(node.getLeftChild());
			if (node.getRightChild() != null)
				q.add(node.getRightChild());
		}		
		
		return sb.toString();		
	}
	
	private String assembleDepthFirst(BinaryNode node) {
		if (node != null) {
			return assembleDepthFirst(node.getLeftChild()) + node.getValue() + assembleDepthFirst(node.getRightChild());
		}
		return "";
	}
}
