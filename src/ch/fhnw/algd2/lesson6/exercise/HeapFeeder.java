// Created by Luzius on 24.03.2014

package ch.fhnw.algd2.lesson6.exercise;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeSet;

public class HeapFeeder extends Thread {

	public static final int ITERATIONS = 100;
	
	private Random rand = new Random(13);
	private IHeap heap;
	private PriorityQueue<String> control;
	
	public HeapFeeder(IHeap heap) {
		setDaemon(true);
		this.heap = heap;
		this.control = new PriorityQueue<>();
	}
	
	public String getNextFromControlQueue(){
		return control.poll();
	}
	
	public void run() {
		try {
			for (int i = 0; i < ITERATIONS; i++) {
				String s = Integer.toString(rand.nextInt(1000));
				heap.offer(s);
				control.offer(s);
				if (i % 10 == 0) {
					Thread.sleep(10);
				}
			}
		} catch (InterruptedException e) {
		}
	}

}
