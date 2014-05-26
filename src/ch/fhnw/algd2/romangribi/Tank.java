// Created by Roman Gribi on 18.05.2014

package ch.fhnw.algd2.romangribi;

import java.util.Comparator;
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
        // PriorityQueue<Node> queue = new PriorityQueue<>(50, new Comparator<Node>() {
        // @Override
        // public int compare(Node o1, Node o2) {
        // return ((Marker) o1.getMarker()).compareTo((Marker) o2.getMarker());
        // }
        // });
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

    private void findPath(PriorityQueue<Marker> markers) {
        while (markers.size() > 0) {
            Marker optimal = markers.poll();
            if (optimal == optimal.node.getMarker()) {
                Node current = optimal.node;
                if (current.hasBonus()) {
                    optimal.setPath();
                    return;
                } else {
                    for (Edge edge : current.getEdges()) {
                        int weight = optimal.weight + edge.getWeight();
                        Node next = edge.getOther(current);
                        Marker nextMarker = (Marker) next.getMarker();
                        if (nextMarker == null || nextMarker.weight > weight) {
                            markers.add(new Marker(next, current, weight));
                        }
                    }
                }
            }
        }
    }

    private class Marker implements Comparable<Marker> {

        private Node node;
        private Node prev;
        private Node next;
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

        private boolean isFirst() {
            return prev == null;
        }

        public boolean hasBonus() {
            return this.node.hasBonus();
        }

        public Edge[] getEdges() {
            return this.node.getEdges();
        }

        private void setPath() {
            if (!isFirst()) {
                Marker prev = (Marker) this.prev.getMarker();
                prev.next = this.node;
                prev.setPath();
            }
        }

        @Override
        public int compareTo(Marker other) {
            return Integer.compare(weight, other.weight);
        }

        public EOrientation action() {
            return node.getDirection(next);
        }

    }

}
