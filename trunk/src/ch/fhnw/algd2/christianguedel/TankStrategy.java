package ch.fhnw.algd2.christianguedel;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

public class TankStrategy implements IStrategy {

	private boolean hasScanned = false;
	private Stack<EOrientation> path;

	@Override
	public int getColor() {
		return 6;
	}

	@Override
	public String getComment() {
		return null;
	}

	@Override
	public String getName() {
		return "Christian Güdel's Strategy";
	}

	// note: currently the strategy only goes for the cherry and
	// looks for tanks in a nearby field. Could be extended to go
	// after any tank on the battlefield if the speed is high enough
	// or to avoid fields with a very high travel cost etc.
	@Override
	public ETankAction getNextAction(Situation situation) {
		// destroy any nearby tanks
		for (EOrientation e : EOrientation.values())
		{
			if (situation.getNeighbor(e).hasTank())
			{
				return situation.getOrientation().deriveTankAction(e);
			}
		}
		
		if (!this.hasScanned) {
			this.hasScanned = true;
			return ETankAction.SCAN;
		}

		if (this.path == null)
			this.path = this.findPathToBonusField(situation);
		
		if (!this.path.isEmpty()) {
			ETankAction next = situation.getOrientation().deriveTankAction(
					this.path.peek());
			// only remove if we actually moved
			if (next == ETankAction.FORWARD)
				this.path.pop();

			return next;
		// find the next cherry if the path is empty on the next turn
		} else {
			this.path = null;
			this.hasScanned = false;
		}

		return ETankAction.FORWARD;
	}

	private Stack<EOrientation> findPathToBonusField(Situation situation) {
		PriorityQueue<Node> q = new PriorityQueue<>(50, new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return ((NodeInfo) n1.getMarker()).cost - ((NodeInfo) n2.getMarker()).cost;
			}
		});

		Node current = situation.getGraph();
		current.setMarker(new NodeInfo(null, 0));
		q.add(current);

		// Traverse the map until we found the cherry (bonus) using the priority queue
		// to find the shortest path. On our way we mark each node with a marker to store
		// additional information about the node (travel cost + node we visited before).
		// Markers are not removed upon finish, so the path can be calculated only once
		// without removing them in a separate routine.
		while (!q.isEmpty() && !current.hasBonus()) {
			for (Edge e : current.getEdges()) {
				Node other = e.getOther(current);
				int cost = e.getWeight() + ((NodeInfo) current.getMarker()).cost;

				if (other.getMarker() == null) {
					other.setMarker(new NodeInfo(current, cost));
					q.add(other);
				}
			}

			current = q.poll();
		}

		Node bonus = current;
		Node previous = ((NodeInfo) current.getMarker()).previous;

		Stack<EOrientation> path = new Stack<>();

		while (previous != null) {
			path.add(previous.getDirection(bonus));
			bonus = previous;
			previous = ((NodeInfo) bonus.getMarker()).previous;
		}

		return path;
	}

	private class NodeInfo {
		public final Node previous;
		public final int cost;

		public NodeInfo(Node previous, int cost) {
			this.previous = previous;
			this.cost = cost;
		}
	}
}
