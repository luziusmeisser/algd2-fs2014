// Created by Stephen Randles 11.05.2014

package ch.fhnw.algd2.stephenrandles.tankland;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

import ch.fhnw.tankland.fields.Field;
import ch.fhnw.tankland.fields.Land;
import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

public class ShortestPathStrategy implements IStrategy {
	private Stack<EOrientation> movesToCherry = new Stack<>();
	

	@Override
	public int getColor() {
		return 3;
	}

	@Override
	public String getComment() {
		return "Cole Train";
	}

	@Override
	public String getName() {
		return "Stephen";
	}

	@Override
	public ETankAction getNextAction(Situation situation) {
		if (movesToCherry.isEmpty()) {
			if (situation.getGraph() == null) {
				return ETankAction.SCAN;
			} else { 
				movesToCherry = findShortestPathToCherry(situation);
			}
		}

		if (!movesToCherry.isEmpty()) {
			EOrientation whereToGo = movesToCherry.pop();
			return situation.getOrientation().deriveTankAction(whereToGo);
		} else {
			return ETankAction.PAUSE;
		}
	}
	
	private Stack<EOrientation> findShortestPathToCherry(Situation situation) {
		PriorityQueue<Node> visited = new PriorityQueue<>(5, new Comparator<Node>() {
			public int compare (Node a, Node b) {
				return ((Marker)a.getMarker()).compareTo(((Marker)b.getMarker()));
			}
		});
		
		Node current = situation.getGraph();
		current.setMarker(new Marker(null, 0));
		visited.add(current);
		
		boolean foundCherry = false;
		while (!foundCherry) {
						
			for (Edge edge : current.getEdges()) {
				Node neighbour = edge.getOther(current);
				int cost = ((Marker)current.getMarker()).cost + neighbour.getTravelCost();
				
				if (neighbour.getMarker() == null) {
					neighbour.setMarker(new Marker(current, cost));
					visited.add(neighbour);
				}
				
				// Overwrite marker if new route is cheaper
				if ( cost < ((Marker)neighbour.getMarker()).cost  ) {
					visited.remove(neighbour);
					neighbour.setMarker(new Marker(current, cost));
					visited.add(neighbour);
				}
				
				if(neighbour.hasBonus()) foundCherry = true;
			}
			
			current = visited.peek();			
		}
		
		return deriveDirectionsFromPath(visited.peek());
	}
	
	
	private Stack<EOrientation> deriveDirectionsFromPath(Node target) {
		Node previous;
		Stack<EOrientation> moves = new Stack<>();

		do {			
			previous = ((Marker)target.getMarker()).previous;
			moves.add(previous.getDirection(target));
			
			target = previous;
		} while ( target.getMarker() != null );
		
		
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
