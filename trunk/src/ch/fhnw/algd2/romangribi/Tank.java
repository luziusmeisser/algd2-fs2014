// Created by Roman Gribi on 18.05.2014

package ch.fhnw.algd2.romangribi;

import java.util.PriorityQueue;
import java.util.Stack;

import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

public class Tank implements IStrategy {

    private boolean scanned = false;
    private Stack<EOrientation> path;

    @Override
    public int getColor() {
        return 3;
    }

    @Override
    public String getComment() {
        return "";
    }

    @Override
    public String getName() {
        return "Gribi";
    }

    @Override
    public ETankAction getNextAction(Situation situation) {
        // destroy tanks next to me
        for (EOrientation e : EOrientation.values()) {
            if (situation.getNeighbor(e).hasTank()) {
                return situation.getOrientation().deriveTankAction(e);
            }
        }

        if (!this.scanned) {
            this.scanned = true;
            return ETankAction.SCAN;
        }

        if (this.path == null) {
            this.path = findPath(situation);
        }

        if (!this.path.isEmpty()) {
            ETankAction next = situation.getOrientation().deriveTankAction(this.path.peek());
            // Only remove from path if we have moved.
            if (ETankAction.FORWARD == next) {
                this.path.pop();
            }
            return next;
        } else {
            // we need a new path, do a rescan
            this.path = null;
            return ETankAction.SCAN;
        }
    }

    private Stack<EOrientation> findPath(Situation situation) {
        PriorityQueue<Marker> markers = new PriorityQueue<>();
        markers.add(new Marker(situation.getGraph()));

        Node next;
        Marker optimal;
        do {
            optimal = markers.poll();

            for (Edge edge : optimal.getEdges()) {
                int weight = optimal.weight + edge.getWeight();
                next = edge.getOther(optimal.node);

                if (next.getMarker() == null) {
                    markers.add(new Marker(next, optimal.node, weight));
                }
            }
        } while (!markers.isEmpty() && !optimal.hasBonus());

        // Check if we found a path.
        if (!optimal.hasBonus())
            return new Stack<EOrientation>();

        // Return the path
        Node current = optimal.node, previous = optimal.prev;
        Stack<EOrientation> path = new Stack<EOrientation>();

        while (previous != null) {
            path.add(previous.getDirection(current));
            current = previous;
            previous = ((Marker) current.getMarker()).prev;
        }

        return path;
    }

    private class Marker implements Comparable<Marker> {

        private Node node;
        private Node prev;
        private int weight;

        public Marker(Node node) {
            this(node, null, 0);
        }

        public Marker(Node node, Node prev, int weight) {
            this.node = node;
            this.prev = prev;
            this.weight = weight;
            this.node.setMarker(this);
        }

        public boolean hasBonus() {
            return this.node.hasBonus();
        }

        public Edge[] getEdges() {
            return this.node.getEdges();
        }

        @Override
        public int compareTo(Marker other) {
            return Integer.compare(weight, other.weight);
        }

    }

}
