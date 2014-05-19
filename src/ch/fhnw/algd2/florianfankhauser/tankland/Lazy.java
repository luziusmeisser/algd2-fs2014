package ch.fhnw.algd2.florianfankhauser.tankland;

import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.Node;
import ch.fhnw.tankland.strategy.Situation;
import ch.fhnw.tankland.tanks.EOrientation;
import ch.fhnw.tankland.tanks.ETankAction;

public class Lazy implements IStrategy {

	@Override
	public int getColor() {
		return 2;
	}

	@Override
	public String getComment() {
		return "slow down - take it easy ;-)";
	}

	@Override
	public String getName() {
		return "Lazy";
	}

	@Override
	public ETankAction getNextAction(Situation situation) {
		EOrientation dir = null;
		situation.getCurrentField();
		if (situation.getNeighbor(EOrientation.NORTH).hasTank()) {
			dir = EOrientation.NORTH;
		} else if (situation.getNeighbor(EOrientation.EAST).hasTank()) {
			dir = EOrientation.EAST;
		} else if (situation.getNeighbor(EOrientation.SOUTH).hasTank()) {
			dir = EOrientation.SOUTH;
		} else if (situation.getNeighbor(EOrientation.WEST).hasTank()) {
			dir = EOrientation.WEST;
		}
		if (dir != null) {
			return situation.getOrientation().deriveTankAction(dir);
		}
		return ETankAction.PAUSE;
	}

}
