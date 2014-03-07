// Created by Martin Eberle on 03.03.2014

package ch.fhnw.algd2.martineberle;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

//Get node
	//get list of neighbors
		//ignore parent
			//check for already visited
				//set already visited or abort

public class TreeDetector implements ITreeDetector{
static int count = 0;
	@Override
	public boolean isTree(Node any) {
		Integer number = count++;
		return testNodes(null, any, number);
	}
	public boolean testNodes(Node parent, Node current, Integer number){
		Node[] next = current.getNeighbors();
		boolean tree;
		
		if(checkVisited(current, number)){ //if current Node has already been visited return false
			return false;
		}
		setVisited(current, number);
		
		for(int i = 0; i < next.length; i++){ //for each neighbor of current Node
			if(next[i] != parent){ //if not link to itself or parent
				tree = testNodes(current,next[i], number);
				if(!tree){
					return false;
				}
			}
			if(next[i] == current){ //if link to itself return false
				return false;
			}			
		}
		return true;
	}

	public void setVisited(Node any, Integer number) {
		any.setMarker(number);
	}
	public boolean checkVisited(Node any, Integer number){
		
		return (any.getMarker() == number);
	}

}
