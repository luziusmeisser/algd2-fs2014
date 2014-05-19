// Created by Luzius on May 2, 2014

package ch.fhnw.tankland;

public class WinnerFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public WinnerFoundException(String name) {
		super(name + " won this round");
		this.name = name;
	}

	public String getName() {
		return name;
	}


}
