// Created by Luzius on May 2, 2014

package ch.fhnw.tankland.strategy.example;

import java.util.Random;

import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.ETankAction;

public class RandomStrategy implements IStrategy {
	
	private Random rand;
	
	public RandomStrategy(Random rand){
		this.rand = rand;
	}

	public int getColor() {
		return 6;
	}

	public String getComment() {
		return null;
	}

	public ETankAction getNextAction(Situation situation) {
		int next = rand.nextInt(7);
		if (next == 0){
			return ETankAction.LEFT;
		} else if (next == 1){
			return ETankAction.RIGHT;
		} else {
			return ETankAction.FORWARD;
		}
	}

	public String getName() {
		return "Random";
	}

}
