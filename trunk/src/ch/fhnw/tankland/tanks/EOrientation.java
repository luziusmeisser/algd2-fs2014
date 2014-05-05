// Created by Luzius on May 2, 2014

package ch.fhnw.tankland.tanks;

public enum EOrientation {

	NORTH, EAST, SOUTH, WEST;

	public EOrientation left() {
		return EOrientation.values()[(ordinal() + 3) % EOrientation.values().length];
	}

	public EOrientation right() {
		return EOrientation.values()[(ordinal() + 1) % EOrientation.values().length];
	}
	
	public ETankAction deriveTankAction(EOrientation target){
		if (target == this){
			return ETankAction.FORWARD;
		} else if ((ordinal() + 1)%EOrientation.values().length == target.ordinal()){
			return ETankAction.LEFT;
		} else {
			return ETankAction.RIGHT;
		}
	}

	public String getFileName() {
		return "data/tanks-" + name().toLowerCase() + ".png";
	}

	public EOrientation opposite() {
		switch (this) {
		default:
		case EAST:
			return WEST;
		case WEST:
			return EAST;
		case NORTH:
			return SOUTH;
		case SOUTH:
			return NORTH;
		}
	}

}
