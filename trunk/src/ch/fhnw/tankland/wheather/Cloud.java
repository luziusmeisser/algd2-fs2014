package ch.fhnw.tankland.wheather;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import ch.fhnw.tankland.fields.Field;
import ch.fhnw.tankland.fields.Land;
import ch.fhnw.tankland.geometry.Position;
import ch.fhnw.tankland.geometry.Positionable;
import ch.fhnw.tankland.geometry.Vector;

public class Cloud extends Positionable {
	
	private static final int ELEVATION = 40;
	
	private Vector speed;
	private int width = 50;
	private int height = 30;

	public Cloud(Position position) {
		super(position);
		this.speed = new Vector(0, 0);
	}

	public void rain(Land area) {
		Field[] around = area.getFieldsAround(getPosition(), true);
		for (int i=0; i<around.length; i++){
			around[i].receiveRain(0.2);
		}
	}
	
	public void move(Random rand, Land area) {
		Field[] fields = area.getFieldsAround(getPosition(), true);
		Field attracted = findMaxEvaporation(fields);
		Vector target = getPosition().getDistanceVector(attracted.getPosition());
		target = target.getScaledCopy(0.02);
		speed.update(rand, 0.3);
		//speed = speed.add(target);
		super.move(speed);
	}

	private Field findMaxEvaporation(Field[] fields) {
		Field max = fields[0];
		for (int i=1; i<fields.length; i++){
			if (fields[i].getHumidity() < max.getHumidity()){
				max = fields[i];
			}
		}
		return max;
	}

	public void draw(Graphics2D g) {
		Position position = getPosition();
		int pixelPosX = position.getPixelPositionX() - width / 2;
		int pixelPosY = position.getPixelPositionY() - height / 2 - ELEVATION;
		g.setColor(Color.GRAY);
		int wind = - (int)(speed.getX() * 50);
		for (int i=5; i<=width - 5; i+=5){
			g.drawLine(pixelPosX + i, pixelPosY + height / 2, pixelPosX + i + wind, pixelPosY + ELEVATION + height / 3);
		}
		g.fillOval(pixelPosX, pixelPosY, width, height);
	}

}
