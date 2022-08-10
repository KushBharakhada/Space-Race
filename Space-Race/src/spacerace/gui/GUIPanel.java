package spacerace.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;
import spacerace.gameobjects.Asteroid;
import spacerace.gameobjects.AsteroidRunnable;
import spacerace.gameobjects.Player;

/**
 * GUIPanel.java
 *
 * Main panel for the game.
 *
 * @author Kush Bharakhada and James March
 */

// TODO Arraylist grows too large, dead thread asteroids need removing

public class GUIPanel extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	// Instance Variables
	private ArrayList<Asteroid> asteroids = new ArrayList<>();
	private Player player;

	public GUIPanel() {
		this.setBackground(Color.BLACK);
        setLayout(null);
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		player.draw(g);
		// Control access of multiple threads to the array list
		synchronized(asteroids) {
			// Goes through all the asteroids that have been added
			for (Asteroid a : asteroids) {
				g2d.setColor(Color.GRAY);
				g2d.fill(a.drawAsteroid());
			}
		}
		
	}
	
	public void createPlayer(int x, int y) {
		player = new Player(400, 525);
	}
	
	public void launchAsteroid(int speed) {
		// Creating an asteroid object
		Asteroid a = new Asteroid(speed);
		
		// Reliable communication between threads
		// Control access of multiple threads to the array list
		synchronized(asteroids) {
			asteroids.add(a);
		}
		
		// Starts a new thread for each new asteroid
	    Runnable r = new AsteroidRunnable(a, this);
	    Thread t = new Thread(r);
	    t.start(); 
	}
	
	/*
	public class AL extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e){
			player.keyPressed(e);
		}
	}
	*/
	
	public void launchGame() {
		createPlayer(400, 300);
		
		
		// Infinitely produces asteroids
		while (true) {
		    launchAsteroid(1);
		    try {
		    	// How often the asteroids are launched
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		    System.out.println(asteroids.size());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
        
    }
	
	@Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }
	
	@Override
    public void keyReleased(KeyEvent e) {
        
    }
	
		
}
