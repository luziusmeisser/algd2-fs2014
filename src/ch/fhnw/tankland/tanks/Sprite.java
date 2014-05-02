// Created by Luzius on May 2, 2014

package ch.fhnw.tankland.tanks;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	public static final int TANK_SIZE = 32;
	public static final int MAX_COLOR = 7;

	private Image[] imgs;

	public Sprite() {
		this.imgs = new Image[EOrientation.values().length];
		try {
			for (int i = 0; i < imgs.length; i++) {
				imgs[i] = ImageIO.read(new File(EOrientation.values()[i].getFileName()));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Image getImg(EOrientation orientation) {
		return imgs[orientation.ordinal()];
	}

	public int getX(int color, EOrientation orientation) {
		switch (orientation) {
		case EAST:
		case WEST:
		default:
			return 0;
		case SOUTH:
			return (MAX_COLOR - color) * TANK_SIZE;
		case NORTH:
			return color * TANK_SIZE;
		}
	}
	
	public int getY(int color, EOrientation orientation) {
		switch (orientation) {
		case SOUTH:
		case NORTH:
		default:
			return 0;
		case EAST:
			return color * TANK_SIZE;
		case WEST:
			return (MAX_COLOR - color) * TANK_SIZE;
		}
	}

}
