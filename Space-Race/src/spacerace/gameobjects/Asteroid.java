package spacerace.gameobjects;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import spacerace.gui.GUIFrame;

/**
 * Asteroid.java
 *
 * An asteroid in the game.
 *
 * @author Kush Bharakhada and James March
 */

public class Asteroid {
	
	// Instance variables
	
	public static final int ASTEROID_SIZE = 15;
	private double xCoordinate;
	private double yCoordinate;
	private int xIncrement;
	private double spawnSide;
	
	// Constructor
	public Asteroid(int speed) {
		xCoordinate = spawnLeftOrRight();
		spawnSide = xCoordinate;
		yCoordinate = randomYCoordinate();
		// Increment determines speed of asteroids
		xIncrement = speed;
	}
	
	public int getAsteroidSpeed() {
		// Speed horizontally is determined by the xIncrement
		return this.xIncrement;
	}
	
	public double randomYCoordinate() {
		// Spawn asteroid randomly on y axis
		double random = Math.random();
		return random * GUIFrame.GAME_HEIGHT;		
	}
	
	public double spawnLeftOrRight() {
		Random random = new Random();
		// Generate a random boolean to determine whether asteroid
		// starts on left or right of screen
		boolean randomBool = random.nextBoolean();
		if (randomBool) {
			return GUIFrame.GAME_WIDTH;
		}
		return 0;
	}
	
	public Ellipse2D drawAsteroid() {		
		// Creates a 2D asteroid
		return new Ellipse2D.Double(xCoordinate, yCoordinate, ASTEROID_SIZE, ASTEROID_SIZE);
		
	}
	
	public void move() {
		// Adjusting the position of the asteroid in each frame
		if (spawnSide == 0)
			xCoordinate += xIncrement;
		else
			xCoordinate -= xIncrement;
	}
	
}
