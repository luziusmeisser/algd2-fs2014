// Created by Stephen Randles 28.02.2014

package ch.fhnw.algd2.stephenrandles.test;

import java.util.Random;

import ch.fhnw.algd2.stephenrandles.lesson02.SkipList;



public class MySkipListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SkipList<Integer> list = new SkipList<>();
		Random rand = new Random();
		
		for (int i=0; i< 10; i++) {
			list.add(rand.nextInt(100));
			list.printList();
		}
	}

}
