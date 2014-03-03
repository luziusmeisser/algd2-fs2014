// Created by Stephen Randles 03.03.2014

package ch.fhnw.algd2.stephenrandles;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

public class TreeDetector implements ITreeDetector {

	@Override
	public boolean isTree(Node any) {
		boolean isTree = visitNode(any, null);
		clearMarkers(any);
		
		return isTree;
	}
	
	private boolean visitNode(Node node, Node source) {
		
		node.setMarker(true);

		for (Node neighbour : node.getNeighbors()) {

			if (!neighbour.equals(source)) {
				if (neighbour.getMarker() != null && neighbour.getMarker().equals(true)){
					return false;
				}
				
				visitNode(neighbour, node);
			}
		}
		
		return true;
	}
	
	private void clearMarkers(Node start) {
		for (Node neighbour : start.getNeighbors()) {
			neighbour.setMarker(false);
			clearMarkers(neighbour);
		}
	}

}
