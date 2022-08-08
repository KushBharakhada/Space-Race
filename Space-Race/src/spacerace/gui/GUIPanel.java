package spacerace.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import spacerace.gameobjects.Asteroid;

/**
 * GUIPanel.java
 *
 * Main panel for the game.
 *
 * @author Kush Bharakhada and James March
 */

public class GUIPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	// Instance Variables
	private ArrayList<Asteroid> asteroids = new ArrayList<>();

	public GUIPanel() {
        setLayout(null);
    }
	
	public void addAsteroid(Asteroid a) {
		// Adds an asteroid to the array list
		asteroids.add(a);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// Goes through all the asteroids that have been added
		for (Asteroid a : asteroids) {
			g2d.fill(a.drawAsteroid());
		}	
	}
		
	public void launchAsteroid() {
		try {
			// Create an asteroid object
			Asteroid asteroid = new Asteroid();
			this.addAsteroid(asteroid);
			
			// Movement of the asteroid, horizontally
			for (int i = 1; i <= asteroid.getSteps() ; i++) {
	            asteroid.move();
	            this.paint(this.getGraphics());
	            Thread.sleep(10);
	         }	
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void launchGame() {
		launchAsteroid();
	}
		
}
