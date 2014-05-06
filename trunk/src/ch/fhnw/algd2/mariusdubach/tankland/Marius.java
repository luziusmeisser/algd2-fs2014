//Created by Marius Dubach 05.05.2014

package ch.fhnw.algd2.mariusdubach.tankland;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;
import ch.fhnw.tankland.tanks.ETankAction;

public class Marius implements IStrategy{
	
	private boolean scanned = false;
	private boolean searchStarted = false;
	private Node startNode;
	private Node cherry;
	LinkedList<ETankAction> turns;
	
	@Override
	public int getColor() {
		return 7;
	}

	@Override
	public String getComment() {
		return "Huhu";
	}

	@Override
	public String getName() {
		return "Marius";
	}

	@Override
	public ETankAction getNextAction(Situation situation) {
		if(!scanned){
			scanned = true;
			return ETankAction.SCAN;
		}else if(!searchStarted){
			startNode = situation.getGraph();
			findCherry(startNode);
			calculateWay(situation.getOrientation());
			searchStarted = true;
		}else{
			if(turns.size() > 0){
				return turns.remove();
			}
		}
		return ETankAction.FORWARD;
		
	}
	
	private void calculateWay(EOrientation orientationOfTank){
		LinkedList<Node> wayPoints = new LinkedList<>();
		
		Node tmp = cherry;
		while(tmp != startNode){
			wayPoints.add(tmp);
			tmp = ((StepMarker)tmp.getMarker()).origin;
			System.out.println(tmp.getPosition());
		}
		//wayPoints is now a list with the cherry at the beginning
		turns = new LinkedList<>();
		for(Node n : wayPoints){
			EOrientation orientationToNext = n.getDirection(((StepMarker)n.getMarker()).origin).opposite();
			while(orientationToNext != orientationOfTank){		
				if(orientationOfTank.ordinal() - orientationToNext.ordinal() < 0){
					turns.add(ETankAction.RIGHT);
					orientationOfTank = orientationOfTank.right();
				}else{
					turns.add(ETankAction.LEFT);
					orientationOfTank = orientationOfTank.left();
				}
			}
			turns.add(ETankAction.FORWARD);
		}
	}
	
	private void findCherry(Node node){
		node.setMarker(new StepMarker(0, node, null));
		PriorityQueue<StepMarker> queue = new PriorityQueue<>();
		while(true){
			if(node.hasBonus()){
				cherry = node;
				return;
			}
			//First: Mark all surounding nodes and put them into my priority queue
			for(Edge e : node.getEdges()){
				StepMarker otherMarker = ((StepMarker) e.getOther(node).getMarker());
				int costsToSet = ((StepMarker)node.getMarker()).costs + e.getWeight();
				if(otherMarker == null || otherMarker.costs > costsToSet){
					queue.remove(otherMarker);
					StepMarker tmp = new StepMarker(costsToSet, e.getOther(node), node);
					e.getOther(node).setMarker(tmp);
					queue.add(tmp);
				}
			}
			node = queue.remove().mine;
		}
	}
	
	private class StepMarker implements Comparable<StepMarker>{
		
		public Node mine;
		public Node origin;
		public int costs;
		
		public StepMarker(int costs, Node mine, Node origin){
			this.costs = costs;
			this.mine = mine;
			this.origin = origin;
		}

		@Override
		public int compareTo(StepMarker o) {
			if(this.mine == o.mine){
				return 0;
			}
			return this.costs - o.costs;
		}
		
	}

}
