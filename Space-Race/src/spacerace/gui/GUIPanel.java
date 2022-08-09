package spacerace.gui;

import java.awt.Color;
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

// TODO Causes ConcurrentModificationException ***********
// TODO Arraylist grows too large, dead thread asteroids need removing

public class GUIPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	// Instance Variables
	private ArrayList<Asteroid> asteroids = new ArrayList<>();

	public GUIPanel() {
		this.setBackground(Color.BLACK);
        setLayout(null);
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// Goes through all the asteroids that have been added
		for (Asteroid a : asteroids) {
			g2d.setColor(Color.GRAY);
			g2d.fill(a.drawAsteroid());
		}
	}
	
	public void add(Asteroid a) {
		// Adds an asteroid to the asteroid array list
		asteroids.add(a);
	}
	
	public void launchAsteroid(int speed) {
		// Creating an asteroid object
		Asteroid a = new Asteroid(speed);
        asteroids.add(a);
		// Starts a new thread for each new asteroid
	    Runnable r = new AsteroidRunnable(a, this);
	    Thread t = new Thread(r);
	    t.start(); 
	}
	
	public void launchGame() {
		// Infinitely produces asteroids
		while (true) {
		    launchAsteroid(1);
		    try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		    System.out.println(Thread.activeCount());
		    System.out.println(asteroids.size());
		}
	}
	
	
	// Testing Threads
	/*
	public Thread getThread(String name) {
		for (Thread t : Thread.getAllStackTraces().keySet()) {
			if (t.getName().equals(name)) {
				return t;
			}
		}
		return null;
	}
	
	public void launchGame() {
	    addAsteroid(1);
	    launchAsteroid();
	    System.out.println("Number of active threads: " + Thread.activeCount());
	    while (getThread("hello") != null) {
	    	System.out.println(getThread("hello").isAlive());
	    }
	    System.out.println("Number of active threads: " + Thread.activeCount());  
	}
	*/
		
}
