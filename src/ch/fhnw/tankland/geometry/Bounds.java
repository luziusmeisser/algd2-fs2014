package ch.fhnw.tankland.geometry;

public class Bounds {

	private int fieldsize;

	private int width;
	private int height;

	public Bounds(int width, int height, int fieldsize) {
		this.width = width;
		this.height = height;
		this.fieldsize = fieldsize;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	// Makes sure x is at least 0 and smaller than width
	public double checkX(double x) {
		while (x < 0) {
			x += width;
		}
		while (x >= width){
			x -= width;
		}
		return x;
	}

	// Makes sure y is at least 0 and smaller than height
	public double checkY(double y) {
		while (y < 0) {
			y += height;
		}
		while (y >= height){
			y -= height;
		}
		return y;
	}

	public int getPixelWidth() {
		return getWidth() * fieldsize;
	}
	
	public int getPixelHeight() {
		return getHeight() * fieldsize;
	}

	public int convertToPixelPosition(double coord) {
		return (int) (coord * fieldsize);
	}
	
	public int getFieldSize() {
		return fieldsize;
	}
	
	@Override
	public String toString(){
		return "(" + width + " x " + height + ")";
	}

}
