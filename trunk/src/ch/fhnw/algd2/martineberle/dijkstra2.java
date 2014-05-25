// Created by Martin Eberle on 25.05.2014

package ch.fhnw.algd2.martineberle;

import java.util.PriorityQueue;
import java.util.Stack;

import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;
import ch.fhnw.tankland.strategy.Node;

public class dijkstra2 implements IStrategy {
	public Stack<WayPoint> stepsToCherry;
	public PriorityQueue<Stack<WayPoint>> shortPath = new PriorityQueue<>();
	public boolean moved;
	public EOrientation last;

	@Override
	public int getColor() {
		return 1;
	}

	@Override
	public String getComment() {
		return null;
	}

	@Override
	public String getName() {
		return "Martin";
	}

	@Override
	public ETankAction getNextAction(Situation situation) {
		Node current = situation.getGraph();
		if (situation.getGraph() == null){
			moved = false;
			return ETankAction.SCAN;
		}
		else {
			//get current position of tank
			
			if (current.getMarker() == null){
				WayPoint first = new WayPoint();
				first.setFirst();
				first.setNode(current);
				
				calculateNeighbours(new WayPoint(0, situation.getGraph()));
			}
		}
			if(stepsToCherry.peek() != null){
				current = situation.getGraph();
					EOrientation o = current.getDirection(stepsToCherry.pop().node);
					moved = true;
					return situation.getOrientation().deriveTankAction(o);
			}
			else {
				return ETankAction.FORWARD;
			}
				//put First Waypoint into List(Hash!)
				//mark first Waypoint as First
				//get all neighbours
				//check for cherry
					//calc distance of next nodes on each neighbor
						//if distance smaller or neighbor null, update
				//mark current as visited
	}
	public void calculateNeighbours(WayPoint n){
		//find all neighbours and create WayPoints
		//Stack<WayPoint> path = null;
		for ( EOrientation e : EOrientation.values()){
			Node next = n.node.getEdge(e).getOther(n.node);
			if((n.origin == null) || !(next.equals(n.origin.node)) || (n.finalized == false)){
				Edge nextEdge = n.node.getEdge(e);
				WayPoint neighbour = new WayPoint(n.distance+nextEdge.getWeight(),next, n);
				n.neighbours.offer(neighbour);
			}
		}
		//Check if path to neighbour is shorter than previous or first time reached
		while(n.neighbours.size() > 0){
			WayPoint neighbour = n.neighbours.poll();
			if(neighbour.node.getMarker() == null || neighbour.distance < ((WayPoint)neighbour.node.getMarker()).distance){
				neighbour.node.setMarker(neighbour);
				//check Neighbours of neighbours if updated
				calculateNeighbours(neighbour);
			}
		}
		//Mark current node as handled
		n.finalized = true;
		//Check for cherry
		if(n.node.hasBonus()){
			buildPath(n);
		}
		//return null;
	}
	
	public synchronized void buildPath(WayPoint n){
		
		Stack<WayPoint> path = new Stack<>();
		WayPoint current = n;
		path.push(n);
		while(!current.node.hasTank() && current.origin != null){
			current = current.origin;
			path.push(current);
		}
		if(path.size() > 0 && (stepsToCherry == null || (path.peek().distance < stepsToCherry.peek().distance))){
			stepsToCherry = path;
		}
	}
	
	public class WayPoint implements Comparable<WayPoint>{
		int distance;
		Node node;
		WayPoint origin;
		PriorityQueue<WayPoint> neighbours = new PriorityQueue<>();
		boolean finalized = false;
		
		public WayPoint(){
			this.distance = Integer.MAX_VALUE;
			this.node = null;
			this.origin = null;
		}
		
		public WayPoint(int i, Node n){
			this.distance = i;
			this.node = n;
			this.origin = null;
		}
		public WayPoint(int dist, Node next, WayPoint origin){
			this.distance = dist;
			this.node = next;
			this.origin = origin;
		}
		public void setDist(int i){
			this.distance = i;
		}
		public void setNode(Node n){
			this.node = n;
		}
		
		public void setOrigin(WayPoint w){
			this.origin = w;
		}
		
		public void setFirst(){
			this.distance = 0;
		}
		
		@Override
		public int compareTo(WayPoint arg0) {
			return Integer.compare(this.distance, arg0.distance);
		}
	}
}
