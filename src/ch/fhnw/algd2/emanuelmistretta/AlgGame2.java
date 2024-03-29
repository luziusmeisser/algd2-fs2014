// Created by Emanuel Mistretta on 25.05.2014
//Da mir nicht klar ist was aufgabe auf lektion 10 ist habe ich mir den code kopiert.
//Werde eigenen code implementieren sobald bekannt was und ob es aufgaben gab :)

package ch.fhnw.algd2.emanuelmistretta;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IField;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

public class AlgGame2 implements IStrategy {
    private Stack<EOrientation> movesToCherry = new Stack<>();
    // private Stack<ETankAction> moves = new Stack<>();

    private final int maxStepsToGetCherry = 20;
    private final int stepsUntilScan = 10;
    private int currentSteps = 0;


    @Override
    public int getColor() {
	return 2;
    }

    @Override
    public String getComment() {
	return "PuuuPuuuu";
    }

    @Override
    public String getName() {
	return "Mongobilie";
    }

    @Override
    public ETankAction getNextAction(Situation situation) {
	ETankAction action = ETankAction.PAUSE;

	if (situation.getGraph() == null || currentSteps % stepsUntilScan == 0) {
	    currentSteps = 0;
	    movesToCherry.clear();

	    currentSteps++;
	    return ETankAction.SCAN;

	}

	if (movesToCherry.empty()) {
	    // Check how far away the cherry is
	    movesToCherry = findShortestPathToCherry(situation);
	}

	// Follow cherry if nearby
	if (!movesToCherry.empty() && movesToCherry.size() < maxStepsToGetCherry) {
	    EOrientation whereToGo = movesToCherry.peek();
	    action = situation.getOrientation().deriveTankAction(whereToGo);

	    // Only remove step if we moved along (not if we turned!)
	    if (action == ETankAction.FORWARD)
		movesToCherry.pop();
	} else {
	    // Otherwise just cruise around
	    int rand = (int) (Math.random() * 10);

	    if (rand % 2 == 0) {
		action = ETankAction.FORWARD;
	    } else if (rand % 5 == 0) {
		if (rand < 5)
		    action = ETankAction.LEFT;
		else
		    action = ETankAction.RIGHT;
	    }

	}

	currentSteps++;
	return action;
    }

    private EOrientation[] tankFreeFields(Situation s) {
	EOrientation tankFreeFields[] = new EOrientation[4];
	int i = 0;

	for (IField f : s.getNeighborhood()) {
	    if (!f.hasTank()) {
		tankFreeFields[i++] = s.getDirection(f);
	    }
	}

	return tankFreeFields;
    }

    private Stack<EOrientation> findShortestPathToCherry(Situation situation) {
	PriorityQueue<Node> visited = new PriorityQueue<>(5, new Comparator<Node>() {
	    public int compare(Node a, Node b) {
		return ((Marker) a.getMarker()).compareTo(((Marker) b.getMarker()));
	    }
	});

	Node current = situation.getGraph();
	current.setMarker(new Marker(null, 0));
	visited.add(current);

	while (!visited.isEmpty()) {
	    if (current.hasBonus())
		break;

	    for (Edge edge : current.getEdges()) {
		Node neighbour = edge.getOther(current);
		int cost = ((Marker) current.getMarker()).cost + edge.getWeight();

		if (neighbour.getMarker() == null) {
		    neighbour.setMarker(new Marker(current, cost));
		    visited.add(neighbour);
		}

		// Overwrite marker if new route is cheaper
		if (cost < ((Marker) neighbour.getMarker()).cost) {
		    visited.remove(neighbour);
		    neighbour.setMarker(new Marker(current, cost));
		    visited.add(neighbour);
		}
	    }

	    current = visited.poll();
	}

	return deriveDirectionsFromPath(current);
    }

    private Stack<EOrientation> deriveDirectionsFromPath(Node target) {
	Stack<EOrientation> moves = new Stack<>();

	Node previous = ((Marker) target.getMarker()).previous;
	while (previous != null) {
	    moves.add(previous.getDirection(target));

	    target = previous;
	    previous = ((Marker) target.getMarker()).previous;
	}

	return moves;
    }

    class Marker implements Comparable<Marker> {
	public Node previous;
	public int cost;

	public Marker(Node previous, int cost) {
	    this.previous = previous;
	    this.cost = cost;
	}

	@Override
	public int compareTo(Marker o) {
	    return this.cost - o.cost;
	}
    }
}
