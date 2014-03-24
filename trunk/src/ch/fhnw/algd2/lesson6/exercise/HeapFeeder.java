// Created by Luzius on 24.03.2014

package ch.fhnw.algd2.lesson6.exercise;

import java.util.Random;

public class HeapFeeder extends Thread {

	public static final int ITERATIONS = 100;
	
	private Random rand = new Random(13);
	private IHeap heap;

	public HeapFeeder(IHeap heap) {
		setDaemon(true);
		this.heap = heap;
	}

	public void run() {
		try {
			for (int i = 0; i < ITERATIONS; i++) {
				heap.offer(Integer.toString(rand.nextInt(1000)));
				if (i % 10 == 0) {
					Thread.sleep(10);
				}
			}
		} catch (InterruptedException e) {
		}
	}

}
