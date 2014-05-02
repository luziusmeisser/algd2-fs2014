package ch.fhnw.tankland.plants;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import ch.fhnw.tankland.fields.Field;
import ch.fhnw.tankland.fields.Land;
import ch.fhnw.tankland.geometry.Position;
import ch.fhnw.tankland.geometry.Positionable;
import ch.fhnw.tankland.geometry.Vector;

public class Flower extends Positionable {
	
	private static final Color BASE_COLOR = new Color(50, 200, 50);
	private static final Color FLOWER = new Color(200, 100, 50);

	private static final int MAX_AGE = 20000;
	private static final double MAX_SIZE = 35;
	private static final int CHANCE_OF_SPREADING = 100;

	private int age;
	private double size;
	private Field field;

	public Flower(Field field, Position pos) {
		super(pos);
		this.size = 0;
		this.field = field;
	}

	public Flower live(Random rand, Land map) throws FlowerDiedException{
		if (age++ > MAX_AGE){
			throw new FlowerDiedException();
		} else if (field.isFrozen()){
			throw new FlowerDiedException();
		} else if (field.isWater()){
			throw new FlowerDiedException();
		} else if (field.isDesert()){
			throw new FlowerDiedException();
		} else if (field.getHumidity() < 10){
			throw new FlowerDiedException();
		} else if (field.getHumidity() >= 20 && field.getTemperature() >= 20 && field.getTemperature() <= 60){
			if (size < MAX_SIZE){
				this.size += field.getSunshine() + 0.01;
				return null;
			} else if (rand.nextInt(CHANCE_OF_SPREADING) == 0){
				return createOffspring(rand, map);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	private Flower createOffspring(Random rand, Land map) {
		Position newPos = getPosition().copy();
		newPos.moveBy(new Vector(rand, 0.5));
		Field field = map.getFieldAt(newPos);
		if (field.canHaveMorePlants()){
			return new Flower(field, newPos);
		} else {
			return null;
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(BASE_COLOR);
		Position pos = getPosition();
		int x = pos.getPixelPositionX();
		int y = pos.getPixelPositionY();
		int headx = x + (int)(size / 10);
		int heady = y - (int) size;
		g.drawLine(x, y, headx, heady);
		int radius = (int) Math.sqrt(size);
		if (radius > 0){
			g.setColor(FLOWER);
			g.fillOval(headx - radius, heady - radius, radius * 2, radius * 2);
		}
	}

	public Field getField() {
		return field;
	}

}
