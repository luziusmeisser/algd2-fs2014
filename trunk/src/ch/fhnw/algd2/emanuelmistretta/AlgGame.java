// Created by Emanuel Mistretta on 13.05.2014
//Quelle Christian Güdel

package ch.fhnw.algd2.emanuelmistretta;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

public class AlgGame implements IStrategy {

    private boolean hasScanned = false;
    private Stack<EOrientation> path;

    @Override
    public int getColor() {
	return 2;
    }

    @Override
    public String getComment() {
	return null;
    }

    @Override
    public String getName() {
	return "Mongobili";
    }

    @Override
    public ETankAction getNextAction(Situation situation) {
	// destroy any nearby tanks
	for (EOrientation e : EOrientation.values()) {
	    if (situation.getNeighbor(e).hasTank()) {
		return situation.getOrientation().deriveTankAction(e);
	    }
	}

	if (!this.hasScanned) {
	    this.hasScanned = true;
	    return ETankAction.SCAN;
	}

	if (this.path == null)
	    this.path = this.findPathToBonusField(situation);

	if (!this.path.isEmpty()) {
	    ETankAction next = situation.getOrientation().deriveTankAction(this.path.peek());
	    // only remove if we actually moved
	    if (next == ETankAction.FORWARD)
		this.path.pop();

	    return next;
	    // find the next cherry if the path is empty on the next turn
	} else {
	    this.path = null;
	    this.hasScanned = false;
	}

	return ETankAction.FORWARD;
    }

    private Stack<EOrientation> findPathToBonusField(Situation situation) {
	PriorityQueue<Node> prioqueue = new PriorityQueue<>(50, new Comparator<Node>() {
	    @Override
	    public int compare(Node n1, Node n2) {
		return ((NodeInfo) n1.getMarker()).value - ((NodeInfo) n2.getMarker()).value;
	    }
	});

	Node current = situation.getGraph();
	current.setMarker(new NodeInfo(null, 0));
	prioqueue.add(current);

	while (!prioqueue.isEmpty() && !current.hasBonus()) {
	    for (Edge e : current.getEdges()) {
		Node other = e.getOther(current);
		int cost = e.getWeight() + ((NodeInfo) current.getMarker()).value;

		if (other.getMarker() == null) {
		    other.setMarker(new NodeInfo(current, cost));
		    prioqueue.add(other);
		}
	    }

	    current = prioqueue.poll();
	}

	Node bonus = current;
	Node previous = ((NodeInfo) current.getMarker()).previous;

	Stack<EOrientation> path = new Stack<>();

	while (previous != null) {
	    path.add(previous.getDirection(bonus));
	    bonus = previous;
	    previous = ((NodeInfo) bonus.getMarker()).previous;
	}

	return path;
    }

    private class NodeInfo {
	public final Node previous;
	public final int value;

	public NodeInfo(Node previous, int cost) {
	    this.previous = previous;
	    this.value = cost;
	}
    }
}
