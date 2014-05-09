// Created by Stephan Brunner on 05.05.2014

package ch.fhnw.algd2.stephanbrunner;

import java.util.HashSet;

import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

import java.util.Stack;


public class Strategy implements IStrategy {
    private static final double SCAN_INTERVAL = 2000;
    
    private Stack<EOrientation> path = new Stack<EOrientation>();
    private static double millis = 0;
    private boolean updateNextTime = false;

    @Override
    public int getColor() {
        return 4;
    }

    @Override
    public String getComment() {
        return null;
    }

    @Override
    public String getName() {
        return "Stephan";
    }

    @Override
    public ETankAction getNextAction(Situation situation) {
        // update scheduler
        if (updateNextTime) {
            updatePathToCherry(situation);
            updateNextTime = false;
        }
        if (System.currentTimeMillis() - millis > SCAN_INTERVAL || path.isEmpty()) {
            millis = System.currentTimeMillis();
            updateNextTime = true;
            return ETankAction.SCAN;
        }
        
        // drive around
        ETankAction a = situation.getOrientation().deriveTankAction(path.peek());
        if (a == ETankAction.FORWARD) {
            path.pop();
        }
        return a;
    }

    private void updatePathToCherry(Situation situation) {
        Node cherry = null;
        HashSet<Node> search = new HashSet<Node>(200);
        Node root = situation.getGraph();
        root.setMarker(new Marker(0, null));
        search.add(situation.getGraph());
        for (int i = 0; cherry == null && i < 100; i++) {
            HashSet<Node> nextSearch = new HashSet<Node>();
            for (Node n: search) {
                for (EOrientation o: EOrientation.values()) {
                    Node nextNode = n.getEdge(o).getOther(n);
                    int cost = n.getEdge(o).getWeight() + ((Marker)n.getMarker()).cost;
                    if (nextNode.hasBonus())
                        cherry = nextNode;
                    if (nextNode.getMarker() == null || ((Marker)nextNode.getMarker()).cost > cost) {
                        nextNode.setMarker(new Marker(cost, o.opposite()));
                        nextSearch.add(nextNode);
                    }
                }
            }
            search = nextSearch;
        }
        path = new Stack<EOrientation>();
        while (cherry != null && cherry.getPosition() != root.getPosition()) {
            EOrientation nextStep = ((Marker)cherry.getMarker()).orientation.opposite();
            path.push(nextStep);
            cherry = cherry.getEdge(nextStep.opposite()).getOther(cherry);
        }
    }
        
    private class Marker{
        public int cost;
        public EOrientation orientation;
        
        public Marker(int cost, EOrientation orientation) {
            this.cost = cost;
            this.orientation = orientation;
        }
        
        public String toString() {
            if (orientation == EOrientation.NORTH)
                return "NORTH";
            if (orientation == EOrientation.EAST)
                return "EAST";
            if (orientation == EOrientation.SOUTH)
                return "SOUTH";
            if (orientation == EOrientation.WEST)
                return "WEST";
            return null;
        }
    }
}
