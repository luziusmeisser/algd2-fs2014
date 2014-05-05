// Created by Luzius on 05.05.2014

package ch.fhnw.algd2.luzius;

import java.util.PriorityQueue;

import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

public class DijkstraStrategy implements IStrategy {

	@Override
	public int getColor() {
		return 5;
	}

	@Override
	public String getComment() {
		return null;
	}

	@Override
	public String getName() {
		return "Edsgar";
	}

	@Override
	public ETankAction getNextAction(Situation situation) {
		if (situation.getGraph() == null) {
			return ETankAction.SCAN;
		} else {
			Node current = situation.getGraph();
			StepMarker marker = (StepMarker) current.getMarker();
			if (marker == null) {
				PriorityQueue<StepMarker> markers = new PriorityQueue<>();
				marker = new StepMarker(current);
				markers.add(marker);
				fillMap(markers);
			}
			EOrientation o = marker.getDirection();
			return situation.getOrientation().deriveTankAction(o);
		}
	}

	private void fillMap(PriorityQueue<StepMarker> markers) {
		while (markers.size() > 0) {
			StepMarker best = markers.poll();
			if (best.isCurrent()) {
				Node current = best.getNode();
				if (current.hasBonus()) {
					best.fillPath();
					break;
				} else {
					for (Edge e : current.getEdges()) {
						int weight = best.getWeight() + e.getWeight();
						Node neighbor = e.getOther(current);
						StepMarker nbMarker = (StepMarker) neighbor.getMarker();
						if (nbMarker == null || nbMarker.getWeight() > weight) {
							markers.add(new StepMarker(current, neighbor, weight));
						}
					}
				}
			}
		}
	}

}
