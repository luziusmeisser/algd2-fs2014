// Created by Luzius on 24.02.2014

package ch.fhnw.algd2;

import java.util.Random;

public class RandomStudentChooser {

	private static final Random rand = new Random();
	
	private final static String[] ALL = new String[] { "Christian Guedel", "Emanuel Mistretta", "Florian Fankhauser", "Kevin Wieser", "Lars Kessler", "Lukas Musy", "Marco Gaiffi", "Marius Dubach", "Martin Eberle", "Roman Gribi", "Stephan Brunner",
			"Stephan Randles", "Yannick Augstburger" };

	public static void main(String[] args) throws InterruptedException {
		int steps = rand.nextInt(ALL.length);
		int pause = 10;
		for (int i=0; i<55; i++){
			System.out.print("\n" + ALL[(i + steps) % ALL.length]);
			Thread.sleep(pause);
			pause = pause * 11 / 10;
		}
		System.out.println(" is the chosen one!");
	}
	
}
