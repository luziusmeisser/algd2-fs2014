package ch.fhnw.tankland.plants;

import java.awt.Graphics2D;
import java.util.Random;

import ch.fhnw.tankland.fields.Land;

public class Flora {

	private Flower[] flowers;

	public Flora() {
		this.flowers = new Flower[] {};
	}

	public void addFlower(Flower flower) {
		int empty = findEmptyPos();
		if (empty >= 0) {
			flowers[empty] = flower;
		} else {
			Flower[] newList = new Flower[flowers.length + 1];
			System.arraycopy(flowers, 0, newList, 0, flowers.length);
			newList[flowers.length] = flower;
			this.flowers = newList;
		}
		flower.getField().notifyPlantAdded();
	}
	
	public void draw(Graphics2D g) {
		for (int i = 0; i < flowers.length; i++) {
			if (flowers[i] != null) {
				flowers[i].draw(g);
			}
		}
	}

	private int findEmptyPos() {
		for (int i = 0; i < flowers.length; i++) {
			if (flowers[i] == null) {
				return i;
			}
		}
		return -1; // -1 heisst: nicht gefunden
	}

	public void live(Random rand, Land land) {
		for (int f = 0; f < flowers.length; f++) {
			try {
				if (flowers[f] != null) {
					Flower offspring = flowers[f].live(rand, land);
					if (offspring != null){
						addFlower(offspring);
					}
				}
			} catch (FlowerDiedException e) {
				flowers[f].getField().notifyPlantRemoved();
				flowers[f] = null;
			}
		}
	}

}
