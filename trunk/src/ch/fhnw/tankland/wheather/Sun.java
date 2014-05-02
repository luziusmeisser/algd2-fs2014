package ch.fhnw.tankland.wheather;

import java.awt.Color;
import java.awt.Graphics2D;

import ch.fhnw.tankland.fields.Field;
import ch.fhnw.tankland.fields.Land;
import ch.fhnw.tankland.geometry.Bounds;
import ch.fhnw.tankland.geometry.Position;
import ch.fhnw.tankland.geometry.Positionable;
import ch.fhnw.tankland.geometry.Vector;

public class Sun extends Positionable {

	private static final int RADIUS = 15;
	
	private Vector direction;
	private Vector goNorth, goSouth;
	private int northTurn, southTurn;
	private double shiningRadius;

	public Sun(Bounds bounds) {
		super(new Position(bounds, bounds.getWidth() / 3, bounds.getHeight() / 2));
		this.goSouth = new Vector(0.1, 0.02); // move east and a little south
		this.goNorth = new Vector(goSouth.getX(), -goSouth.getY()); // move east and a little north
		this.direction = goSouth;
		int height = bounds.getHeight();
		this.northTurn = 0; // height / 6; TEMP
		this.southTurn = height - northTurn;
		this.shiningRadius = calcShiningRadius(bounds.getWidth(), bounds.getHeight());
	}

	private double calcShiningRadius(double width, double height) {
		return Math.sqrt(width * width + height * height) / 5; // let the sun shine across a fifth of the field in all directions 
	}

	public void shine(Land area) {
		Field[] fields = area.getAllFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			double distance = field.getPosition().getDistanceTo(getPosition());
			if (distance < shiningRadius){
				fields[i].receiveSunshine((shiningRadius - distance) / shiningRadius / 5);
			}
		}
	}

	public void move() {
		super.move(direction);
		int yPos = getPosition().getRoundedY();
		if (yPos > southTurn){
			direction = goNorth;
		} else if (yPos < northTurn){
			direction = goSouth;
		}
	}

	public void draw(Graphics2D g) {
		Position pos = getPosition();
		int pixelPosX = pos.getPixelPositionX();
		int pixelPosY = pos.getPixelPositionY();
		g.setColor(Color.YELLOW);
		g.fillOval(pixelPosX - RADIUS, pixelPosY - RADIUS, RADIUS * 2, RADIUS * 2);
		
	}

}
