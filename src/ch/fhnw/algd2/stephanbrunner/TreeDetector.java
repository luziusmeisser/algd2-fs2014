// Created by Stephan Brunner on 03.03.2014

package ch.fhnw.algd2.stephanbrunner;

import ch.fhnw.algd2.lesson3.exercise.ITreeDetector;
import ch.fhnw.algd2.lesson3.exercise.Node;

public class TreeDetector implements ITreeDetector {

    @Override
    public boolean isTree(Node any) {
        boolean ret = isTree(any, 0);
        clean(any);
        return ret;
    }
    

    private boolean isTree(Node any, int level) {
        if (any.getMarker() == null) {
            // node not visited yet
            any.setMarker(new Integer(level));
            boolean ret = true;
            for (Node n: any.getNeighbors()) {
                ret = ret && isTree(n, level + 1);
            }
            return ret;
        }
        else if ((Integer)any.getMarker() == level - 2) {
            // thats the node where we came from
            return true;
        }
        else {
            // node already visited -> not a tree
            return false;
        }
        
    }

    private void clean(Node any) {
        int level = (Integer)any.getMarker();
        any.setMarker(null);
        for (Node n: any.getNeighbors()) {
            if (n.getMarker() != null && (Integer)n.getMarker() > level) {
                clean(n);
            }
        }
    }
}
