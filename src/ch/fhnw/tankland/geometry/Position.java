package ch.fhnw.tankland.geometry;

import java.util.Random;

import ch.fhnw.tankland.tanks.EOrientation;

public class Position {

	// Wir merken uns die Position "exakt", auch wenn nur ganze Pixel angezeigt werden können
	private double x, y;

	private Bounds bounds;

	public Position(Random rand, Bounds bounds) {
		this(bounds, rand.nextDouble() * bounds.getWidth(), rand.nextDouble() * bounds.getHeight());
	}

	public Position(Bounds bounds, double x, double y) {
		this.bounds = bounds;
		this.x = x;
		this.y = y;
	}

	public void moveLeft() {
		this.x = bounds.checkX(x - 1);
	}

	public void moveRight() {
		this.x = bounds.checkX(x + 1);
	}

	public void moveUp() {
		this.y = bounds.checkY(y - 1);
	}

	public void moveDown() {
		this.y = bounds.checkY(y + 1);
	}

	public void move(EOrientation dir) {
		switch (dir) {
		case EAST:
			moveRight();
			break;
		case NORTH:
			moveUp();
			break;
		case SOUTH:
			moveDown();
			break;
		case WEST:
			moveLeft();
			break;
		}
	}

	public void moveBy(Vector speed) {
		this.x = bounds.checkX(x + speed.getX());
		this.y = bounds.checkY(y + speed.getY());
	}

	public int getRoundedY() {
		return (int) y;
	}

	public int getRoundedX() {
		return (int) x;
	}

	public int getPixelPositionX() {
		return bounds.convertToPixelPosition(x);
	}

	public int getPixelPositionY() {
		return bounds.convertToPixelPosition(y);
	}

	public Position getRoundedCopy() {
		return new Position(bounds, getRoundedX(), getRoundedY());
	}
	
	public Position copy() {
		return new Position(bounds, x, y);
	}
	
	@Override
	public int hashCode(){
		return getPixelPositionX() << Short.SIZE | getPixelPositionY();
	}
	
	@Override
	public boolean equals(Object o){
		Position p = (Position)o;
		return p.x == x && p.y == y;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public Vector getDistanceVector(Position pos) {
		return new Vector(pos.x - x, pos.y - y);
	}

	public double getDistanceTo(Position pos) {
		double xdist = calcDist(x, pos.x, bounds.getWidth());
		double ydist = calcDist(y, pos.y, bounds.getHeight());
		return Math.sqrt(xdist * xdist + ydist * ydist);
	}

	private double calcDist(double first, double second, int wrap) {
		if (first > second) {
			// first is bigger -> switch them
			return calcDist(second, first, wrap);
		} else {
			// first is smaller
			double distDirect = second - first;
			assert distDirect >= 0;
			assert distDirect <= wrap;
			if (distDirect > wrap / 2) {
				// the other way around the globe is closer
				return wrap - distDirect;
			} else {
				return distDirect;
			}
		}
	}

	public int getTileSize() {
		return bounds.getFieldSize();
	}

}
