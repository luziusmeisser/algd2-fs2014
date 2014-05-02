package ch.fhnw.tankland.geometry;


public abstract class Positionable {
	
	private Position position;
	
	public Positionable(Position position) {
		this.position = position;
	}
	
	public Position getPosition(){
		return position;
	}

	public void move(Vector direction) {
		position.moveBy(direction);
	}

}
