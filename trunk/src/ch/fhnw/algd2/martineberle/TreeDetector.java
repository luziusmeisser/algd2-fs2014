// Created by Martin Eberle on 03.03.2014

package ch.fhnw.algd2.martineberle;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

public class TreeDetector implements ITreeDetector{

	@Override
	public boolean isTree(Node any) {
		Integer number = 1337; //To set marker to a recognizeable value...
		return testNode(null, any, number);
	}
	
	public boolean testNode(Node parent, Node current, Integer number){
		Node[] next = current.getNeighbors();
		boolean tree;
		
		if(checkVisited(current, number)){ //if current Node has already been visited return false
			return false;
		}
		setVisited(current, number); //set current Node as visited
		
		for(int i = 0; i < next.length; i++){ //for each neighbor of current Node
			if(next[i] != parent){ //if not link to parent
				tree = testNode(current,next[i], number);//check if nodes(and subnodes) are visited
				if(!tree){ //if testNode returns false once, return false for all
					return false;
				}
			}
			if(next[i] == current){ //if link to itself return false
				return false;
			}			
		}
		return true; //if all nodes have been passed and no circle has been found return true
	}

	public void setVisited(Node visited, Integer number) {
		visited.setMarker(number);
	}
	public boolean checkVisited(Node any, Integer number){
		
		return (any.getMarker() == number);
	}

}
