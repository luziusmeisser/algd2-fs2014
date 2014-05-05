// Created by Luzius on May 2, 2014

package ch.fhnw.tankland.strategy.example;

import ch.fhnw.tankland.strategy.IField;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

public class DerekZoolander implements IStrategy {

	private int step = 0;
	private int circle = 1;

	public int getColor() {
		return 3;
	}

	public String getComment() {
		return null;
	}

	public ETankAction getNextAction(Situation s) {
		for (EOrientation o : EOrientation.values()) {
			IField nb = s.getNeighbor(o);
			if (nb.hasBonus() || nb.hasTank()) {
				if (o == s.getOrientation()) {
					return ETankAction.FORWARD;
				} else {
					return ETankAction.RIGHT;
				}
			}
		}
		IField front = s.getNeighbor(s.getOrientation());
		if (front.getTravelCost() > 100) {
			return ETankAction.RIGHT;
		} else if (step >= circle) {
			circle++;
			if (circle == 17) {
				circle = 1;
			}
			step = 0;
			return ETankAction.RIGHT;
		} else {
			step++;
			return ETankAction.FORWARD;
		}
	}

	public String getName() {
		return "Derek";
	}

}
