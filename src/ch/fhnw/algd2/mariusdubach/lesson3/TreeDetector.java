//Created by Marius Dubach 03.03.2014

package ch.fhnw.algd2.mariusdubach.lesson3;

import java.util.UUID;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

public class TreeDetector implements ITreeDetector {
	
	java.util.UUID marker;

	@Override
	public boolean isTree(Node any) {
		marker = UUID.randomUUID();
		
		if(any.getNeighbors().length == 0){
			return true;
		}
		try{
			isTreeRecursively(any, null);
		}catch(NoTreeException e){
			return false;
		}
		return true;
	}
	
	private boolean isTreeRecursively(Node any, Node origin) throws NoTreeException{
		Node[] currentNeighbors = any.getNeighbors();
		try{
			if(any.getMarker() == marker){
				throw new NoTreeException();
			}
		}catch(NullPointerException e){
		}
		any.setMarker(marker);
		
		for(Node n : currentNeighbors){
			if(n != origin){
				isTreeRecursively(n, any);
			}
		}
		return true;
	}
	
	class NoTreeException extends Exception{
		
	}

}