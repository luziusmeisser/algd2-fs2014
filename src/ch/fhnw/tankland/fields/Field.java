package ch.fhnw.tankland.fields;

import java.awt.Color;
import java.awt.Graphics2D;

import ch.fhnw.tankland.geometry.Position;
import ch.fhnw.tankland.strategy.IField;
import ch.fhnw.tankland.tanks.Bonus;
import ch.fhnw.tankland.tanks.Tank;

public class Field implements IField {

	private static final double ICE_BELOW = 8;

	private static final Color ICE_COLD = new Color(200, 200, 230, 255);
	private static final Color GRASS = new Color(60, 170, 95, 255);
	private static final Color DESERT_HOT = new Color(240, 140, 64, 255);
	private static final Color WATER = new Color(30, 80, 180, 255);
	private static final Color WATER_WARM = new Color(40, 100, 160, 255);

	private double humidity; // from 0 to 100
	private double temperature; // from 0 to 100
	private Position pos;

	private double sunshineReceived;
	private double evaporation;

	private int flowers;
	private Tank tank;
	private Bonus bonus;

	public Field(Position pos) {
		this.pos = pos;
		this.temperature = 0;
		this.humidity = 0;
		this.sunshineReceived = 0;
		this.flowers = 0;
	}

	public boolean hasBonus() {
		return bonus != null;
	}
	
	public void setBonus(Bonus bonus2) {
		this.bonus = bonus2;
	}

	public int getTravelCost() {
		if (isFrozen()) {
			return 80;
		} else if (isWater()) {
			return 500;
		} else {
			return 20 + flowers * 10;
		}
	}

	public void notifyPlantAdded() {
		this.flowers++;
	}

	public void notifyPlantRemoved() {
		this.flowers--;
	}

	public boolean canHaveMorePlants() {
		return flowers < 8;
	}

	public void notifyTankEntered(Tank tank) {
		assert this.tank != tank;
		if (this.tank != null){
			this.tank.destroy();
		}
		this.tank = tank;
		if (bonus != null){
			bonus.applyTo(tank);
		}
	}

	public void notifyTankLeft(Tank tank) {
		assert this.tank == tank;
		this.tank = null;
	}

	public boolean hasTank() {
		return tank != null;
	}

	public double getHumidity() {
		return humidity;
	}

	public double getTemperature() {
		return temperature;
	}

	public void receiveSunshine(double amount) {
		temperature = Math.min(100, amount + temperature);
		sunshineReceived = amount;
	}

	public void receiveRain(double amount) {
		humidity = Math.min(100, amount + humidity);
	}

	public void updateConditions() {
		// Je fuechter und waermer der Boden ist, desto mehr Wasser wird verdunsten.
		evaporate();
		radiate();
	}

	private void radiate() {
		double cooldown = temperature * temperature / 10000 / 50;
		temperature -= cooldown;
	}

	private void evaporate() {
		evaporation = humidity * temperature * temperature / 1000000;
		humidity -= evaporation;
		temperature -= evaporation; // Wenn Wasser verdunstet, wird es kuehler
	}

	public void draw(Graphics2D g, int x, int y, int size) {
		Color color = findColor();
		if (sunshineReceived > 0.01) {
			Color brighter = color.brighter();
			color = mix(color, brighter, sunshineReceived / 0.2);
		}
		g.setColor(color);
		g.fillRect(x, y, size, size);
		g.setColor(color.darker().darker());
		g.drawString(Integer.toString((int) getTravelCost()), x, y + 10);
		if (bonus != null){
			bonus.draw(g, x, y);
		}
		// g.drawString(Integer.toString((int) temperature), x, y + 10);
		// g.drawString(Integer.toString((int) humidity), x, y + 20);
	}

	private Color mix(Color color, Color brighter, double factor) {
		return new Color(mix(color.getRed(), brighter.getRed(), factor), mix(color.getGreen(), brighter.getGreen(), factor), mix(color.getBlue(), brighter.getBlue(), factor));
	}

	private int mix(int base, int direction, double factor) {
		return (int) (base + (direction - base) * factor);
	}

	public boolean isGoodPlaceForNewTank() {
		return !hasTank() && canTankSurvive();
	}

	public boolean canTankSurvive() {
		return !isWater();
	}

	public double getSunshine() {
		return sunshineReceived;
	}

	public boolean isFrozen() {
		return temperature < ICE_BELOW;
	}

	public boolean isDesert() {
		return temperature > 70 && humidity < 20;
	}

	private Color findColor() {
		if (isFrozen()) {
			return ICE_COLD;
		} else {
			Color base = null;
			if (isWater()) {
				Color water = mix(WATER, WATER_WARM, temperature / 100);
				if (temperature < 12) {
					return mix(ICE_COLD, water, (temperature - ICE_BELOW) / (12 - ICE_BELOW));
				} else {
					return water;
				}
			} else {
				base = mix(GRASS, DESERT_HOT, (73 + temperature - humidity) / 173);
				if (temperature < 15) {
					return mix(ICE_COLD, base, (temperature - ICE_BELOW) / (15 - ICE_BELOW));
				} else {
					return base;
				}
			}
		}
	}

	public boolean isWater() {
		return humidity > 85;
	}

	public double getEvaporation() {
		return evaporation;
	}

	public Position getPosition() {
		return pos;
	}

	@Override
	public String toString() {
		return pos + " t" + ((int) temperature);
	}

}
