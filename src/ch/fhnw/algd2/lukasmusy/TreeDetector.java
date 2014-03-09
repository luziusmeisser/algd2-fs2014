//created by lukas.musy on Mar 9, 2014

package ch.fhnw.algd2.lukasmusy;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

public class TreeDetector implements ITreeDetector {

	@Override
	public boolean isTree(Node any) {
		return isTree(any, null);
	}

	private boolean isTree(Node current, Node previous) {
		
		if (current.getMarker() != null)
			return false;
		
		current.setMarker(new Object());
		
		Node[] neighbors = current.getNeighbors();
		for (Node n : neighbors) {
			if (n != previous && !isTree(n, current)){
				return false;
			}
		}
		current.setMarker(null);
		
		return true;
		
	}

}
