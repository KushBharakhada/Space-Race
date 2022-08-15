package spacerace.gameobjects;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

/**
 * Target.java
 *
 * The item the user needs to get to level up
 *
 * @author Kush Bharakhada and James March
 */

public class Target {
	
	// Instance variables
	private int xCoordinate;
	private int yCoordinate;
	
	public static final int TARGET_SIZE_WIDTH = 20;
	public static final int TARGET_SIZE_HEIGHT = 20;
	
	public Target(int x, int y) {
		xCoordinate = x;
		yCoordinate = y;
	}
	
	public Ellipse2D drawTarget() {
		// Creates a 2D target
		return new Ellipse2D.Double(xCoordinate, yCoordinate, TARGET_SIZE_WIDTH, TARGET_SIZE_HEIGHT);
	}

}
