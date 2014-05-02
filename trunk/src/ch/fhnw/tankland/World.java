package ch.fhnw.tankland;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import ch.fhnw.tankland.fields.Field;
import ch.fhnw.tankland.fields.Land;
import ch.fhnw.tankland.geometry.Bounds;
import ch.fhnw.tankland.geometry.Position;
import ch.fhnw.tankland.plants.Flora;
import ch.fhnw.tankland.plants.Flower;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.example.DerekZoolander;
import ch.fhnw.tankland.strategy.example.RandomStrategy;
import ch.fhnw.tankland.tanks.Bonus;
import ch.fhnw.tankland.tanks.Tank;
import ch.fhnw.tankland.view.Window;
import ch.fhnw.tankland.wheather.Cloud;
import ch.fhnw.tankland.wheather.Sun;

public class World {

	private Bounds bounds;
	private Random random;
	private int round;

	private Land land;
	private Sun sun;
	private Cloud[] clouds;
	private Flora flowers;
	private ArrayList<Tank> tanks;
	private Bonus bonus;

	public World(int width, int height, int seed) {
		this.round = 0;
		this.bounds = new Bounds(width, height, 32);
		this.random = new Random(seed);
		this.land = new Land(bounds);
		this.sun = new Sun(bounds);
		this.clouds = new Cloud[width * height / 100]; // one cloud per 100 fields
		for (int i = 0; i < clouds.length; i++) {
			this.clouds[i] = new Cloud(new Position(random, bounds));
		}
		this.flowers = new Flora();
		this.tanks = new ArrayList<>();
	}

	public void plantRandomFlower() {
		Position pos = new Position(random, bounds);
		Flower flower = new Flower(land.getFieldAt(pos), pos);
		flowers.addFlower(flower);
	}

	public Bounds getBounds() {
		return bounds;
	}

	private void add(IStrategy strat) {
		Tank t = new Tank(strat, findFreePosition());
		land.getFieldAt(t.getPosition()).notifyTankEntered(t);
		tanks.add(t);
	}

	private Position findFreePosition() {
		while (true) {
			Position pos = new Position(random, bounds);
			Field f = land.getFieldAt(pos);
			if (f.isGoodPlaceForNewTank()) {
				return new Position(getBounds(), pos.getRoundedX(), pos.getRoundedY());
			}
		}
	}

	private static final int ENVIRONMENT_SLOWDOWN = 10;

	public void simulate(int rounds) throws WinnerFoundException {
		for (int i = 0; i < rounds; i++) {
			round++;
			if (round % ENVIRONMENT_SLOWDOWN == 0) {
				simulatEnvironment();
			}
			simulateTanks();
		}
	}

	private void simulateTanks() throws WinnerFoundException {
		assert tanks.size() > 0;
		Iterator<Tank> iter = tanks.iterator();
		while (iter.hasNext()) {
			Tank n = iter.next();
			if (n.performStep(land)) {
				// ok
			} else {
				iter.remove();
			}
		}
		if (tanks.size() == 1){
			throw new WinnerFoundException(tanks.get(0).getName());
		}
		handleBonus();
	}

	public void simulatEnvironment() {
		sun.move();
		sun.shine(land);
		for (int c = 0; c < clouds.length; c++) {
			clouds[c].move(random, land);
			clouds[c].rain(land);
		}
		land.updateConditions();
		flowers.live(random, land);
		if (round % (100 * ENVIRONMENT_SLOWDOWN) == 0) {
			plantRandomFlower();
		}
	}

	private void handleBonus() {
		if (bonus != null && bonus.isDead(land)) {
			land.getFieldAt(bonus.getPosition()).setBonus(null);
			bonus = null;
		}
		if (round % 1000 == 0 && bonus == null) {
			bonus = new Bonus(findFreePosition());
			land.getFieldAt(bonus.getPosition()).setBonus(bonus);
		}
	}

	public void draw(Graphics2D g) {
		g.fillRect(0, 0, bounds.getPixelWidth(), bounds.getPixelHeight());
		land.draw(g);
		flowers.draw(g);
		for (Tank t : tanks) {
			t.draw(g);
		}
		for (int c = 0; c < clouds.length; c++) {
			clouds[c].draw(g);
		}
		sun.draw(g);
	}

	public static void main(String[] args) throws InterruptedException {
		try {
			World world = new World(30, 20, 2);
			for (int i = 0; i < 20000; i++) {
				world.simulatEnvironment();
			}
			Window window = new Window(world);
			for (int i = 0; i < 5; i++) {
				world.add(new RandomStrategy());
			}
			for (int i = 0; i < 3; i++) {
				world.add(new DerekZoolander());
			}
			while (true) {
				long t0 = System.nanoTime();
				world.simulate(5);
				window.repaint();
				long t1 = System.nanoTime();
				long millisPassed = (t1 - t0) / 1000000;
				long millisLeft = 20 - millisPassed;
				if (millisLeft > 0) {
					Thread.sleep(millisLeft);
				}
			}
		} catch (WinnerFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
