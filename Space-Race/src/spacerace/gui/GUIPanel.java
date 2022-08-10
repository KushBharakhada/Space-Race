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
import spacerace.gameobjects.Target;

/**
 * GUIPanel.java
 *
 * Main panel for the game.
 *
 * @author Kush Bharakhada and James March
 */

// TODO Concurrent modification error needs fixing

public class GUIPanel extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	// Instance Variables
	private ArrayList<Asteroid> asteroids = new ArrayList<>();
	private ArrayList<Thread> threads = new ArrayList<>();
	private Player player;
	private Target target;

	public GUIPanel() {
		this.setBackground(Color.BLACK);
        setLayout(null);
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		player.draw(g);
		g2d.setColor(Color.YELLOW);
		g2d.fill(target.drawTarget());
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
	
	public void createTarget(int x, int y) {
		target = new Target(x, y);
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
	    threads.add(t);
	    t.start(); 
	}
	
	public void launchGame() {
		createTarget(200, 200);		
		player = new Player(GUIFrame.GAME_WIDTH/2, GUIFrame.GAME_HEIGHT-100); //created player at bottom of screen
		Boolean start = false; //checks whether an asteroid has been created

		//for the start of the game, need to ensure an asteroid spawns
		while (true) {
			if (start) { //once an asteroid has spawned, can start quick generation of asteroids
				// Infinitely produces asteroids when game is started
				if (RNG(20) == 0) {
					launchAsteroid(1);
				}
			}
			else { //otherwise create one asteroid so the program doesnt break when checking.isAlive and start game
				launchAsteroid(1);
				start = true;
			}
		    try {
		    	
		    	// How often the asteroids are launched
				Thread.sleep(8); //the lower this number, harder the game
		    	player.setXCoord(player.getXCoord() + player.getXSpeed());
				player.setYCoord(player.getYCoord() + player.getYSpeed());
				player.checkOutOfBounds();
				
				// Remove a dead thread from the threads array list
				// and the corresponding asteroid that has finished its journey
				if (!threads.get(0).isAlive()) {
					threads.remove(0);
					asteroids.remove(0);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		    
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
        player.keyReleased(e);
    }
	
	//generates a random number between 0 and probability - 1
	public int RNG(int probability) {
		return (int)Math.floor(Math.random()*probability);
	}
	
		
}
