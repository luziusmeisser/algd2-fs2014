// Created by Yannick Augstburger on Mar 7, 2014

package ch.fhnw.algd2.yannickaugstburger;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

public class TreeDetector implements ITreeDetector {
	
	private long mark;

	@Override
	public boolean isTree(Node any) {
		
		mark++;;
		any.setMarker(mark);
		try {
			for (Node n : any.getNeighbors()) {
				if (n.getMarker() != null && (long) n.getMarker() == mark) {
					throw new IllegalStateException();
				} else {
					treeVisit(n, any);
				}
			}
		} catch (IllegalStateException e) {
			return false;
		}
		return true;
	}

	private void treeVisit(Node any, Node parent) {
		any.setMarker(mark);
		for (Node n : any.getNeighbors()) {
			if (!(n == parent)) {
				if (n.getMarker() != null && (long) n.getMarker() == mark) {
					throw new IllegalStateException();
				} else {
					treeVisit(n, any);
				}
			}
		}
	}
}

