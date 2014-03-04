package ch.fhnw.algd2.larskessler;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

import java.util.UUID;

public class TreeDetector implements ITreeDetector{
	
	private static java.util.UUID marker;
		
	@Override
	public boolean isTree(Node any) {
		Node current = any;
		Node[] currentNeighbours = current.getNeighbors();
		marker = UUID.randomUUID();
		
		if(currentNeighbours.length == 0) {
			return true;
		} else {
			return isTreeRec(current, null);
		}
	}

	private boolean isTreeRec(Node current, Node from) {
		current.setMarker(marker);
		Node[] currentNeighbours = current.getNeighbors();
	
		for(int i = 0; i < currentNeighbours.length; i++) {
			if(currentNeighbours[i] != from) {
				if(currentNeighbours[i] != null && currentNeighbours[i].getMarker() == marker) {
					return false;
				} else if(!isTreeRec(currentNeighbours[i], current)) {
					return false;
				}
			}
			
			/*
			if(currentNeighbours[i] == from) {
				// do nothing (just increment)
			} else if (currentNeighbours[i].getMarker() == (Object) marker) {
				return false;
			} else {
				from = current;
				currentNeighbours[i].setMarker(marker);
				if(!isTreeRec(currentNeighbours[i], from)) {
					return false;
				}
			}
			*/
		}
		return true;
	}
}
