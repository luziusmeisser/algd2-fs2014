package ch.fhnw.algd2.mariusdubach.tankland;

import java.util.LinkedList;
import java.util.PriorityQueue;
import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

/**
 * 
 * Mit grosszuegiger Unterstuetzung von Florian Fankhauser! Danke
 *
 */

public class Marius2 implements IStrategy {
	
	boolean scanned = false;
	boolean found = false;
	private LinkedList<ETankAction> turns = new LinkedList<>();

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
		if(situation.getGraph() == null){
			return ETankAction.SCAN;
		}
		if (!found) {
			NodeWrapper cherry = whereIsCherry(situation.getGraph());
			if (cherry != null) {
				found = true;
				calcTurns(cherry, situation.getOrientation());
			}
		}
		if (turns.size() > 0) {
			return turns.removeFirst();
		}
		return ETankAction.FORWARD;
	}

	private NodeWrapper whereIsCherry(Node node) {
		PriorityQueue<NodeWrapper> queue = new PriorityQueue<>();
		node.setMarker(0);
		
		NodeWrapper wrap = new NodeWrapper(node, null);
		
		while (node != null && !found) {
			for (Edge e : wrap.node.getEdges()) {
				Node n = e.getOther(wrap.node);
				if (n.hasBonus()) {

					wrap = new NodeWrapper(n, wrap);
					return wrap.fillPath();
				} else {
					int costsToSet = (int) wrap.node.getMarker() + e.getWeight();	
					if (n.getMarker() != null) {
						if ((int) n.getMarker() > costsToSet) {
							n.setMarker(costsToSet);
							queue.add(new NodeWrapper(n, wrap));
						}
					} else {
						n.setMarker(costsToSet);
						queue.add(new NodeWrapper(n, wrap));
					}
				}
			}
			wrap = queue.poll();
		}
		return null;
	}
	
	private void calcTurns(NodeWrapper node, EOrientation or) {
		while (node.next != null) {
			EOrientation orNext = node.node.getDirection(node.next.node);
			while (orNext != or) {
				if (or.ordinal() - orNext.ordinal() > 0) {
					turns.add(ETankAction.LEFT);
					or = or.left();
				} else {
					turns.add(ETankAction.RIGHT);
					or = or.right();
				}
			}
			if (orNext == or) {
				turns.add(ETankAction.FORWARD);
			}
			node = node.next;
		}
	}
	
	private class NodeWrapper implements Comparable {
		private Node node;
		private NodeWrapper previous;
		private NodeWrapper next;
		
		private NodeWrapper(Node node, NodeWrapper previous) {
			this.node = node;
			this.previous = previous;
		}

		@Override
		public int compareTo(Object arg0) {
			if (arg0 == this || arg0 == node || ((NodeWrapper) arg0).node == node) {
				return 0;
			}
			return (int) node.getMarker() - (int) ((NodeWrapper) arg0).node.getMarker();
		}
		
		private NodeWrapper fillPath() {
			if (previous != null) {
				previous.next = this;
				return previous.fillPath();
			} else {
				return this;
			}
 		} 
	}

}
