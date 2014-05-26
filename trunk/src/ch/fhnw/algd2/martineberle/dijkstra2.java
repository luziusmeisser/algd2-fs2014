// Created by Martin Eberle on 25.05.2014

package ch.fhnw.algd2.martineberle;

import java.util.LinkedList;
import java.util.PriorityQueue;
import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;
import ch.fhnw.tankland.strategy.Node;

public class dijkstra2 implements IStrategy {
	public LinkedList<WayPoint> stepsToCherry;
	public LinkedList<ETankAction> way = new LinkedList<>();

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
		if (situation.getGraph() == null){
			return ETankAction.SCAN;
		}
		else {
			Node current = situation.getGraph();
			WayPoint first = (WayPoint)current.getMarker();
			//start creating Waypoints if none exist
			if (first == null){
				first = new WayPoint();
				first.setFirst();
				first.setNode(current);
				current.setMarker(first);
				
				calculateNeighbours(new WayPoint(0, situation.getGraph()));
				convertStepsToWay(stepsToCherry, way, situation.getOrientation());
			}
		}
		
		//while(stepsToCherry.getFirst().distance > 0){
			//Node current = situation.getGraph();
//			EOrientation o = stepsToCherry.removeFirst().dest;//.opposite();
//			System.out.println(o.toString());
//			if(situation.getOrientation().equals(o)){
				return way.removeFirst();
//			}
//			else{
//				return situation.getOrientation().deriveTankAction(o);
//			}
		//}
//			else {
//				return ETankAction.FORWARD;
//			}
				//put First Waypoint into List(Hash!)
				//mark first Waypoint as First
				//get all neighbours
				//check for cherry
					//calc distance of next nodes on each neighbor
						//if distance smaller or neighbor null, update
				//mark current as visited
	}
	private void convertStepsToWay(LinkedList<WayPoint> stepsToCherry,
			LinkedList<ETankAction> way2, EOrientation or) {
		
		WayPoint from = stepsToCherry.getLast();
		WayPoint to = from.target;
		//EOrientation orNext = from.node.getDirection(to.node);
		while (to != null) {
			EOrientation orNext = from.node.getDirection(to.node);
			while (orNext != or) {
				if (or.ordinal() - orNext.ordinal() > 0) {
					way.add(ETankAction.LEFT);
					or = or.left();
				} else {
					way.add(ETankAction.RIGHT);
					or = or.right();
				}
			}
			if (orNext == or) {
				way.add(ETankAction.FORWARD);
			}
			from = to;
			to = to.target;
		}
	}

	public void calculateNeighbours(WayPoint n){
		//find all neighbours and create WayPoints
		if (!n.finalized){
			for ( EOrientation e : EOrientation.values()){
				Node next = n.node.getEdge(e).getOther(n.node);
				if((n.origin == null) || !(next.equals(n.origin.node)) || (n.finalized == false)){
					Edge nextEdge = n.node.getEdge(e);
					WayPoint neighbour = new WayPoint(n.distance+nextEdge.getWeight(),next, n);
					n.neighbours.add(neighbour);
				}
			}
			//Check if path to neighbour is shorter than previous or first time reached
			while(n.neighbours.size() > 0){
				WayPoint neighbour = n.neighbours.poll();
				if(neighbour.node.getMarker() == null || neighbour.distance < ((WayPoint)neighbour.node.getMarker()).distance){
					neighbour.node.setMarker(neighbour);
					//check for cherry when accessed new node over shortest path
					if(neighbour.node.hasBonus()){
						buildPath(neighbour);
						return;
					}
					//check Neighbours of neighbours if updated
					calculateNeighbours(neighbour);
				}
			}
		
		//Mark current node as handled
		n.finalized = true;
		}
		//Check for cherry
//		if(n.node.hasBonus()){
//			buildPath(n);
//		}
		//return null;
	}
	
	public synchronized void buildPath(WayPoint n){
		
		LinkedList<WayPoint> path = new LinkedList<>();
		WayPoint current = n;
		n.target = null;
		n.origin.target = n;
		path.add(n);
		while(!current.node.hasTank() /*&& current.origin != null*/){
			current.origin.target = current;
			current = current.origin;
			path.add(current);
		}
		//if(stepsToCherry != null)System.out.println(path.getFirst().distance+" "+stepsToCherry.getFirst().distance);
		if(stepsToCherry == null || path.getFirst().distance < stepsToCherry.getFirst().distance){
			//stepsToCherry = new Stack<WayPoint>();
			stepsToCherry = path;
			return;
		}

	}
	
	public class WayPoint implements Comparable<WayPoint>{
		int distance;
		EOrientation dest;
		Node node;
		WayPoint origin;
		WayPoint target;
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
		public WayPoint(int dist, Node node, WayPoint origin){
			this.distance = dist;
			this.node = node;
			this.origin = origin;
			this.dest = origin.node.getDirection(this.node);
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
		
		public void setDets(EOrientation e){
			this.dest = e;
		}
		
		public EOrientation getDest(WayPoint p){
			return this.node.getDirection(p.node);
		}
		
		@Override
		public int compareTo(WayPoint arg0) {
			return Integer.compare(this.distance, arg0.distance);
		}
	}
}
