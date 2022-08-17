package spacerace.gameobjects;

import java.awt.geom.Ellipse2D;

/**
 * Target.java
 *
 * The item the user needs to get to in order to level up.
 * Spawns randomly on the panel every time it is collected.
 *
 * @author Kush Bharakhada and James March
 */

public class Target {
	
	// Instance variables
	public static final int TARGET_SIZE = 20;
	private int xCoordinate;
	private int yCoordinate;
	
	// Constructor, parameters are coordinates to place target
	public Target(int x, int y) {
		xCoordinate = x;
		yCoordinate = y;
	}
	
	// Get methods
	public int getXCoordinate() {
		return this.xCoordinate;
	}
	
	public int getYCoordinate() {
		return this.yCoordinate;
	}
	
	// ---
	
	public Ellipse2D drawTarget() {
		// Creates a 2D target
		return new Ellipse2D.Double(xCoordinate, yCoordinate, TARGET_SIZE, TARGET_SIZE);
	}

}
