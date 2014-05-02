// Created by Luzius on May 2, 2014

package ch.fhnw.tankland.tanks;

import ch.fhnw.tankland.strategy.IField;
import ch.fhnw.tankland.strategy.Situation;


public enum ETankAction {

	LEFT, RIGHT, FORWARD, SCAN, PAUSE;

	public int calcWork(Situation s) {
		switch (this){
		default:
		case PAUSE:
			return 10;
		case SCAN:
			return s.applyBonus(200);
		case LEFT:
		case RIGHT:
			return s.applyBonus(s.getCurrentField().getTravelCost());
		case FORWARD:
			IField f1 = s.getCurrentField();
			IField f2 = s.getNeighbor(s.getOrientation()); 
			return s.applyBonus((f1.getTravelCost() + f2.getTravelCost()) / 2);
		}
	}
	
}
