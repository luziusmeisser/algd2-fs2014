// Created by Luzius on May 2, 2014

package ch.fhnw.tankland.strategy;

public class Edge {
	
	private Node n1, n2;
	private int weight;

	public Edge(Node node, Node node2, int weight) {
		this.n1 = node;
		this.n2 = node2;
		this.weight = weight;
	}

	public Node getN1() {
		return n1;
	}

	public Node getN2() {
		return n2;
	}

	public int getWeight() {
		return weight;
	}
	
}
