// Created by Luzius on 03.03.2014

package ch.fhnw.algd2.luzius;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

public class TreeDetector implements ITreeDetector {

	private static int count = 0;

	@Override
	public boolean isTree(Node any) {
		Integer marker = count++;
		return check(null, any, marker);
	}

	private boolean check(Node prev, Node current, Integer marker) {
		if (current.getMarker() == marker) {
			return false;
		} else {
			current.setMarker(marker);
			for (Node n : current.getNeighbors()) {
				if (n != prev) { // don't go back
					boolean tree = check(current, n, marker);
					if (!tree) {
						return false;
					}
				}
			}
			return true;
		}
	}

}
