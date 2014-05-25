// Created by Luzius on 31.03.2014

package ch.fhnw.algd2.lesson9;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.tankland.WinnerFoundException;
import ch.fhnw.tankland.World;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.tanks.Bonus;
import ch.fhnw.tankland.tanks.ETankAction;
import ch.fhnw.tankland.view.Window;

public class TankTest {
	
	private ArrayList<IStrategy> lists;

	@Before
	public void setUp() throws Exception {
		lists = new ArrayList<>();

		/*lists.add(new ch.fhnw.algd2.luzius.DijkstraStrategy());
		lists.add(new ch.fhnw.algd2.florianfankhauser.tankland.Funky());
		lists.add(new ch.fhnw.algd2.christianguedel.TankStrategy());
		lists.add(new ch.fhnw.algd2.mariusdubach.tankland.Marius2());
        lists.add(new ch.fhnw.algd2.stephanbrunner.PureDijkstraStrategy());
        lists.add(new ch.fhnw.algd2.larskessler.LarsKessler());
        lists.add(new ch.fhnw.algd2.stephenrandles.tankland.ShortestPathStrategy());*/
        //lists.add(new ch.fhnw.algd2.kevinwieser.Strategy());
        lists.add(new ch.fhnw.algd2.emanuelmistretta.AlgGame2());
        lists.add(new ch.fhnw.algd2.romangribi.Tank());
        lists.add(new ch.fhnw.algd2.lukasmusy.MyStrategy());
        lists.add(new ch.fhnw.algd2.martineberle.Strategy());
		// add your own implementation here
        
	}

	@Test
	public void testTank() throws WinnerFoundException, InterruptedException {
		int reference = -1;
		int seed = 12; // (int) System.nanoTime();
		ETankAction.FREE_TURNS = true;
		for (IStrategy map : lists) {
			// should always be the same world, as same seed
			World world = new World(1000000, 30, 20, seed);
			world.setEternal(true);
			for (int i = 0; i < 7000; i++) {
				world.simulateEnvironment();
			}
			world.add(map);
			Bonus b = world.getBonus();
			int max = 100000;
			int step = 0;
			Window window = new Window(world);
			try {
				while (b == world.getBonus() && step < max) {
					world.simulate(1);
					if (step % 10 == 0) {
						window.repaint();
						Thread.sleep(50);
					}
					step++;
				}
			} finally {
				window.dispose();
			}
			if (reference == -1) {
				reference = step;
				System.out.println("Reference implementation has found cherry in  " + step + " steps");
			} else if (step > reference) {
				throw new RuntimeException(map.getName() + " is too slow");
			}
		}

	}

}
