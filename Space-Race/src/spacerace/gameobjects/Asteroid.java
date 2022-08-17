package spacerace.gameobjects;

import java.awt.geom.Ellipse2D;
import java.util.Random;
import spacerace.gui.GUIPanel;

/**
 * Asteroid.java
 *
 * An asteroid object in the game.
 * Aim is to avoid these asteroids. Asteroids spawn on
 * the left or right of the screen and travel horizontally.
 *
 * @author Kush Bharakhada and James March
 */

public class Asteroid {
	
	// Instance variables
	public static final int ASTEROID_SIZE = 15;
	private int xCoordinate;
	private int yCoordinate;
	private int xIncrement;
	private int spawnSide;
	
	// Constructor
	public Asteroid(int speed) {
		xCoordinate = spawnLeftOrRight();
		spawnSide = xCoordinate;
		yCoordinate = randomYCoordinate();
		// Increment determines speed of asteroids
		xIncrement = speed;
	}
	
	// Get Methods ---
	
	public int getAsteroidSpeed() {
		// Speed horizontally is determined by the xIncrement
		return this.xIncrement;
	}
	
	public int getXCoordinate() {
		return this.xCoordinate;
	}
	
	public int getYCoordinate() {
		return this.yCoordinate;
	}
	
	// ---
	
	private int randomYCoordinate() {
		// Spawn asteroid randomly on y axis
		double random = Math.random();
		return (int)(random * GUIPanel.GAME_HEIGHT);		
	}
	
	private int spawnLeftOrRight() {
		Random random = new Random();
		// Generate a random boolean to determine whether asteroid
		// starts on left or right of screen
		boolean randomBool = random.nextBoolean();
		if (randomBool) {
			return GUIPanel.GAME_WIDTH;
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
