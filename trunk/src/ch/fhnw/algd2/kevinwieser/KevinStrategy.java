// Created by Kevin Wieser on 05.05.2014
package ch.fhnw.algd2.kevinwieser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

public class KevinStrategy implements IStrategy {
	
	
	
	
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
	int counter = 0;

	@Override
	public int getColor() {
		return 4;
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
	
		LinkedList<ETankAction> stack = new LinkedList<>();

		stack.add(ETankAction.FORWARD);

		if (situation.getNeighbor(EOrientation.EAST).hasTank()) {
			stack.add(situation.getOrientation().deriveTankAction(
					EOrientation.EAST));
		} else if (situation.getNeighbor(EOrientation.NORTH).hasTank()) {
			stack.add(situation.getOrientation().deriveTankAction(
					EOrientation.NORTH));
		} else if (situation.getNeighbor(EOrientation.WEST).hasTank()) {
			stack.add(situation.getOrientation().deriveTankAction(
					EOrientation.WEST));
		} else if (situation.getNeighbor(EOrientation.SOUTH).hasTank()) {
			stack.add(situation.getOrientation().deriveTankAction(
					EOrientation.SOUTH));
		}

		return stack.pop();
		
//	turns.add(ETankAction.FORWARD);
		
//		if(situation.getNeighbor(EOrientation.EAST).hasTank()){
//			turns.add(situation.getOrientation().deriveTankAction(EOrientation.EAST));
//		}else if(situation.getNeighbor(EOrientation.NORTH).hasTank()){
//			turns.add(situation.getOrientation().deriveTankAction(EOrientation.NORTH));
//		}else if(situation.getNeighbor(EOrientation.WEST).hasTank()){
//			turns.add(situation.getOrientation().deriveTankAction(EOrientation.WEST));
//		}else if(situation.getNeighbor(EOrientation.SOUTH).hasTank()){
//			turns.add(situation.getOrientation().deriveTankAction(EOrientation.SOUTH));
//		}
//		
//		
//		return turns.pop();
		
		
		
//		if (counter < 10) {
//			counter++;
//			
//			if (situation.getGraph() == null) {
//				// If there no graph then scan
//				return ETankAction.SCAN;
//			}
//
//			if (cherryPath.isEmpty()) {
//				// evaluate the shortest way to the cherry
//				cherryPath = shortestWayToCherry(situation);
//			}
//
//			if (!cherryPath.isEmpty()) {
//				ETankAction next = situation.getOrientation().deriveTankAction(
//						cherryPath.peek());
//
//				if (next == ETankAction.FORWARD) {
//					cherryPath.pop();
//				}
//				return next;
//			}
//			
//			return ETankAction.FORWARD;
//		}
//		
//		cherryPath = new Stack<>();
//		counter = 0;
//
//		return ETankAction.RIGHT;
	}

	
	
	private ETankAction randomAction(){
		int zahl = (int)((Math.random()) * 4 + 1);
		
		int counter = 0;
		
		if (counter % 10 == 0) {
			counter++;
			return ETankAction.RIGHT;
		} else {
			counter++;
			return ETankAction.FORWARD;
		}
		
//		switch(zahl) {
//		case 1 : return ETankAction.FORWARD; 
//		case 2: return ETankAction.LEFT;
//		case 3: return ETankAction.RIGHT;
//		case 4: return ETankAction.PAUSE;
//		}
//		
//		return ETankAction.FORWARD;
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
