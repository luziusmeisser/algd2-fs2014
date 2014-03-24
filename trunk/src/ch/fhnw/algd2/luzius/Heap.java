// Created by Luzius on 24.03.2014

package ch.fhnw.algd2.luzius;

import java.util.concurrent.PriorityBlockingQueue;

import ch.fhnw.algd2.lesson6.exercise.IHeap;

public class Heap implements IHeap {
	
	// Not an acceptable implementation! For testing puposes only.
	private PriorityBlockingQueue<String> queue;

	public Heap(){
		this.queue = new PriorityBlockingQueue<>();
	}
	
	@Override
	public void offer(String s) {
		this.queue.offer(s);
	}

	@Override
	public String peek() {
		return queue.peek();
	}

	@Override
	public String poll() {
		return queue.poll();
	}

}
