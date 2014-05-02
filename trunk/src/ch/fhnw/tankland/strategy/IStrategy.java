// Created by Luzius on May 2, 2014

package ch.fhnw.tankland.strategy;

import ch.fhnw.tankland.tanks.ETankAction;

public interface IStrategy {

	/**
	 * @return a number from 0 to 7
	 */
	public int getColor();
	
	public String getComment();
	
	public String getName();

	public ETankAction getNextAction(Situation situation);

}
