package ch.fhnw.tankland.view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

import ch.fhnw.tankland.World;
import ch.fhnw.tankland.geometry.Bounds;

public class Window {

	private World world;
	private JFrame frame;

	public Window(World world) {
		this.world = world;
		this.frame = new JFrame("Woodland");
		this.frame.add(new JComponent() {

			@Override
			public void paint(Graphics g) {
				draw((Graphics2D) g);
			}
		});
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Bounds bounds = world.getBounds();
		this.frame.setSize(bounds.getPixelWidth() + 20, bounds.getPixelHeight() + 40);
		this.frame.setVisible(true);
	}

	protected void draw(Graphics2D g) {
		world.draw(g);
	}

	public void repaint() {
		frame.repaint();
	}

	public void dispose() {
		frame.dispose();
	}

}
