// Created by Kevin Wieser on 05.05.2014
package ch.fhnw.algd2.kevinwieser;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

public class Strategy implements IStrategy {
	
	private class NodeMarker {
		// This class stores all relevant information for a single node
		private Node before;
		private int cost;
		
		NodeMarker(Node before, int cost) {
			this.before = before;
			this.cost = cost;
		}	
	}
	
	// Contains all nodes, which represent the shortest way to the cherry
	private Stack<EOrientation> cherryPath = new Stack<>();
	

	@Override
	public int getColor() {
		return 5;
	}

	@Override
	public String getComment() {
		return "Cherry Bomber";
	}

	@Override
	public String getName() {
		return "Kevin";
	}

	@Override
	public ETankAction getNextAction(Situation situation) {
		if (situation.getGraph() == null) {
			// If there no graph then scan
			return ETankAction.SCAN;
		}

		if (cherryPath.isEmpty()) {
			// evaluate the shortest way to the cherry
			cherryPath = shortestWayToCherry(situation);
		}

		if (!cherryPath.isEmpty()) {
			ETankAction next = situation.getOrientation().deriveTankAction(
					cherryPath.peek());

			if (next == ETankAction.FORWARD) {
				cherryPath.pop();
			}
			return next;
		}
		return ETankAction.FORWARD;
	}

	private Stack<EOrientation> shortestWayToCherry(Situation situation) {
		// this function returns a stack, which contains a list of moves to perform the shortest way to the cherry  
		PriorityQueue<Node> moves = new PriorityQueue<>(50, new Comparator<Node>() {
			public int compare(Node a, Node b) {
				return ((NodeMarker) a.getMarker()).cost - ((NodeMarker)b.getMarker()).cost;
			}
		});
		
		Node current = situation.getGraph();
		current.setMarker(new NodeMarker(null, 0));
		moves.add(current);
		
		while(!moves.isEmpty() && !current.hasBonus()) {
			for (Edge e: current.getEdges()) {
				Node neighbour = e.getOther(current);
				// cost = All cost to the current Node summarized + cost from the new edge
				int cost = ((NodeMarker) current.getMarker()).cost + e.getWeight();
				
				if (neighbour.getMarker() == null) {
					neighbour.setMarker(new NodeMarker(current, cost));
					moves.add(neighbour);
				}
				
			}
			
			current = moves.poll();
		}
		// Now we have the node (current) with the cherry on it
		return listAllMoves(current);
	}

	private Stack<EOrientation> listAllMoves(Node cherry) {
		Stack<EOrientation> path = new Stack<>();
		
		Node before = ((NodeMarker)cherry.getMarker()).before;
		
		while(before != null) {
			path.add(before.getDirection(cherry));
			cherry = before;
			before = ((NodeMarker)cherry.getMarker()).before;
		}
		
		return path;
	}

}
