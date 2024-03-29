// Created by Kevin Wieser on 03.03.2014


package ch.fhnw.algd2.kevinwieser;

import java.util.UUID;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

public class TreeDetector implements ITreeDetector {

	private Object marker = new Object();

	@Override
	public boolean isTree(Node any) {
		Node[] nodes = any.getNeighbors();
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
					// ist die folgende Bedinung nicht wahr (dann pr�fe rekursiv weiter, ansonsten
					// f�hrt er in einem zyklus visitTree endlos lang aus
					if (nodes[i] != null && nodes[i].getMarker() == marker) {
						return false;
					} else if(!visitTree(nodes[i], any)) {
						// wenn true -> false (geht weiter) // wenn false -> true (return false)
						return false;
					}
				}	
			}
		any.setMarker(null);
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
