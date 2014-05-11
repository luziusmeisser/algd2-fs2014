package ch.fhnw.algd2.larskessler;

import java.util.LinkedList;
import java.util.PriorityQueue;

import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

public class LarsKessler implements IStrategy {
	private boolean scanned;
	private boolean cherry;
	private PriorityQueue<StepMarker> toVisit = new PriorityQueue<StepMarker>();
	private LinkedList<ETankAction> cherryPath = new LinkedList<>();
	
	@Override
	public int getColor() {
		return 1;
	}

	@Override
	public String getComment() {
		return "Ke Chance!";
	}

	@Override
	public String getName() {
		return "Turbo Hanni";
	}

	@Override
	public ETankAction getNextAction(Situation situation) {
		if(!scanned) {
			scanned = true;
			return ETankAction.SCAN;
		} 
		if(!cherry) {
			StepMarker start = findBonus(situation.getGraph());
			if(start != null) {
				cherry = true;
				//System.out.println("found cherry");
				calcSteps(start, situation.getOrientation());
			}
		}
		if(cherryPath.size() > 0) {
			return cherryPath.removeFirst();
		}
		return ETankAction.FORWARD;
	}
	
	private StepMarker findBonus(Node position) {
		//System.out.println(position.getTravelCost());
		
		StepMarker marker = new StepMarker(null, null, position, 0);
		position.setMarker(marker);
		
		/*
		toVisit.add(marker);
		StepMarker next = toVisit.poll();
		Node source = next.getCurrent();
		*/
		
		while(position != null && !cherry) {
			for(Edge e: marker.getCurrent().getEdges()) {
				Node current = marker.getCurrent();
				Node neighbor = e.getOther(current);
				
				int distance = ((StepMarker) current.getMarker()).getWeight() + e.getWeight();
				StepMarker step = new StepMarker(null, (StepMarker) current.getMarker(), neighbor, distance);
				if(neighbor.hasBonus()) {
					return step.solvePath();
				} else {
					if(neighbor.getMarker() == null || distance < neighbor.getTravelCost()) {
						// save new marker
						if(neighbor.getMarker() != null) {
							StepMarker old = (StepMarker) neighbor.getMarker();
							toVisit.remove(old);
						}
						neighbor.setMarker(step);
						toVisit.add(step);
					}
				}
			}
			
			marker = toVisit.poll();
		}
		
		return null;
	}
	
	private void calcSteps(StepMarker start, EOrientation orientation) {
		while(start.next != null) {
			//System.out.println("entered calc while");
			EOrientation orientationNext = start.getCurrent().getDirection(start.next.getCurrent());
			while(orientation != orientationNext) {
				//System.out.println("entered second while");
				//System.out.println(orientation.ordinal());
				//System.out.println(orientationNext.ordinal());
				
				if (orientation.ordinal() - orientationNext.ordinal() > 0) {
					cherryPath.add(ETankAction.LEFT);
					orientation = orientation.left();
				} else {
					cherryPath.add(ETankAction.RIGHT);
					orientation = orientation.right();
				}
				
				//System.out.println("finished");
				/*
				cherryPath.add(ETankAction.LEFT);
				orientation = orientation.left();
				*/
			}
			cherryPath.add(ETankAction.FORWARD);
			start = start.next;
		}
	}
	
	@SuppressWarnings("rawtypes")
	class StepMarker implements Comparable {
		private StepMarker next;
		private Node current;
		private StepMarker prev;
		private int weight;
		
		StepMarker(Node current, int weight) {
			this(null, null, current, weight);
		}
		
		public StepMarker(StepMarker next, StepMarker prev, Node current, int weight) {
			this.next = next;
			this.prev = prev;
			this.current = current;
			this.weight = weight;
		}
		
		private StepMarker solvePath() {
			if(prev != null) {
				prev.next = this;
				return prev.solvePath();
			} else {
				return this;
			}
		}
		
		@Override
		public int compareTo(Object obj) {
			if(obj == this || obj == this.current) {
				return 0;
			} else {
				return this.weight - ((StepMarker) obj).getWeight();
			}
		}
		
		public Node getCurrent() {
			return current;
		}
		
		public int getWeight() {
			return weight;
		}
	}
}