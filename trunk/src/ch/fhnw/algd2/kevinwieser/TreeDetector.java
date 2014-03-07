// Created by Kevin Wieser on 03.03.2014

package ch.fhnw.algd2.kevinwieser;

import java.util.UUID;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

public class TreeDetector implements ITreeDetector {

	private UUID marker;

	@Override
	public boolean isTree(Node any) {
		Node[] nodes = any.getNeighbors();
		// marker bei jedem Durchgang neu setzen, sonst nimmt es immer die alten Werte
		marker = UUID.randomUUID();
		if (checkNodesNull(nodes)) {
			return true;
		} else {
			return visitTree(any, null);
		}
	}

	private boolean visitTree(Node any, Node source) {
		any.setMarker(marker);
		Node[] nodes = any.getNeighbors();
		
		for (int i = 0; i < nodes.length; i++) {
				if (nodes[i] != source) {	
					if (nodes[i] != null && nodes[i].getMarker() == marker && !visitTree(nodes[i], any)) {
						return false;
					}
				}	
			}
		return true;
	}
	
	private boolean checkNodesNull(Node[] nodes) {
		int counter = 0;
		
		if (nodes == null || nodes.length == 0) {
			return true;
		}
		
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
