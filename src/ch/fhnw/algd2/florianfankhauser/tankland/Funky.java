package ch.fhnw.algd2.florianfankhauser.tankland;

import java.util.LinkedList;
import java.util.PriorityQueue;
import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

public class Funky implements IStrategy {
	boolean scanned = false;
	boolean found = false;
	private LinkedList<ETankAction> moves = new LinkedList<>();

	@Override
	public int getColor() {
		return 6;
	}

	@Override
	public String getComment() {
		return "Hello World";
	}

	@Override
	public String getName() {
		return "Funky";
	}

	@Override
	public ETankAction getNextAction(Situation situation) {
		// CHECK NEIGHBOURS
		boolean watchout = false;
		EOrientation dir = null;
		situation.getCurrentField();
		if (situation.getNeighbor(EOrientation.NORTH).hasTank()) {
			watchout = true;
			dir = EOrientation.NORTH;
		} else if (situation.getNeighbor(EOrientation.EAST).hasTank()) {
			watchout = true;
			dir = EOrientation.EAST;
		} else if (situation.getNeighbor(EOrientation.SOUTH).hasTank()) {
			watchout = true;
			dir = EOrientation.SOUTH;
		} else if (situation.getNeighbor(EOrientation.WEST).hasTank()) {
			watchout = true;
			dir = EOrientation.WEST;
		}
		if (watchout) {
			scanned = false;
			found = false;
			return situation.getOrientation().deriveTankAction(dir);
		}
		
		// LOOK FOR CHERRY
		if (!scanned) {
			scanned = true;
			return ETankAction.SCAN;
		}
		if (!found) {
			NodeWrapper cherry = null;
			try {
				cherry = calculateCherryPath(situation.getGraph());
				if (cherry != null) {
					found = true;
					moves = calculateMoves(cherry, situation.getOrientation());
				}
			} catch (NullPointerException e) {}
		}
		if (moves.size() > 0) {
			return moves.removeFirst();
		}
		scanned = false;
		found = false;
		return ETankAction.PAUSE;
	}

	private NodeWrapper calculateCherryPath(Node node) {
		PriorityQueue<NodeWrapper> unbesucht = new PriorityQueue<>();
		node.setMarker(0);
		unbesucht.add(new NodeWrapper(node, null));
		NodeWrapper wrap = unbesucht.poll();
		int counter = 0;
		while (node != null && !found) {
			for (Edge e : wrap.node.getEdges()) {
				Node n = e.getOther(wrap.node);
				if (n.hasBonus()) {
					wrap = new NodeWrapper(n, wrap);
					return wrap.fillPath();
				} else {
					int newWeight = (int) wrap.node.getMarker() + e.getWeight();	
					if (n.getMarker() != null) {
						if ((int) n.getMarker() > newWeight) {
							if (unbesucht.contains(n)) {
								unbesucht.remove(n);
							}
							n.setMarker(newWeight);
							unbesucht.add(new NodeWrapper(n, wrap));
						}
					} else {
						n.setMarker(newWeight);
						unbesucht.add(new NodeWrapper(n, wrap));
					}
				}
			}
			wrap = unbesucht.poll();
		}
		return null;
	}
	
	private LinkedList<ETankAction> calculateMoves(NodeWrapper node, EOrientation or) {
		LinkedList<ETankAction> moves = new LinkedList<>();
		int counter = 0;
		while (node.next != null) {
			EOrientation orNext = node.node.getDirection(node.next.node);
			while (orNext != or) {
				if (or.ordinal() - orNext.ordinal() > 0) {
					moves.add(ETankAction.LEFT);
					or = or.left();
				} else {
					moves.add(ETankAction.RIGHT);
					or = or.right();
				}
			}
			if (orNext == or) {
				moves.add(ETankAction.FORWARD);
			}
			node = node.next;
		}
		return moves;
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
