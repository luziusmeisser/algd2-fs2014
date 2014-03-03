// Created by Roman Gribi on 03.03.2014

package ch.fhnw.algd2.romangribi;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

public class TreeDetector implements ITreeDetector {

    @Override
    public boolean isTree(Node any) {
        return isTree(any, null);
    }

    private boolean isTree(Node any, Node previous) {
        if (any.getMarker() != null)
            return false;

        any.setMarker(new Object());

        boolean isTree = true;
        Node[] neighbors = any.getNeighbors();
        int i = 0;

        while (isTree && i < neighbors.length) {
            if (neighbors[i] != previous) {
                isTree = isTree && isTree(neighbors[i], any);
            }
            i++;
        }
        
        any.setMarker(null);

        return isTree;
    }
}
