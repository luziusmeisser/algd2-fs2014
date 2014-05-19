// Created by Luzius on 05.05.2014

package ch.fhnw.tankland.strategy.example;

import java.util.PriorityQueue;

import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IField;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

public class DijkstraBot implements IStrategy {

	@Override
	public int getColor() {
		return 5;
	}

	@Override
	public String getComment() {
		return null;
	}

	@Override
	public String getName() {
		return "Edsgar";
	}

	int forwards = 0;

	@Override
	public ETankAction getNextAction(Situation s) {
		ETankAction a1 = checkSurroundings(s);
		if (a1 != null) {
			return a1;
		} else if (s.getGraph() == null) {
			return ETankAction.SCAN;
		} else {
			Node current = s.getGraph();
			StepMarker marker = (StepMarker) current.getMarker();
			if (marker == null) {
				PriorityQueue<StepMarker> markers = new PriorityQueue<>();
				marker = new StepMarker(current);
				markers.add(marker);
				fillMap(s, current, markers);
			}
			if (marker.isTarget()) {
				return ETankAction.SCAN;
			} else {
				EOrientation o = marker.getDirection();
				return s.getOrientation().deriveTankAction(o);
			}
		}
	}

	private ETankAction checkSurroundings(Situation s) {
		for (EOrientation o : EOrientation.values()) {
			IField f = s.getNeighbor(o);
			if (f.hasBonus() || f.hasTank()) {
				return s.getOrientation().deriveTankAction(o);
			}
		}
		return null;
	}

	private void fillMap(Situation s, Node start, PriorityQueue<StepMarker> markers) {
		while (markers.size() > 0) {
			StepMarker best = markers.poll();
			if (best.isCurrent()) {
				Node current = best.getNode();
				if (current.hasBonus() || (current.hasTank() && s.getSpeed() >= 200 && current != start)) {
					best.fillPath();
					break;
				} else {
					for (Edge e : current.getEdges()) {
						int weight = best.getWeight() + e.getWeight();
						Node neighbor = e.getOther(current);
						StepMarker nbMarker = (StepMarker) neighbor.getMarker();
						if (nbMarker == null || nbMarker.getWeight() > weight) {
							markers.add(new StepMarker(current, neighbor, weight));
						}
					}
				}
			}
		}
	}

}

class StepMarker implements Comparable<StepMarker> {

	private Node node;
	private Node origin;
	private Node next;
	private int totWeight;

	public StepMarker(Node node) {
		this(null, node, 0);
	}

	public StepMarker(Node origin, Node node, int i) {
		this.origin = origin;
		this.node = node;
		this.totWeight = i;
		this.node.setMarker(this);
	}

	public Node getNode() {
		return node;
	}

	public void setNext(Node next) {
		this.node = next;
	}

	public EOrientation getDirection() {
		return node.getDirection(next);
	}

	@Override
	public int compareTo(StepMarker o) {
		return Integer.compare(totWeight, o.totWeight);
	}

	public boolean isFirst() {
		return origin == null;
	}

	public Node getFirstStep() {
		if (isFirst()) {
			return null;
		} else {
			StepMarker prev = (StepMarker) origin.getMarker();
			return prev.isFirst() ? node : prev.getFirstStep();
		}
	}

	public boolean isCurrent() {
		return node.getMarker() == this;
	}

	public boolean isTarget() {
		return next == null;
	}

	public int getWeight() {
		return totWeight;
	}

	public void fillPath() {
		if (!isFirst()) {
			StepMarker prev = (StepMarker) origin.getMarker();
			prev.next = node;
			prev.fillPath();
		}
	}

}