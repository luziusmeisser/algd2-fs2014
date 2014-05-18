//created by lukas.musy on May 18, 2014

package ch.fhnw.algd2.lukasmusy;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

public class MyStrategy implements IStrategy{

private Stack<EOrientation> movesToCherry = new Stack<>();
	

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
		return "Lukas Musy";
	}

	@Override
	public ETankAction getNextAction(Situation situation) {
		if (situation.getGraph() == null) {
			return ETankAction.SCAN;
		}
		
		if (movesToCherry.isEmpty()) { 
			movesToCherry = findShortestPath(situation);
		}

		if (!movesToCherry.isEmpty()) {
			EOrientation whereToGo = movesToCherry.peek();
			ETankAction action = situation.getOrientation().deriveTankAction(whereToGo);
			
			if (action == ETankAction.FORWARD) movesToCherry.pop();		
			return action;
			
		} else {
			return ETankAction.PAUSE;
		}
	}
	
	private Stack<EOrientation> findShortestPath(Situation situation) {
		PriorityQueue<Node> visited = new PriorityQueue<>(5, new Comparator<Node>() {
			public int compare (Node a, Node b) {
				return ((Marker)a.getMarker()).compareTo(((Marker)b.getMarker()));
			}
		});
		
		Node current = situation.getGraph();
		current.setMarker(new Marker(null, 0));
		visited.add(current);
		
		while (!visited.isEmpty()) {
			if(current.hasBonus()) break;
						
			for (Edge edge : current.getEdges()) {
				Node neighbour = edge.getOther(current);
				int cost = ((Marker)current.getMarker()).cost + edge.getWeight();
				
				if (neighbour.getMarker() == null) {
					neighbour.setMarker(new Marker(current, cost));
					visited.add(neighbour);
				}
				
				if ( cost < ((Marker)neighbour.getMarker()).cost  ) {
					visited.remove(neighbour);
					neighbour.setMarker(new Marker(current, cost));
					visited.add(neighbour);
				}
			}
			
			current = visited.poll();			
		}
		
		return getDirectionsFromPath(current);
	}
	
	
	private Stack<EOrientation> getDirectionsFromPath(Node target) {
		Stack<EOrientation> moves = new Stack<>();

		Node previous = ((Marker)target.getMarker()).previous;
		while (previous != null) {	
			moves.add(previous.getDirection(target));
			
			target = previous;
			previous = ((Marker)target.getMarker()).previous;
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
