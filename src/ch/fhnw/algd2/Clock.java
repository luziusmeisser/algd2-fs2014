// Created by Luzius on Feb 15, 2014

package ch.fhnw.algd2;

public class Clock {
	
	private static final long MUCH = 5;
	
	private long start, end;
	
	public Clock(){
		this.start = System.nanoTime();
	}

	public void stop() {
		this.end = System.nanoTime();
	}
	
	private long getTime() {
		return end - start;
	}

	public boolean tookMuchLongerThan(Clock c2) {
		return getTime() > MUCH * c2.getTime();
	}

	@Override
	public String toString(){
		if (end == 0){
			return "Clock";
		} else {
			long diff = getTime();
			if (diff > 1000000){
				diff /= 1000000;
				return diff + "ms";
			} else {
				return diff + "ns";
			}
		}
	}

}
