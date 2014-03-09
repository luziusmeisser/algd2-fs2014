// Created by Marco on 03.03.2014
//Teamarbeit mit Emanuel Mistretta

package ch.fhnw.algd2.marcogaiffi;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

public class TreeDetector implements ITreeDetector {

	@Override
	public boolean isTree(Node any) {
		return checkTree(any, null);
	}

	/**
	 * Starts at an item, sets a marker and goes through all children
	 * recursively If one branches end is reached and items don't point back,
	 * markes will be removed. If markers are not removed it means an item
	 * points back to previous ones
	 */
	private boolean checkTree(Node any, Node previous) {
		// Means that a neighbour points back to a previous item
		if (any.getMarker() != null)
			return false;

		// set marker, used to determine if a neighbor points back
		any.setMarker(new Object());

		boolean isTree = true;
		Node[] neighbors = any.getNeighbors();
		int counter = 0;

		// recursive call as long as true
		// If a neighbour equals the previous item -> points back -> loop
		while (isTree && counter < neighbors.length) {
			if (neighbors[counter] != previous) {
				isTree = isTree && checkTree(neighbors[counter], any);
			}
			counter++;
		}
		any.setMarker(null);
		return isTree;
	}
}
