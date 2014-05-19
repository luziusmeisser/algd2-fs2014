package ch.fhnw.tankland.fields;

import java.awt.Graphics2D;

import ch.fhnw.tankland.geometry.Bounds;
import ch.fhnw.tankland.geometry.Position;

public class Land {

	private Bounds bounds;
	private Field[][] area;
	private Field[] list; // list of the same fields for convenience

	public Land(Bounds bounds) {
		this.bounds = bounds;
		this.area = new Field[bounds.getWidth()][bounds.getHeight()];
		// Alles mit Boden fuellen
		for (int i = 0; i < area.length; i++) {
			for (int j = 0; j < area[i].length; j++) {
				area[i][j] = new Plains(new Position(bounds, i + 0.5, j + 0.5));
			}
		}
		this.list = prepareList(area);
	}

	private Field[] prepareList(Field[][] area) {
		Field[] list = new Field[area.length * area[0].length];
		for (int i = 0; i < area.length; i++) {
			for (int j = 0; j < area[i].length; j++) {
				list[j * area.length + i] = area[i][j];
			}
		}
		return list;
	}

	// Returns the 9 fields around the given position
	public Field[] getFieldsAround(Position posOrig, boolean includeOwn) {
		Position pos = posOrig.copy();
		Field[] fields = new Field[includeOwn ? 9 : 8];
		int i = 0;
		if (includeOwn){
			fields[i++] = getFieldAt(pos);
		}
		pos.moveUp();
		fields[i++] = getFieldAt(pos);
		pos.moveRight();
		fields[i++] = getFieldAt(pos);
		pos.moveDown();
		fields[i++] = getFieldAt(pos);
		pos.moveDown();
		fields[i++] = getFieldAt(pos);
		pos.moveLeft();
		fields[i++] = getFieldAt(pos);
		pos.moveLeft();
		fields[i++] = getFieldAt(pos);
		pos.moveUp();
		fields[i++] = getFieldAt(pos);
		pos.moveUp();
		fields[i++] = getFieldAt(pos);
		return fields;
	}

	public void updateConditions() {
		for (int i = 0; i < area.length; i++) {
			for (int j = 0; j < area[i].length; j++) {
				area[i][j].updateConditions();
			}
		}
	}

	public Field getFieldAt(Position position) {
		return area[position.getRoundedX()][position.getRoundedY()];
	}

	public void draw(Graphics2D g) {
		int fieldSize = bounds.getFieldSize();
		for (int i = 0; i < area.length; i++) {
			for (int j = 0; j < area[i].length; j++) {
				area[i][j].draw(g, i*fieldSize, j*fieldSize, fieldSize - 1);
			}
		}
	}

	public Field[] getAllFields() {
		return list;
	}

}
