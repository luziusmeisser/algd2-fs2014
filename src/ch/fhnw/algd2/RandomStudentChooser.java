// Created by Luzius on 24.02.2014

package ch.fhnw.algd2;

import java.util.Random;

public class RandomStudentChooser {

	private static final Random rand = new Random();
	
	private final static String[] ALL = new String[] { "Christian Guedel", "Emanuel Mistretta", "Florian Fankhauser", "Kevin Wieser", "Lars Kessler", "Lukas Musy", "Marco Gaiffi", "Marius Dubach", "Martin Eberle", "Roman Gribi", "Stephan Brunner",
			"Stephan Randles", "Yannick Augstburger" };

	public static void main(String[] args) throws InterruptedException {
		int wheelSteps = rand.nextInt(ALL.length) * 3 + ALL.length * 4;
		for (int i=0; i<wheelSteps; i++){
			System.out.println(ALL[i % ALL.length]);
			Thread.sleep((i + 1) * (i + 1));
		}
		System.out.println("Finished!");
	}
	
}
