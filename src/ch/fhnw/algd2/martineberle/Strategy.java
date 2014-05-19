//Copied by Martin Eberle on 15.05.2014

package ch.fhnw.algd2.martineberle;

import java.util.PriorityQueue;

import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

public class Strategy implements IStrategy{

	@Override
	public int getColor() {
		return 3;
	}

	@Override
	public String getComment() {
		return "haha";
	}

	@Override
	public String getName() {
		return "Knalli der zerstöri";
	}

	@Override
	public ETankAction getNextAction(Situation situation) {
		if (situation.getGraph() == null) {
			return ETankAction.SCAN;
		} else {
			Node current = situation.getGraph();
			MyStepMarker marker = (MyStepMarker) current.getMarker();
			if (marker == null) {
				PriorityQueue<MyStepMarker> markers = new PriorityQueue<>();
				marker = new MyStepMarker(current);
				markers.add(marker);
				fillMap(markers);
			}
			EOrientation o = marker.getDirection();
			return situation.getOrientation().deriveTankAction(o);
		}
	}
	
	private void fillMap(PriorityQueue<MyStepMarker> markers) {
		while (markers.size() > 0) {
			MyStepMarker best = markers.poll();
			if (best.isCurrent()) {
				Node current = best.getNode();
				if (current.hasBonus()) {
					best.fillPath();
					break;
				} else {
					for (Edge e : current.getEdges()) {
						int weight = best.getWeight() + e.getWeight();
						Node neighbor = e.getOther(current);
						MyStepMarker nbMarker = (MyStepMarker) neighbor.getMarker();
						if (nbMarker == null || nbMarker.getWeight() > weight) {
							markers.add(new MyStepMarker(current, neighbor, weight));
						}
					}
				}
			}
		}
	}
}
