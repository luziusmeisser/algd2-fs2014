// Created by Luzius on 05.05.2014

package ch.fhnw.algd2.luzius;

import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.tanks.EOrientation;

public class StepMarker implements Comparable<StepMarker> {

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
	
	public void setNext(Node next){
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
		if (isFirst()){
			return null;
		} else {
			StepMarker prev = (StepMarker) origin.getMarker();
			return prev.isFirst() ? node : prev.getFirstStep();
		}
	}

	public boolean isCurrent() {
		return node.getMarker() == this;
	}

	public int getWeight() {
		return totWeight;
	}

	public void fillPath() {
		if (!isFirst()){
			StepMarker prev = (StepMarker) origin.getMarker();
			prev.next = node;
			prev.fillPath();
		}
	}

}
