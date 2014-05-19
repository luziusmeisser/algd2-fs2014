//Created by Marius Dubach 05.05.2014

package ch.fhnw.algd2.mariusdubach.tankland;

import java.util.LinkedList;

import ch.fhnw.tankland.strategy.Edge;
import ch.fhnw.tankland.strategy.IField;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.ETankAction;

public class Marius implements IStrategy{
	
	LinkedList<ETankAction> turns = new LinkedList<>();
	
	@Override
	public int getColor() {
		return 0;
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
		if(turns.size() == 0){
			fillList();
		}
		return turns.poll();
		
	}
	
	private void fillList(){
		turns.add(ETankAction.FORWARD);
		turns.add(ETankAction.LEFT);
		turns.add(ETankAction.FORWARD);
		turns.add(ETankAction.LEFT);
		turns.add(ETankAction.FORWARD);
		turns.add(ETankAction.LEFT);
		turns.add(ETankAction.FORWARD);
		turns.add(ETankAction.FORWARD);
		turns.add(ETankAction.LEFT);
		turns.add(ETankAction.FORWARD);
		turns.add(ETankAction.FORWARD);		
	}

}
