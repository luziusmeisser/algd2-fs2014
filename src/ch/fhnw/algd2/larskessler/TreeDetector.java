package ch.fhnw.algd2.larskessler;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;
import java.util.UUID;

public class TreeDetector implements ITreeDetector{
	
	// where does current node come from
	private Node from = null;
	
	@Override
	public boolean isTree(Node any) {
		Node current = any;
		Node[] currentNeighbours = current.getNeighbors();
		
		if(currentNeighbours.length == 0) {
			return true;
		} else {
			for(int i = 0; i < currentNeighbours.length; i++) {
				if(currentNeighbours[i] == from) {
					i++;
				} else if (currentNeighbours[i].getMarker() == (Object) true) {
					return false;
				} else if (currentNeighbours[i].getMarker() == (Object) false) {
					this.from = current;
					currentNeighbours[i].setMarker(true);
					current = currentNeighbours[i];
					isTree(current);
				}
			}
		}
		return true;
	}
	
}
