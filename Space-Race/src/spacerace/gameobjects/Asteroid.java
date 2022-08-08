package spacerace.gameobjects;

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
	
	private static final int ASTEROID_SIZE = 15;
	// Starting position of asteroids
	private double xCoordinate;
	private double yCoordinate;
	// Increment determines speed of asteroids
	private double xIncrement;
	// Whether asteroid spawned on the left or right of screen
	private double spawnSide;
	// Lifetime of an asteroid - number of frames it has for the motion
	private double steps;
	
	// Constructor
	public Asteroid() {
		xCoordinate = spawnLeftOrRight();
		spawnSide = xCoordinate;
		yCoordinate = randomYCoordinate();
		xIncrement = 1;
		steps = (GUIFrame.GAME_WIDTH / xIncrement) + ASTEROID_SIZE;
	}
	
	public double getSteps() {
		return this.steps;
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
