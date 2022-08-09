package spacerace.gameobjects;

import java.awt.Component;

import spacerace.gui.GUIFrame;

public class AsteroidRunnable implements Runnable {
	
	private Asteroid asteroid;
	private Component component;

	public AsteroidRunnable(Asteroid a, Component c) {
		asteroid = a;
		component = c;
	}
	
	public void run() {
			
		try {	
			// Number of frames the asteroid has so asteroid doesn't travel forever
			int steps = (GUIFrame.GAME_WIDTH / asteroid.getAsteroidSpeed()) + Asteroid.ASTEROID_SIZE;
			
			// Movement of the asteroid, horizontally
			for (int i = 1; i <= steps ; i++) {
	            asteroid.move();
	            component.repaint();
	            Thread.sleep(10);
	        }
			
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}

}
