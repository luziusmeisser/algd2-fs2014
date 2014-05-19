// Created by Luzius on 19.05.2014

package ch.fhnw.tankland;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import ch.fhnw.algd2.florianfankhauser.tankland.Funky;
import ch.fhnw.algd2.kevinwieser.KevinStrategy;
import ch.fhnw.algd2.mariusdubach.tankland.Marius;
import ch.fhnw.tankland.strategy.example.RaegarBot;

public class WinnerStats {

	private Map<String, Integer> wins = new TreeMap<>();

	public void run(Random rand, int rounds) {
		for (int i = 0; i < rounds; i++) {
			World world = new World(30, 20, rand.nextInt());
			for (int j = 0; j < 10000; j++) {
				world.simulateEnvironment();
			}
			try {
				fill(world);
				while (true) {
					world.simulate(100);
				}
			} catch (WinnerFoundException e) {
				Integer prev = wins.put(e.getName(), 1);
				if (prev != null) {
					wins.put(e.getName(), prev + 1);
				}
				System.out.println(e.getMessage());
			}
		}
	}

	protected void fill(World world) {
		world.add(new KevinStrategy());
		world.add(new Marius());
		// for (int bot = 0; bot < 2; bot++) {
		// world.add(new RandomStrategy(rand));
		// world.add(new DerekZoolander());
		// }
		// world.add(new EdsgarBot());
		world.add(new RaegarBot());
		world.add(new Funky());
	}

	private void print() {
		for (Map.Entry<String, Integer> e : wins.entrySet()) {
			if (e.getValue() > 0) {
				System.out.println(e.getKey() + " wins " + e.getValue() + " rounds");
			}
		}
	}

	public int getWins(String name) {
		return wins.containsKey(name) ? wins.get(name) : 0;
	}

}
