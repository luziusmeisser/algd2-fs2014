package ch.fhnw.tankland.geometry;

import java.util.Random;

public class Vector {

	private double distx;
	private double disty;

	public Vector(double distx, double disty) {
		this.distx = distx;
		this.disty = disty;
	}

	public Vector(Random rand, double max) {
		this.distx = rand.nextGaussian() * max;
		this.disty = rand.nextGaussian() * max;
	}

	public double getY() {
		return disty;
	}

	public double getX() {
		return distx;
	}

	public Vector getScaledCopy(double length) {
		double norm = getLength();
		return new Vector(distx / norm * length, disty / norm * length);
	}

	public Vector add(Vector target) {
		return new Vector(distx + target.distx, disty + target.disty);
	}
	
	public void update(Random rand, double max) {
		double newX = rand.nextGaussian() * max;
		double newY = rand.nextGaussian() * max;
		distx = average(distx, newX);
		disty = average(disty, newY);
	}

	public double getLength() {
		return Math.sqrt(distx * distx + disty * disty);
	}

	private double average(double dist, double neu) {
		return (dist * 31 + neu) / 32;
	}

	@Override
	public String toString() {
		return "[" + distx + " x " + disty + "]";
	}

}
