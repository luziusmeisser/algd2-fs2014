// Created by Luzius on May 2, 2014

package ch.fhnw.tankland.tanks;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ch.fhnw.tankland.fields.Land;
import ch.fhnw.tankland.geometry.Position;
import ch.fhnw.tankland.geometry.Positionable;

public class Bonus extends Positionable {
	
	private static Image img;

	static {
		try {
			img = ImageIO.read(new File("data/cherry.png"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private boolean eaten;
	
	public Bonus(Position position) {
		super(position);
		this.eaten = false;
	}
	
	public boolean isDead(Land l){
		if (l.getFieldAt(getPosition()).isWater()){
			return true;
		} else {
			return eaten;
		}
	}
	
	public void draw(Graphics g, int x, int y){
		g.drawImage(img, x, y, null);
	}

	public void applyTo(Tank tank) {
		assert !eaten;
		tank.increaseTime(10);
		eaten = true;
	}

}
