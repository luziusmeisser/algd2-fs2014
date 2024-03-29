// Created by Luzius on May 2, 2014

package ch.fhnw.tankland.strategy;

import java.util.HashMap;

import ch.fhnw.tankland.fields.Land;
import ch.fhnw.tankland.geometry.Position;
import ch.fhnw.tankland.tanks.EOrientation;

public class Situation {

	private Land l;
	private int speed;
	private Position pos;
	private EOrientation o;
	private HashMap<Position, Node> scan;

	public Situation(Land land, HashMap<Position, Node> scan, Position copy, EOrientation orientation, int speed) {
		this.scan = scan;
		this.l = land;
		this.pos = copy;
		this.o = orientation;
		this.speed = speed;
	}

	public int applyBonus(int cost){
		return cost * 100 / speed;
	}
	
	public IField[] getNeighborhood() {
		return l.getFieldsAround(pos, false);
	}
	
	public EOrientation getDirection(IField neighbor){
		for (EOrientation o: EOrientation.values()){
			if (getNeighbor(o) == neighbor){
				return o;
			}
		}
		if (getDiagonal(EOrientation.NORTH, EOrientation.EAST) == neighbor){
			return EOrientation.NORTH;
		} else  if (getDiagonal(EOrientation.EAST, EOrientation.SOUTH) == neighbor){
			return EOrientation.EAST;
		} else  if (getDiagonal(EOrientation.SOUTH, EOrientation.WEST) == neighbor){
			return EOrientation.SOUTH;
		} else if (getDiagonal(EOrientation.WEST, EOrientation.NORTH) == neighbor){
			return EOrientation.WEST;
		}
		return null;
	}
	
	public IField getNeighbor(EOrientation orientation) {
		Position pos2 = pos.copy();
		pos2.move(orientation);
		return l.getFieldAt(pos2);
	}
	
	public IField getDiagonal(EOrientation step1, EOrientation step2) {
		assert step1 != step2 && step1 != step2.opposite();
		Position pos2 = pos.copy();
		pos2.move(step1);
		pos2.move(step2);
		return l.getFieldAt(pos2);
	}
	
	public Node getGraph(){
		return scan == null ? null : scan.get(pos);
	}
	
	public Position getPosition(){
		return pos;
	}
	
	public EOrientation getOrientation(){
		return o;
	}

	public IField getCurrentField() {
		return l.getFieldAt(pos);
	}

	public int getSpeed() {
		return speed;
	}

}
