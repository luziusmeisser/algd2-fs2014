//created by lukas.musy on May 18, 2014

package ch.fhnw.algd2.lukasmusy;

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

public class MyStrategy implements IStrategy{

private Stack<EOrientation> movesToCherry = new Stack<>();

	private final int maxWay = 20;
	private final int stepsUntilScan = 10;
	private int currentSteps = 0;

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
		ETankAction action = ETankAction.PAUSE;
		
		if (situation.getGraph() == null || currentSteps % stepsUntilScan == 0) {
			currentSteps = 0;
			movesToCherry.clear();
			
			currentSteps++;
			return ETankAction.SCAN;
			
		}
		
		if (movesToCherry.empty()) {
			movesToCherry = findShortestPath(situation);
		}
		
		if (!movesToCherry.empty() && movesToCherry.size() < maxWay) {
			EOrientation whereToGo = movesToCherry.peek();
			action = situation.getOrientation().deriveTankAction(whereToGo);
			
			if (action == ETankAction.FORWARD) movesToCherry.pop();
		} else {
			int rand = (int)(Math.random() * 10);
			
			if (rand % 2 == 0) {
				action = ETankAction.FORWARD;
			} else if (rand % 5 == 0) {
				if (rand < 5) action = ETankAction.LEFT;
				else action = ETankAction.RIGHT;				
			}
			
		}
		
		currentSteps++;		
		return action;
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
