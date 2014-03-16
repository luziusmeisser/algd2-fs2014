//created by lukas.musy on Mar 12, 2014
/* Grundlsätzlich trees:
 * traversiere(Tree t) {
 * traversiere (left(t));
 * verarbeite(key(t));
 * traversiere(right(t));
 * 
 * 
 */


package ch.fhnw.algd2.lukasmusy;

import java.util.Queue;
import java.util.LinkedList;

import ch.fhnw.algd2.lesson4.exercise.BinaryNode;
import ch.fhnw.algd2.lesson4.exercise.IBinaryTreeTraverser;

public class BinaryTreeTraverser implements IBinaryTreeTraverser {

	@Override
	public String assemble(BinaryNode root, boolean depthFirst) {
		if(depthFirst) 
			return assembleDepthFirstOrder(root);
		else 
			return assembleLevelOrder(root);
	}

	private String assembleLevelOrder(BinaryNode root) {
		
		if(root == null)
		return "";
		
		String result = "";
		BinaryNode current;
		
		Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
		queue.add(root);
		
		while (!queue.isEmpty()) {
			current = queue.poll();
			result += current.getValue();
			
			if(current.getLeftChild() != null)
				queue.add(current.getLeftChild());
			
			if(current.getRightChild() != null)
				queue.add(current.getRightChild());
		}
		
		return result;
			
	}

	private String assembleDepthFirstOrder(BinaryNode root) {
		String result = "";
		
		if(root != null){
			result += assembleDepthFirstOrder(root.getLeftChild());
			result += root.getValue();
			result += assembleDepthFirstOrder(root.getRightChild());
		}
		
		return result;
	}
	
	

}
