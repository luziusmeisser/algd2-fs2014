// Created by Luzius on 03.03.2014

package ch.fhnw.algd2.lesson4.exercise;

/**
 * Exercise 4.1: Implement IBinaryTreeTraverser
 */
public interface IBinaryTreeTraverser {

	/**
	 * Traverses the tree from the given root and assembles all
	 * encountered values (Strings). Null values should be ignored.
	 */
	public String assemble(IBinaryNode root, boolean depthFirst);
	
}
