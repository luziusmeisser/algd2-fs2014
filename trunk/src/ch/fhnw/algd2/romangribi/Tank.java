// Created by Roman Gribi on 18.05.2014

package ch.fhnw.algd2.romangribi;

import java.util.PriorityQueue;

import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

public class Tank implements IStrategy {

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
        if (situation.getGraph() == null) {
            return ETankAction.SCAN;
        } else {
            Marker marker = (Marker) situation.getGraph().getMarker();
            if (marker == null) {
                PriorityQueue<Marker> markers = new PriorityQueue<>();
                marker = new Marker(situation.getGraph());
                markers.add(marker);
                findPath(markers);
            }
            return situation.getOrientation().deriveTankAction(marker.action());
        }
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
