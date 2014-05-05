// Created by Luzius on May 2, 2014

package ch.fhnw.tankland.strategy;

import java.util.HashMap;

import ch.fhnw.tankland.fields.Field;
import ch.fhnw.tankland.fields.Land;
import ch.fhnw.tankland.geometry.Position;
import ch.fhnw.tankland.tanks.EOrientation;

public class Node {

	private boolean bonus;
	private boolean tank;
	private int cost;
	private Position pos;

	private Object marker;
	private Edge[] neighbors;

	public Node(HashMap<Position, Node> cache, Land land, Position pos) {
		this.pos = pos;
		Field field = land.getFieldAt(pos);
		this.bonus = field.hasBonus();
		this.tank = field.hasTank();
		this.cost = field.getTravelCost();
		this.neighbors = new Edge[EOrientation.values().length];
		cache.put(pos, this);
		for (EOrientation o : EOrientation.values()) {
			Position pos2 = pos.copy();
			pos2.move(o);
			Node neighbor = cache.get(pos2);
			if (neighbor == null) {
				neighbor = new Node(cache, land, pos2);
				neighbors[o.ordinal()] = neighbor.getEdge(o.opposite());
			} else {
				int weight = (cost + neighbor.cost) / 2;
				neighbors[o.ordinal()] = new Edge(this, neighbor, weight);
			}
		}
	}
	
	public Edge[] getEdges(){
		return neighbors;
	}

	public Edge getEdge(EOrientation o) {
		return neighbors[o.ordinal()];
	}

	public void setMarker(Object o) {
		this.marker = o;
	}

	public Object getMarker() {
		return marker;
	}

	public int getTravelCost() {
		return cost;
	}

	public Position getPosition() {
		return pos;
	}

	public boolean hasBonus() {
		return bonus;
	}

	public boolean hasTank() {
		return tank;
	}

}
