// Created by Stephen Randles 03.03.2014

package ch.fhnw.algd2.stephenrandles;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

public class TreeDetector implements ITreeDetector {

	@Override
	public boolean isTree(Node any) {
		return visitNode(any, null);
	}
	
	private boolean visitNode(Node node, Node source) {
		
		node.setMarker(source);
		
		for (Node neighbour : node.getNeighbors()) {
			if (!neighbour.equals(source)) {
				if (!neighbour.getMarker().equals(node)){
					return false;
				}
				
				visitNode(neighbour, node);
			}
		}
		
		return true;
	}

}
