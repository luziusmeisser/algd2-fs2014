// Created by Luzius on 19.05.2014

package ch.fhnw.tankland;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import ch.fhnw.algd2.florianfankhauser.tankland.Funky;
import ch.fhnw.algd2.kevinwieser.KevinStrategy;
import ch.fhnw.algd2.mariusdubach.tankland.Marius;
import ch.fhnw.tankland.strategy.example.DerekZoolander;
import ch.fhnw.tankland.strategy.example.EdsgarBot;
import ch.fhnw.tankland.strategy.example.RaegarBot;
import ch.fhnw.tankland.strategy.example.RandomStrategy;

public class WinnerStats {

	private Map<String, Integer> wins = new TreeMap<>();
	
	public void run(int rounds) {
		Random rand = new Random(123);
		for (int i = 0; i < rounds; i++) {
			World world = new World(30, 20, rand.nextInt());
			for (int j = 0; j < 10000; j++) {
				world.simulateEnvironment();
			}
			try {
				world.add(new KevinStrategy());
				world.add(new Marius());
				world.add(new RandomStrategy(rand));
				world.add(new DerekZoolander());
				world.add(new EdsgarBot());
				world.add(new RaegarBot());
				world.add(new Funky());
				while (true){
					world.simulate(100);
				}
			} catch (WinnerFoundException e) {
				Integer prev = wins.put(e.getName(), 1);
				if (prev != null){
					wins.put(e.getName(), prev + 1);
				}
				System.out.println(e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		WinnerStats stats = new WinnerStats();
		stats.run(10);
		stats.print();
	}

	private void print() {
		for (Map.Entry<String, Integer> e: wins.entrySet()){
			if (e.getValue() > 0){
				System.out.println(e.getKey() + " wins " + e.getValue() + " rounds");
			}
		}
	}
	
}
