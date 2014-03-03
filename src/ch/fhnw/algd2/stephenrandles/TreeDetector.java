// Created by Stephen Randles 03.03.2014

package ch.fhnw.algd2.stephenrandles;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

public class TreeDetector implements ITreeDetector {
	private long marker; 

	@Override
	public boolean isTree(Node any) {
		marker = (long)(Math.random() * Math.pow(10, Math.random()*10));
		boolean isTree = visitNode(any, null);		
		return isTree;
	}
	
	private boolean visitNode(Node node, Node source) {
		node.setMarker(marker);
		for (Node neighbour : node.getNeighbors()) {

			if (!neighbour.equals(source)) {
				if (neighbour.getMarker() != null && neighbour.getMarker().equals(marker)
						|| visitNode(neighbour, node) == false) {
					return false;
				}
			}
		}
		return true;
	}
	

}
