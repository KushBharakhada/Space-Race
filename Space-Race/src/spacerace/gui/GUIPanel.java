package spacerace.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import spacerace.gameobjects.Asteroid;
import spacerace.gameobjects.AsteroidRunnable;

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
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// Goes through all the asteroids that have been added
		for (Asteroid a : asteroids) {
			g2d.fill(a.drawAsteroid());
		}
	}
	
	public void add(Asteroid a) {
		asteroids.add(a);
	}
	
	public void populateAsteroids(int speed) {
		// Creates an asteroid object and adds to the array list
		Asteroid a = new Asteroid(speed);
        asteroids.add(a);		
	}
	
	public void launchAsteroid() {
		// Starts a new thread for each new asteroid
	    Runnable r = new AsteroidRunnable(asteroids.get(0), this);
	    Thread t = new Thread(r);
	    t.start(); 
	}
	
	public void launchGame() {
	    populateAsteroids(1);
	    launchAsteroid();
	    System.out.println("Number of active threads: " + Thread.activeCount());
	}
		
}
