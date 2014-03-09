// Created by Emanuel Mistretta on 09.03.2014

package ch.fhnw.algd2.emanuelmistretta;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

public class TreeDetector implements ITreeDetector{

    @Override
    public boolean isTree(Node any) {
        return checkTree(any, null);
    }
    
    /**
     * Starts at an item, sets a marker and goes through all children recursively
     * If one branches end is reached and items don't point back, markes will be removed.
     * If markers are not removed it means an item points back to previous ones
     */
    private boolean checkTree(Node any, Node previous){
	//Means that a neighbour points back to a previous item
	if(any.getMarker() != null)
	    return false;
	
	//Set a marker to determin if a neighbour points back
	any.setMarker(new Object());

        boolean isTree = true;
        Node[] neighbors= any.getNeighbors();
        int counter = 0;

        //Iterate as long as the result (recursive) is true.
        //If a neighbour equals the previous item -> points back -> circle
        while (isTree && counter < neighbors.length) {
            if (neighbors[counter] != previous) {
                isTree = isTree && checkTree(neighbors[counter], any);
            }
            counter++;
        }
        
        any.setMarker(null);

        return isTree;
	
    }

}
