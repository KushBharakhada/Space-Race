package spacerace.gameobjects;

import java.awt.Component;
import spacerace.gui.GUIPanel;

/**
 * AsteroidRunnable.java
 *
 * Implements runnable to use for multi-threading the asteroid objects.
 *
 * @author Kush Bharakhada and James March
 */

public class AsteroidRunnable implements Runnable {
	
	// Instance Variables
	private Asteroid asteroid;
	private Component component;

	// Constructor
	public AsteroidRunnable(Asteroid a, Component c) {
		asteroid = a;
		// Component is the main panel to be drawn on
		component = c;
	}
	
	public void run() {		
		try {	
			// Number of frames the asteroid has so asteroid doesn't travel forever
			int steps = (GUIPanel.GAME_WIDTH / asteroid.getAsteroidSpeed()) + Asteroid.ASTEROID_SIZE;
			final int FRAME_SPEED = 10;
			
			// Movement of the asteroid, horizontally
			for (int i = 1; i <= steps ; i++) {
	            asteroid.move();
	            component.repaint();
	            Thread.sleep(FRAME_SPEED);
	        }
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
