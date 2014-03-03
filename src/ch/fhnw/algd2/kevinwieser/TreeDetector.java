// Created by Kevin Wieser on 03.03.2014

package ch.fhnw.algd2.kevinwieser;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

public class TreeDetector implements ITreeDetector {

	@Override
	public boolean isTree(Node any) {
		
		Node[] nodes = any.getNeighbors();
		int counter = 0;
		
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] == null) {
				counter++;
			}
		}
		
		if (counter == nodes.length) {
			return true;
		}
		
		
		
		
		
		return false;
	}

}
