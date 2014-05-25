// Created by Luzius on 19.05.2014

package ch.fhnw.algd2.lesson10;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.algd2.christianguedel.TankStrategy;
import ch.fhnw.algd2.florianfankhauser.tankland.Funky;
import ch.fhnw.algd2.kevinwieser.CherryStrategyKevin;
import ch.fhnw.algd2.kevinwieser.Strategy;
import ch.fhnw.algd2.larskessler.LarsKessler;
import ch.fhnw.algd2.mariusdubach.tankland.Marius;
import ch.fhnw.algd2.stephenrandles.tankland.SurvivalStrategy;
import ch.fhnw.tankland.WinnerFoundException;
import ch.fhnw.tankland.WinnerStats;
import ch.fhnw.tankland.World;
import ch.fhnw.tankland.strategy.IStrategy;
import ch.fhnw.tankland.strategy.example.EdsgarBot;
import ch.fhnw.tankland.strategy.example.RandomStrategy;

public class StrategyTest {

	private ArrayList<IStrategy> strats;
	
	@Before
	public void setUp() throws Exception {
		strats = new ArrayList<>();
		strats.add(new Funky());
		strats.add(new Marius());
		strats.add(new EdsgarBot());
		strats.add(new SurvivalStrategy());
		strats.add(new TankStrategy());
		strats.add(new LarsKessler());
		strats.add(new CherryStrategyKevin());
	}
	
	@Test
	public void testStrategy() throws WinnerFoundException, InterruptedException {
		for (final IStrategy s: strats){
			final Random rand = new Random(123);
			WinnerStats stats = new WinnerStats(){
				@Override
				protected void fill(World world) {
					try {
						for (int i=0; i<4; i++){
							world.add(new RandomStrategy(rand));
						}
						world.add(s.getClass().newInstance());
					} catch (InstantiationException e) {
						throw new RuntimeException(e);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				}
			};
			stats.run(rand, 15);
			assert stats.getWins(s.getName()) >= 5;
		}
	}
	
}
